package drp.basedata.servlet;

import drp.basedata.domain.Item;
import drp.basedata.manager.ItemManager;
import drp.basedata.manager.ItemManagerImpl;
import drp.util.datadict.domain.ItemCategory;
import drp.util.datadict.domain.ItemUnit;
import drp.util.datadict.manager.DataDictManager;
import drp.util.exception.ApplicationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "ModifyItemServlet",urlPatterns = "/basedata/ModifyItemServlet.servlet")
public class ModifyItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String itemId = request.getParameter("itemNo");
        String itemName = request.getParameter("itemName");
        String itemSpec = request.getParameter("spec");
        String itemPattern = request.getParameter("pattern");
        String itemCategoryId = request.getParameter("category");
        String itemUnitId = request.getParameter("unit");

        ItemCategory itemCategory = (ItemCategory) DataDictManager.getInstance().findAbstractDataDictById(itemCategoryId);
        ItemUnit itemUnit = (ItemUnit) DataDictManager.getInstance().findAbstractDataDictById(itemUnitId);

        Item item = new Item();
        item.setItemId(itemId);
        item.setItemName(itemName);
        item.setSpec(itemSpec);
        item.setItemPattern(itemPattern);
        item.setItemUnit(itemUnit);
        item.setItemCategory(itemCategory);

        String message = null;
        try{
            ItemManager itemManager = new ItemManagerImpl();
            itemManager.modifyItem(item);
        } catch (ApplicationException ape){
            ape.printStackTrace();
            message = ape.getMessage();

        }
        if(message == null){

            message = "修改物料成功!";
        }
        response.sendRedirect(request.getContextPath()+"/basedata/ShowModifyItemServlet.servlet?message="
                + URLEncoder.encode(message,"utf-8")+"&itemId="+itemId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
