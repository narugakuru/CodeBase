import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Test;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class BatUpload {
    private String Name_Node = "hdfs://192.168.1.128:9000";

    @Test
    public void testFileSystem() throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();

        FileSystem fs  =  FileSystem.get( (new URI (Name_Node)), configuration,"root" );
        if (!fs.exists(new Path("/test"))) {
            fs.mkdirs(new Path("/test"));
        }


    }

    @Test
    public void uploadFileSystem() throws URISyntaxException, IOException, InterruptedException {
        String srcPath = "D:/Code/Hadoop/data/";
        Configuration configuration = new Configuration();

        FileSystem fs  =  FileSystem.get( (new URI (Name_Node)), configuration,"root" );

        LocalFileSystem local = FileSystem.getLocal(configuration);
        FileStatus[] listFile = local.globStatus(new Path(srcPath), new RegexPathFilter("*.jpg"));
        System.out.println(listFile.toString());

/*        Path[] paths = FileUtil.stat2Paths(listFile);
        for(Path path : paths) {
            fs.copyFromLocalFile(path, new Path("/test/"));
        }
        */

//        fs.copyFromLocalFile(new Path(srcPath), new Path("/test"));
    }
}
