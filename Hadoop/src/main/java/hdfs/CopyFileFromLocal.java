package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class CopyFileFromLocal {

    public static void main(String[] args) throws Exception {
        System.setProperty("HADOOP_USER_NAME", "root");
        System.setProperty("hadoop.home.dir", "D:/Environment/hadoop-common-2.2.0-bin-master/");
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://192.168.126.2:9000/user/root");
        FileSystem hdfs = FileSystem.get(conf);
        // 设置一个文件目录的路径
        String dfspath = "hdfs://192.168.126.2:9000/user/root/input";
        //设置一个目录路径
        Path dir = new Path(dfspath);
        //创建文件系统实例
        FileSystem fs = dir.getFileSystem(conf);
        System.out.println("创建实例");
        //创建目录
//        fs.mkdirs(dir);
//        System.out.println("mkdir success!");
        //d.txt文件拷贝到了HDFS上dir指定目录下
        fs.copyFromLocalFile(new Path("192.168.126.2:9000:/root/d.txt"), dir);
    }
}
