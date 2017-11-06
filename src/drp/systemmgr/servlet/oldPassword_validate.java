package drp.systemmgr.servlet;

import drp.systemmgr.domain.User;
import drp.systemmgr.manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "oldPassword_validate.servlet",urlPatterns = "/sysmgr/oldPassword_validate.servlet")
public class oldPassword_validate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserManager userManager = UserManager.getInstance();
        String password = userManager.getPasswordById(((User)request.getSession().getAttribute("user_info")).getId());
        response.setCharacterEncoding("utf-8");
        if(!password.equals(request.getParameter("password"))){
            response.getWriter().print("密码错误，请重新输入");
        }else{
            response.getWriter().print("");
        }
    }
}
