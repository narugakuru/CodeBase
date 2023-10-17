package TCP.Exam05Text;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable {
    private Socket s;
    public ServerThread(Socket s) {
        this.s=s;
    }

    @Override
    public void run() {

        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
//            BufferedWriter bw=new BufferedWriter(new FileWriter("ProjectExam03Net\\src\\TCP\\Exam05Text\\new.text"));
            int count=0;
            File file=new File("D:\\Code\\IdeaProjects\\Project01\\ProjectExam03Net\\src\\TCP\\Exam05Text\\new"+count+".text");
            while (file.exists()){
                count++;
                file =new File("D:\\Code\\IdeaProjects\\Project01\\ProjectExam03Net\\src\\TCP\\Exam05Text\\new"+count+".text");
            }
            BufferedWriter bw=new BufferedWriter(new FileWriter(file));

//            in-file
            String line;
            while((line=br.readLine())!=null){
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
//couple back
            BufferedWriter bwServer=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            bwServer.write("file upload successful");
//            bwServer.newLine();//可有可无？
            bwServer.flush();
//
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
