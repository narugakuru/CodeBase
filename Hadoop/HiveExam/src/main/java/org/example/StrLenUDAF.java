package org.example;

import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.udf.generic.AbstractGenericUDAFResolver;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFParameterInfo;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;

import java.util.ArrayList;
import java.util.List;

public class StrLenUDAF extends AbstractGenericUDAFResolver {

    @Override
    public GenericUDAFEvaluator getEvaluator(GenericUDAFParameterInfo info) throws SemanticException {
        TypeInfo[] parameters = info.getParameters();
        // 参数个数检查
        if (parameters.length != 1) {
            throw new UDFArgumentTypeException(parameters.length - 1, "必须仅指定一个参数");
        }
        // 验证参数是否为基础类型
        if (parameters[0].getCategory() != ObjectInspector.Category.PRIMITIVE) {
            throw new UDFArgumentTypeException(0,
                    "仅使用基本数据类型，但" + parameters[0].getTypeName() + "被传递到参数");
        }
        // 验证参数是否为String
        if( ((PrimitiveTypeInfo) parameters[0]).getPrimitiveCategory()
                != PrimitiveObjectInspector.PrimitiveCategory.STRING) {
            throw new UDFArgumentTypeException(0,
                    "仅接受String类型数据，但" + parameters[0].getTypeName() + "被传递到参数");
        }
        return new StrLenEvaluator();
    }

    public static  class StrLenEvaluator extends GenericUDAFEvaluator {

        static class MyAggregationBuffer extends AbstractAggregationBuffer {
            Integer counter;
        }


        @Override
        public ObjectInspector init(Mode m, ObjectInspector[] parameters) throws HiveException {
            // 初始化输入和输出参数
            return super.init(m, parameters);
        }

        public AggregationBuffer getNewAggregationBuffer() throws HiveException {
            // 创建中间结果Buffer
            MyAggregationBuffer buffer = new MyAggregationBuffer();
            reset(buffer);
            return buffer;
        }

        public void reset(AggregationBuffer agg) throws HiveException {
            // 重置中间结果Buffer
            ((MyAggregationBuffer) agg).counter = new Integer(0);
        }

        public void iterate(AggregationBuffer agg, Object[] parameters) throws HiveException {
            // 迭代输入原始数据
            if(parameters == null || parameters.length != 1 || parameters[0] == null) {
                return;
            }
            int count = parameters[0].toString().length() + ((MyAggregationBuffer) agg).counter;
            ((MyAggregationBuffer) agg).counter = count;
        }

        public Object terminatePartial(AggregationBuffer agg) throws HiveException {
            // 输出部分聚合结果
            Integer ret = ((MyAggregationBuffer) agg).counter;
            return ret;
        }

        public void merge(AggregationBuffer agg, Object partial) throws HiveException {
            // 合并部分聚合结果
            int part = PrimitiveObjectInspectorFactory.javaIntObjectInspector.get(partial);
            int count = part + ((MyAggregationBuffer) agg).counter;
            ((MyAggregationBuffer) agg).counter = count;
        }

        public Object terminate(AggregationBuffer agg) throws HiveException {
            // 输出最终聚合结果
            return ((MyAggregationBuffer) agg).counter;
        }
    }

}
