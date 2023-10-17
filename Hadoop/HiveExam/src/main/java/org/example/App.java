package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, TemplateException {
        // 生成配置
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDirectoryForTemplateLoading(new File("./templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        // 指定数据
        Map root = new HashMap();
        root.put("fruits", new String[]{});
        root.put("flag", false);
        // 指定模版
        Template temp = cfg.getTemplate("demo.ftl");

        // 打开数据保存的文件流
        try  (OutputStream fileOut = new FileOutputStream("reports/demo.html");
              Writer out = new OutputStreamWriter(fileOut)) {
            // 融合数据与模版，并输出到指定流
            temp.process(root, out);
        }


    }
}
