package org.example;

import org.apache.hadoop.hive.ql.exec.MapredContext;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;

/**
 * 替代org.apache.hadoop.hive.ql.exec.UDF类
 * GenericUDF泛(通用)型类，二者区别
 * 1.GenericUDF可以接受复杂类型的参数，并返回复杂类型。
 * 2.GenericUDF可以接受可变长度的参数。
 * 3.GenericUDF可以接受无限数量的函数签名——例如，很容易编写一个接受数组、数组>等（任意级别的嵌套）的 GenericUDF。
 * 4.可以使用DeferredObject进行短路评估（short-circuit evaluations）。
 * */
public class UpperGenericUDF extends GenericUDF {

    // 第一参数检查器
    private StringObjectInspector valArg;
    // 第二参数检查器
    private StringObjectInspector defArg;

    /**
     * 初始化GenericUDF实例，每个实例仅执行一次，在所有业务处理之前执行
     * 参数是ObjectInspector类型检查器数组，传递业务evaluate的入参类型
     * 返回值ObjectInspector类型检查器，传递业务evaluate的返回值类型
     * 如，参数类型错误，参数长度错误等可抛出异常
     * */
    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
        // 1. 检查该记录是否传过来正确的参数数量
        if (arguments == null || arguments.length == 0 || arguments.length > 2) {
            throw new UDFArgumentLengthException("参数形式:(val String),(val String, default String)");
        }

        // 2. 检查该条记录是否传过来正确的参数类型
        ObjectInspector val = arguments[0];     // 第一个参数
        if (!(val instanceof StringObjectInspector)) {
            throw new UDFArgumentException("第一个参数是String类型");
        }
        if (arguments.length == 2) {
            ObjectInspector def = arguments[1];  // 第二个参数
            if (!(val instanceof StringObjectInspector)) {
                throw new UDFArgumentException("第二个参数是String类型");
            }
            this.defArg = (StringObjectInspector)def;
        }

        // 3. 检查通过后，将参数赋值给成员变量ObjectInspector，为了在evaluate()中使用
        this.valArg = (StringObjectInspector)val;

        // 4. 用工厂类生成用于表示业务方法（evaluate）返回值的ObjectInspector
        return PrimitiveObjectInspectorFactory.javaStringObjectInspector;
    }

    public Object evaluate(DeferredObject[] arguments) throws HiveException {
        if (arguments == null || arguments.length == 0) {
            return null;
        }
        String val = this.valArg.getPrimitiveJavaObject(arguments[0].get());
        String def = null;
        if (arguments.length == 2) {
            def = this.valArg.getPrimitiveJavaObject(arguments[0].get());
        }
        if (val == null) {
            if (def == null) {
                return null;
            } else {
                return "￥" + def.toUpperCase() + "￥";
            }
        }
        return "￥" + val.toUpperCase() + "￥";
    }

    public String getDisplayString(String[] children) {
        return "$Upper$方法显示信息";
    }

    @Override
    public void configure(MapredContext context) {
        // 获取执行MapReduce程序中Context对象
        // 通过Context对象可获得。JobConf，Report，Counter等对象
        super.configure(context);
    }
}
