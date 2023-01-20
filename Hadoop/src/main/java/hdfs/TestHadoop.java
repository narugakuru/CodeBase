package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TestHadoop {
    /**
     * FileSystem就是对HDFS的抽取和封装
     */
    private FileSystem fileSystem;

    /**
     * 对hdfs核心配置文件的抽取和封装，如：cores-site.xml,hdfs-site.xml
     */
    Configuration conf;
    /**
     * 进行初始化
     */
    @Before
    public void init() {
        //对全局变量进行初始化
        conf = new Configuration();
        try {
            //URI: uniform resource indentifer, 统一资源标识符，如：hdfs,http,ftp等等协议所标注的远程资源
            //URL: uniform resource locator, 统一资源定位符，一般指的是http所标识的网络资源，如：https://fanyi.baidu.com/#en/zh/artifact
            //URL是URI的子集
            System.setProperty("hadoop.home.dir", "D:/Environment/hadoop-common-2.2.0-bin-master/");
            URI uri = new URI("hdfs://192.168.126.2:9000/");
            fileSystem = FileSystem.get(uri,conf);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    /**
     * 测试环境→ 用来验证java客户端能够正常连接远程的hadoop分布式集群
     *
     * [root@master ~]# hdfs dfs -cat /input/a.txt
     * hello you
     *
     * hello kitty
     *
     * are you ok
     * 最近可好
     * 感觉如何？
     * [root@master ~]#
     */
    @Test
    public void testEnv() {
        System.out.println("fileSystem = " + fileSystem);
        System.setProperty("hadoop.home.dir", "D:/Environment/hadoop-common-2.2.0-bin-master/");
        try (FSDataInputStream in = fileSystem.open(new Path("hdfs://192.168.126.2:9000/user/root/input"))) {
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = in.read(bytes)) != -1) {
                String content = new String(bytes, 0, len);
                System.out.print(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 进行资源释放
     */
    @After
    public void releaseResource() {
        if (fileSystem != null) {
            try {
                fileSystem.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
