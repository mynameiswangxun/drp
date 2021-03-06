package drp.basedata.servlet;

import drp.basedata.domain.Item;
import drp.util.datadict.domain.ItemCategory;
import drp.util.datadict.domain.ItemUnit;
import drp.util.datadict.manager.DataDictManager;
import drp.util.exception.ApplicationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "AddItemServlet",urlPatterns = "/basedata/AddItemServlet.servlet")
public class AddItemServlet extends AbstractItemServlet {
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
        String message = null;
        try {
            itemManager.addItem(item);
        }catch (ApplicationException aex){
            aex.printStackTrace();
            message = aex.getMessage();
        }
        if(message==null){
            message = "添加物料成功";
        }
        //重定向
        response.sendRedirect("ShowAddItemServlet.servlet?message="+ URLEncoder.encode(message,"utf-8"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
