import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;

//私聊,往聊天对象Session里存消息
//群聊，需要一个公共消息存储结构
//@SuppressWarnings("serial")
public class MessageServlet extends HttpServlet {
    public String chatRecord = "\n";  //定义聊天记录变量，此处为全局变量


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String chatName = req.getParameter("chatName");//私聊对象的username
        ServletContext application = this.getServletContext();
        Map allmap = (Map) application.getAttribute("allmap");
//        System.out.println(allmap);
        HttpSession targetSession = (HttpSession) allmap.get(chatName);
//        System.out.println("targetSession===>"+targetSession);
        //私聊对象的username存在
        if (targetSession != null) {
            String privateChat = (String) targetSession.getAttribute("privateChat");
            privateChat = addChatRecord(req, resp, privateChat);//获取处理过的私聊记录
            targetSession.setAttribute("privateChat", privateChat);//加入到目标Session
//            System.out.println("privateChat===>"+privateChat);
        } else {
            //群聊
            this.chatRecord = addChatRecord(req, resp, this.chatRecord);
//            System.out.println("群聊:"+chatRecord);
        }
        req.getSession().setAttribute("chatRecord", this.chatRecord); //将当前聊天输入内容存储
        req.getRequestDispatcher("main.jsp").forward(req, resp);  //跳转到当前聊天输入界面，即界面布局不变
    }

    //在原本的聊天记录上加入新的一条消息
    String addChatRecord(HttpServletRequest req, HttpServletResponse resp, String tmpChat) throws ServletException, IOException {
        Date now = new Date();    //创建日期对象，及系统当前时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");//设置日期格式 yyyy-MM-dd HH:mm:ss
        String time = dateFormat.format(now); //按照给定的日期格式获取系统当前时间
        String inputChat = req.getParameter("inputChat");//获取新输入的消息
//        System.out.println("获取新输入的消息:" + inputChat);
        String username = (String) req.getSession().getAttribute("username");  //获取登陆页面用户名
        tmpChat += "来自" + username + " " + time + " : " + inputChat + "\n";   //加入聊天记录存储
        return tmpChat;
    }
}
