package drp.basedata.servlet;

import drp.basedata.manager.ItemManager;
import drp.basedata.manager.ItemManagerImpl;
import drp.util.exception.ApplicationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "DeleteItemServlet",urlPatterns = "/basedata/DeleteItemServlet.servlet")
public class DeleteItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] itemIds = request.getParameterValues("selectFlag");
        ItemManager itemManager = new ItemManagerImpl();
        String message = null;
        try {
            itemManager.deleteItem(itemIds);
        }catch (ApplicationException ape){
            ape.printStackTrace();
            message = ape.getMessage();
        }
        if(message==null){
            message = "删除物料成功";
        }

        response.sendRedirect(request.getContextPath()+"/basedata/SearchItemServlet.servlet?message="+ URLEncoder.encode(message,"utf-8"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
