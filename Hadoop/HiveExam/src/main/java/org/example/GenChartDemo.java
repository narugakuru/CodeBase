package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenChartDemo {
    // 构建查询逻辑 SQL

    // 编写数据图表模版 FTL

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    public static void main(String[] args) throws IOException, TemplateException, SQLException {
        // 连接JDBC
        try {
            // 加载Hive的JDBC驱动类
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
        // 设置JDBC连接字符串并创建连接
        Connection con = DriverManager.getConnection("jdbc:hive2://192.168.1.128:10000/movie", "root", "root");
        //
        Statement stmt = con.createStatement();

        String sql = "SELECT age, AVG(rating) AS avg_rating FROM movies GROUP BY age ORDER BY age ASC;";

        // 执行查询
        ResultSet res = stmt.executeQuery(sql);
        List<String> titles = new ArrayList<String>();
        List<String> coutes = new ArrayList<String>();
        // 遍历结果集 => 生成一个为模版合并使用的数据集
        while (res.next()) {
            titles.add(res.getString(1));
            coutes.add(res.getString(2));
            System.out.println(res.getString(1) + "\t" + res.getString(2));
        }
        // 生成模版
        // 生成配置
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDirectoryForTemplateLoading(new File("./templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        // 指定数据
        Map root = new HashMap();
        root.put("mts", titles);
        root.put("rts", coutes);
        // 设置模版保存路径
        // 指定模版
        Template temp = cfg.getTemplate("movieratingtop10.ftl");

        // 打开数据保存的文件流
        try  (OutputStream fileOut = new FileOutputStream("reports/echart.html");
              Writer out = new OutputStreamWriter(fileOut)) {
            // 融合数据与模版，并输出到指定流
            temp.process(root, out);
        }
    }
}
