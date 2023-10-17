package TCP;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class readFile {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Project01/ProjectExam03Net/src/TCP/InetAddress.text"));
        String line;
        while ((line=br.readLine())!=null){
            System.out.println(line);
        }
    }

}
