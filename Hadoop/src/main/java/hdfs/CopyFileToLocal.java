package hdfs;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
public class CopyFileToLocal {
    public static void main(String[] args) throws Exception {
        System.setProperty("HADOOP_USER_NAME", "root");
        Configuration conf=new Configuration();
        conf.set("fs.defaultFS", "hdfs://master:9000");
// 设置一个文件目录的路径
        String dfspath = "hdfs://master:9000/experiment/tmp/math.txt";
//设置一个目录路径
        Path source = new Path(dfspath);
//创建文件系统实例
        FileSystem fs = source.getFileSystem(conf);
        String dest="/root/experiment/output/math.txt";
        Path destpath=new Path(dest);
        fs.copyToLocalFile(source,destpath);
    }
}
