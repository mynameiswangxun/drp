package drp.flowlist.servlet;

import drp.systemmgr.domain.User;
import drp.util.factory.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {
    private String command;
    private User user;
    private BeanFactory beanFactory = BeanFactory.getInstance();
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       command = request.getParameter("command");
       user = (User) request.getAttribute("");

    }
    protected String getCommand(){
        return command;
    }
    protected User getUser(){
        return user;
    }
    protected BeanFactory getBeanFactory(){
        return beanFactory;
    }
}
