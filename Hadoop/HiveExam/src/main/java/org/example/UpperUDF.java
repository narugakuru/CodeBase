package org.example;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 用户定义函数(user-defined function)UDF
 * 1 对 1 （1进1出）
 * Step 1：继承org.apache.hadoop.hive.ql.exec.UDF类(Deprecated)
 * */
public class UpperUDF extends UDF {

    /**
     *  Step 2:编写名为'evaluate'的方法。
     *  1.方法可重载
     *  2.返回值不能声明为Void
     *  3.可返回null
     * */
    public String evaluate(String word) {
        if (word == null) {
            return null;
        }
        return "$" + word.toUpperCase() + "$";
    }

    /**
     * Step 3:使用Maven package生成JAR包
     * Step 4:JAR上传至Hive Server或HDFS
     * Step 5:注册JAR包到Hive，并创建函数别名
     * -- 创建临时（会话）函数（Temporary Function）
     * hive> add jar <JAR路径>
     * hive> create temporary function <SQL中函数名> as '包名.类名';
     * -- 创建永久函数（Permanent Functions）
     * hive> create function <SQL中函数名> as '包名.类名' using jar '<JAR路径>';
     *
     * Step 6:在SQL语句中使用<SQL中函数名>
     * */

    public String evaluate(String word, String defVal) {
        if (word == null) {
            return evaluate(defVal);
        }
        return "$" + word.toUpperCase() + "$";
    }
}
