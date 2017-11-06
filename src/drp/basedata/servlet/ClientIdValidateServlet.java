package drp.basedata.servlet;

import drp.basedata.manager.ClientManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ClientIdValidateServlet",urlPatterns = "/basedata/ClientIdValidateServlet.servlet")
public class ClientIdValidateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClientManager clientManager = ClientManager.getInstance();
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        if(clientManager.isExistClientByClientId(request.getParameter("clientId"))){
            response.getWriter().print("分销商id已经存在");
        } else {
            response.getWriter().print("");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
