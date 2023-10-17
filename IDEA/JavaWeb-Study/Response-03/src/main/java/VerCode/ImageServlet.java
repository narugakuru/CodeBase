package VerCode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        设置浏览器刷新
        resp.setHeader("refresh","1");
//      内存中创建图片
        BufferedImage image = new BufferedImage(80,20,BufferedImage.TYPE_INT_RGB);
//      得到图片,设置画笔
        Graphics2D g2 = (Graphics2D) image.getGraphics();
//      设置图片
        g2.setColor(Color.white);
        g2.fillRect(0,0,80,20);
//      给图片写入数据
        g2.setColor(Color.BLUE);
        g2.setFont(new Font(null,Font.BOLD,20));
        g2.drawString(makeNum(),0,20);
//        告诉浏览器，这个请求用图片打开
        resp.setContentType("image/jpeg");
//        网站存在缓存，不然浏览器缓存
        resp.setDateHeader("expires",-1);
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("Pragma","no-cache");
//        把图片给浏览器
        ImageIO.write(image,"jpg",resp.getOutputStream());
    }

    private String makeNum() {
        Random random = new Random();
        String num = "";
        StringBuffer str = new StringBuffer();
        for (int i=0;i<7;i++){
            str.append(random.nextInt(9));
        }
        num += str.toString();
        return num;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
