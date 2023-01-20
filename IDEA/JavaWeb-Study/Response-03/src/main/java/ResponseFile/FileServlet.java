package ResponseFile;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

public class FileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1. 要获取下载文件的路径,及其输入流
        String realPath = "D:\\Code\\JavaWeb-1-Maven\\Response-01\\target\\Response-01\\WEB-INF\\classes\\ME.png";
        System.out.println("下载文件的路劲:"+realPath);
        FileInputStream is = new FileInputStream (realPath);
//        2. 下载的文件名是啥？
        String fileName = realPath.substring(realPath.lastIndexOf("\\")+1);
//        3. 设置想办法让浏览器能够支持下载我们需要的东西
        resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
//        4. 创建内存缓冲区
        int len=0;
        byte[] buffer =  new byte[1024];
//        5. 获取OutputStream对象，用于输出流到客户端
        ServletOutputStream out=resp.getOutputStream();
//        6. 将FileOutputStream流写入到buffer缓冲区,使用OutputStream将缓冲区中的数据输出到客户端！
        while ((len=is.read(buffer))>0){
            out.write(buffer,0,len);//使用OutputStream将缓冲区中的数据输出到客户端！
        }
        out.close();
        is.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
