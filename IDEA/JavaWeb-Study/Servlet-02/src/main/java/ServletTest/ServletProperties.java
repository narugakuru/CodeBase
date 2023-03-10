package ServletTest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServletProperties extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        InputStream asStream = this.getServletContext().getResourceAsStream("/WEB-INF/classes/db.properties");
        //进入了ServletProperties
        Properties prop = new Properties();
        prop.load(asStream);
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        resp.getWriter().print(username+":"+password);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
