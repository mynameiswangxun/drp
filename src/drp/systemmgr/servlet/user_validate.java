package drp.systemmgr.servlet;

import drp.systemmgr.manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "user_validate",urlPatterns = "/user_validate.servlet")
public class user_validate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        response.setCharacterEncoding("utf-8");
        if(UserManager.getInstance().findUserById(userId)!=null){
            response.getWriter().print("用户ID已经存在");
        }else{
            response.getWriter().print("");
        }
    }
}
