import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 */
public class BatUpload {
    private final String Name_Node = "hdfs://192.168.1.128:9000";

    @Test
    public void testFileSystem() throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();

        FileSystem fs  =  FileSystem.get( (new URI (Name_Node)), configuration,"root" );
        if (!fs.exists(new Path("/test"))) {
            fs.mkdirs(new Path("/test"));
        }



    }

    /**
     * 上传data文件夹
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void uploadFileSystem() throws URISyntaxException, IOException, InterruptedException {
        String srcPath = "D:/Code/Hadoop/data/";
        String tarPath = Name_Node+"/test";
        Configuration configuration = new Configuration();

        FileSystem fs  =  FileSystem.get( (new URI (Name_Node)), configuration,"root" );
//        LocalFileSystem local = FileSystem.getLocal(configuration);
        fs.copyFromLocalFile(new Path(srcPath), new Path("/test"));
    }

    /**
     * 判断hdfs内的文件，文件夹
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public void judgePath() throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fs  =  FileSystem.get( (new URI (Name_Node)), configuration,"root" );
        // 2 判断是文件还是文件夹
        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));

        for (FileStatus fileStatus : fileStatuses) {
            if (fileStatus.isFile()){
                System.out.println("file"+fileStatus.getPath().getName());
            }else {
                System.out.println("directory"+fileStatus.getPath());
            }
        }
    }
}
