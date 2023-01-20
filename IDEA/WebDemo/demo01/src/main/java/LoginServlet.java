import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class LoginServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        String namespace = config.getInitParameter("namespace");
        config.getServletContext().setAttribute("namespace", namespace);

        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

//        System.out.println("已进入login" + username + ":" + password);


        //getServletPath
        System.out.println(req.getServletPath());
        // 当前工程路径
        System.out.println(super.getServletContext().getContextPath());
// 工程部署的绝对路径
        System.out.println(super.getServletContext().getRealPath("/"));
// 获取请求的资源路径
        System.out.println(req.getRequestURI());
        // 得到工程路径
        System.out.println(req.getContextPath());



        resp.sendRedirect("/");
        System.out.println("重定向！！");

//        登录成功，则把username存入Session
        if (username != null && "123".equals(password)) {
            req.getSession().setAttribute("username", username);
            req.getRequestDispatcher("/main.jsp").forward(req, resp);
        }
        System.out.println("转发结束");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
