package drp.basedata.servlet;

import drp.basedata.domain.Item;
import drp.basedata.manager.ItemManager;
import drp.basedata.manager.ItemManagerImpl;
import drp.util.datadict.domain.ItemCategory;
import drp.util.datadict.domain.ItemUnit;
import drp.util.datadict.manager.DataDictManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddItemServlet",urlPatterns = "/basedata/AddItemServlet.servlet")
public class AddItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //从request中取得数据
        String itemNo = request.getParameter("itemNo");
        String itemName = request.getParameter("itemName");
        String spec = request.getParameter("spec");
        String pattern = request.getParameter("pattern");
        ItemCategory itemCategory = (ItemCategory) DataDictManager.getInstance().findAbstractDataDictById(request.getParameter("category"));
        ItemUnit itemUnit = (ItemUnit) DataDictManager.getInstance().findAbstractDataDictById(request.getParameter("unit"));

        //构造Item
        Item item = new Item();
        item.setItemId(itemNo);
        item.setItemName(itemName);
        item.setSpec(spec);
        item.setItemPattern(pattern);
        item.setItemCategory(itemCategory);
        item.setItemUnit(itemUnit);

        //向数据库添加Item
        ItemManager itemManager = new ItemManagerImpl();
        itemManager.addItem(item);

        //重定向
        response.sendRedirect("item_maint.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
