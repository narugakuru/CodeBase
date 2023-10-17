package example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * @author jzg
 *
 * Hadoop官方Word Count 示例1.0
 * [官方JAVA API Doc] <a href="https://hadoop.apache.org/docs/r3.3.4/api/index.html">...</a>
 */
public class WordCount {

    // TokenizerMapper是MapReduce程序Mapper主类
    // Mapper是将输入键/值对映射到一组中间键/值对，输入对可以映射到零个或多个输出对
    // 继承org.apache.hadoop.mapreduce.Mapper类
    // org.apache.hadoop.mapreduce.Mapper类是泛型类，需要指定4个类型，分别为
    // 输入键key类型， 输入值value类型，输出键key类型，输出值value类型
    public static class TokenizerMapper
            extends Mapper<Object, Text, Text, IntWritable>{
        // 每个英文单词初始权重，Int 1。
        // IntWritable类为Hadoop MapReduce计算框架中Int类型
        private final static IntWritable one = new IntWritable(1);

        // Text类为Hadoop MapReduce计算框架中String类型
        private Text word = new Text();

        // map方法由InputFormat调用，默认为org.apache.hadoop.mapreduce.lib.input.TextInputFormat，可修改
        // TextInputFormat读取文本文件中的一行，字节偏移量作为key，一行文本内容作为value
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            // StringTokenizer类用于分隔字符串
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                // map输出映射结果
                context.write(word, one);
            }
        }
    }

    // IntSumReducer是MapReduce程序Reducer主类
    // Reducer是对Mapper输出键值对做规约处理，Reducer会把Mapper输出结果中相同键的数据规约为一组数据来处理。
    // 继承org.apache.hadoop.mapreduce.Reducer类
    // org.apache.hadoop.mapreduce.Reducer类是泛型类，需要指定4个类型，分别为
    // 输入键key类型， 输入值values集合中元素的类型，输出键key类型，输出值value类型
    // 其中输入键key类型， 输入值values集合中元素的类型与之前对接Mapper输出键key类型，输出值value类型必须一致
    // 而Reducer输出通常保存到文件中，或作为Combiner做Mapper局部规约处理
    public static class IntSumReducer
            extends Reducer<Text,IntWritable,Text,IntWritable> {
        private IntWritable result = new IntWritable();

        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context
        ) throws IOException, InterruptedException {
            // 累加技术结果
            int sum = 0;
            // 循环处理同一个Key的输入值集合
            for (IntWritable val : values) {
                sum += val.get();
            }

            // 设置输出结果
            result.set(sum);
            // 以KV对的形式写入到输出流
            context.write(key, result);
        }
    }

    public static class MyCombiner
            extends Reducer<Text,IntWritable,Text,IntWritable> {
        private IntWritable result = new IntWritable();


        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context
        ) throws IOException, InterruptedException {
            // 累加技术结果
            int sum = 0;
            // 循环处理同一个Key的输入值集合
            for (IntWritable val : values) {
                sum += val.get();
            }

            // 设置输出结果
            result.set(sum);
            // 以KV对的形式写入到输出流
            context.write(key, result);
        }
    }


    // MapReduce程序Driver类，代表程序Enter Point（入口点）
    public static void main(String[] args) throws Exception {
        /*
         * Step 0 初始化MapReduce环境
         * */
        // 创建Job（作业）执行环境的配置对象，可通过该对象与Yarn进行计算资源协调和设置
        Configuration conf = new Configuration();

        // 创建一个新MapReduce Job（作业），并指定作业名称
        Job job = Job.getInstance(conf, "My Word Count");
        // 绑定Job（作业）启动类
        job.setJarByClass(WordCount.class);

        /*
         * Step 1 设置输入格式处理器
         * */
        // A.PlainText文件处理器(默认，可省略)
        // job.setInputFormatClass(TextInputFormat.class);

        // B.合并小文件的处理器
        // job.setInputFormatClass(CombineTextInputFormat.class);
        // 设置合并小文件的上限
        // CombineTextInputFormat.setMaxInputSplitSize(job, 32 * 1024 * 1024);

        // C.KV格式输入处理器（默认以Tab分割一行文本数据，Tab前为Key，Tab后为Value）
        // job.setInputFormatClass(KeyValueTextInputFormat.class);

        /*
         * Step 2 设置Map阶段
         * */
        // 设置map（映射）处理程序
        job.setMapperClass(TokenizerMapper.class);
        // 指定Map阶段combiner（合并）处理程序【局部】
        job.setCombinerClass(MyCombiner.class);
        //
        //job.setPartitionerClass(MyPartitioner.class);

        /*
         * Step 3 设置Reduce阶段
         * */
        // 设置reduce（规约）处理程序【整体】
        job.setReducerClass(IntSumReducer.class);
        // 指定MapReduce程序输出数据的KEY(键)
        job.setOutputKeyClass(Text.class);
        // 指定MapReduce程序输出数据的VALUE(值)
        job.setOutputValueClass(IntWritable.class);
        // 指定自定义输出文件格式
        // job.setOutputFormatClass(XMLTextOutputFormat.class);

        /*
         * Step 4 设置MapReduce程序需处理输入输出数据位置
         * bin/hadoop jar /root/MapReduce-1.0-SNAPSHOT.jar wordcount /datasets/email_log.txt /output
         * */
        // 为Job（作业）指定输入文件的位置
        FileInputFormat.addInputPath(job, new Path(args[0]));
        // 为Job（作业）指定输出文件的位置
        FileOutputFormat.setOutputPath(job, new Path(args[1] + new Date().getTime()));

        /*
         * Step 5 启动MapReduce程序
         * */
        // 等待MapReduce Job运行完成
        System.exit( job.waitForCompletion(false) ? 0 : 1);
    }

}
