package drp.util.servlet;


import drp.util.factory.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * 负责系统在服务器启动时初始化
 */
@WebServlet(name = "InitServlet",urlPatterns = "/InitServlet.servlet",loadOnStartup = 1)
public class InitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();

        //将工厂设置到ServletContext中（Application）
        getServletContext().setAttribute("BeanFactory",BeanFactory.getInstance());

        System.out.println("InitServlet init");
    }
}
