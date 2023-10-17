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
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

public class AccessSort {
    public static class MyMap extends Mapper<Object, Text, IntWritable, Text> {
        public void map(Object key, Text value, Context context)
                throws IOException, InterruptedException {

            //指定tab为分隔符
            String arr[] = value.toString().split("\t");
            //key：统计结果， value：日期
            context.write(new IntWritable(Integer.parseInt(arr[1])),
                    new Text(arr[0]));
        }
    }

    public static class MyReduce extends Reducer<IntWritable, Text, Text, IntWritable> {
        public void reduce(IntWritable key, Iterable<Text> values,
                           Context context)
                throws IOException, InterruptedException {
            //reduce默认会根据key排序，所以需要将key转化为int
            for (Text value : values) {
                context.write(value, key);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args)
                .getRemainingArgs();
        if (otherArgs.length < 2) {
            System.err.println("必须输入读取文件路径和输出路径");
            System.exit(2);
        }
        Job job = Job.getInstance(conf, "Visits Sort");
        job.setJarByClass(AccessSort.class);
        job.setMapperClass(MyMap.class);
        job.setReducerClass(MyReduce.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        for (int i = 0; i < otherArgs.length - 1; ++i) {
            FileInputFormat.addInputPath(job, new Path(otherArgs[i]));
        }
        FileOutputFormat.setOutputPath(job,
                new Path(otherArgs[otherArgs.length - 1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
