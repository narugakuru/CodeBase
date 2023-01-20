import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class mkdirDemo {
    public static void main(String[] args) throws IOException {
        System.out.println("hello world");
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://192.168.126.2:9000");
//        conf.set("fs.defaultFS", "hdfs://你的云服务器的IP:19000或hadoop-node1节点的IP:9000");
        FileSystem hdfs = FileSystem.get(conf);
        boolean is_success = hdfs.mkdirs(new Path("/helloByJava"));
        if (is_success) {
            System.out.println("success");
        } else {
            System.out.println("failure");
        }
        hdfs.close();
    }
}
