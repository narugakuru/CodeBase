import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.*;

public class OnlineListener implements HttpSessionListener, ServletContextListener, HttpSessionAttributeListener {

    private ServletContext application = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 容器初始化时，向application中存放一个空的容器
        this.application = sce.getServletContext();
        this.application.setAttribute("alluser", new HashSet<>());//用户名列表
        this.application.setAttribute("allmap", new HashMap<>());//Session映射至用户名
        String s = "\n";
        this.application.setAttribute("chats", s);
        System.out.println("监听器初始化完成");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        // Session新增值,即登录时，将新增的用户名保存在列表之中
        Set alluser = (Set) this.application.getAttribute("alluser");
        alluser.add(se.getValue());//获取新加入Session的值
        this.application.setAttribute("alluser", alluser);
        // Session映射至用户名
        Map map = (Map) this.application.getAttribute("allmap");
        map.put(se.getValue(), se.getSession());
        this.application.setAttribute("allmap", map);

        //初始化Session中的privateChat，避免本函数被二次调用
        String s = "\n";
        se.getSession().setAttribute("privateChat", s);
        se.getSession().setAttribute("chatRecord", s);

//        System.out.println("OnlineListener:attributeAdded:alluser===>" + alluser.toString());
//        System.out.println("OnlineListener:attributeAdded:map===>" + map.toString());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        //值被替换时
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //用户进入网站界面，什么也不做
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // 销毁Session时,将用户名称从列表中删除
        Set alluser = (Set) this.application.getAttribute("alluser");
        String user = (String) se.getSession().getAttribute("user");
        alluser.remove(user);
        this.application.setAttribute("alluser", alluser);

        Map map = (Map) this.application.getAttribute("allmap");
        map.remove(user);
        this.application.setAttribute("allmap", map);
//        System.out.println("OnlineListener:sessionDestroyed:alluser===>" + alluser.toString());
//        System.out.println("OnlineListener:sessionDestroyed:map===>" + map.toString());

    }
}
