package drp.basedata.servlet;

import drp.basedata.domain.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowItemDetailServlet",urlPatterns = "/basedata/ShowItemDetailServlet.servlet")
public class ShowItemDetailServlet extends AbstractItemServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemId = request.getParameter("itemId");

        Item item = itemManager.findItemById(itemId);

        request.setAttribute("item",item);

        request.getRequestDispatcher("item_detail.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
