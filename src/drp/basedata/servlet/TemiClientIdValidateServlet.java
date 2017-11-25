package drp.basedata.servlet;

import drp.basedata.domain.TemiClient;
import drp.basedata.manager.TemiClientManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TemiClientIdValidateServlet",urlPatterns = "/basedata/TemiClientIdValidateServlet.servlet")
public class TemiClientIdValidateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        String message = "";
        if(new TemiClientManager().isExistTemiClientByTemiClientId(request.getParameter("TemiClientId"))){
            message = "该终端客户id已经存在，请重新输入!";
        }
        response.getWriter().print(message);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
