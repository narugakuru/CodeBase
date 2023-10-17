package org.example;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户定义表生成函数(User-Defined Table Generating Function) UDTF
 * 1 对 多 （1进N出）
 * Step 1：继承org.apache.hadoop.hive.ql.udf.generic.GenericUDTF类
 * */
public class UpperLowerGenericUDTF extends GenericUDTF {

    // 处理数据行数计数器
    private Integer count = Integer.valueOf(0);

    /**
     * 初始化GenericUDTF实例，每个实例仅执行一次
     * 返回值：表示处理方法返回结果的列名和列数据类型
     * */
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        // 检查业务处理方法入参数据类型
        if (argOIs == null) {
            throw new UDFArgumentException("||||...");
        }

        // 指定业务处理方法返回信息
        ArrayList<String> fieldNames = new ArrayList<String>();
        ArrayList<ObjectInspector> fieldOIs = new ArrayList<ObjectInspector>();
        // 输出结果列名(可被覆盖)
        fieldNames.add("Up&Low1");
        // 输出结果列对应的类型
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldOIs);
    }

    public void process(Object[] args) throws HiveException {
        // 处理数据行计数
        count = Integer.valueOf(count.intValue() + 1);
        // 输出结果
        List<Object> result = new ArrayList<Object>();
        result.add(count.toString());
        // 获取输出数据
        if (args == null || args.length == 0 || args[0] ==null) {
            forward(result);
            return;
        }
        String val =  args[0].toString();
        result.add("$" + val.toUpperCase() + "$");
        result.add("￥" + val.toLowerCase() + "￥");
        // 输出处理结果
        forward(result);
    }

    /**
     * 关闭事件，表示所有输入行已提交处理完成
     * 可在此事件中关闭连接，释放资源等
     * 亦可使用forward() 输出其他信息
     * */
    public void close() throws HiveException {
        // TODO
    }
}
