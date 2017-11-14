package drp.basedata.servlet;

import drp.basedata.domain.Item;
import drp.util.datadict.domain.ItemCategory;
import drp.util.datadict.domain.ItemUnit;
import drp.util.datadict.manager.DataDictManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowModifyItemServlet",urlPatterns = "/basedata/ShowModifyItemServlet.servlet")
public class ShowModifyItemServlet extends AbstractItemServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //根据id查找item
        Item item = itemManager.findItemById(request.getParameter("itemId"));
        request.setAttribute("item",item);

        //获得物料类别列表
        List<ItemCategory> categories = DataDictManager.getInstance().findItemCategoryList();
        request.setAttribute("categories",categories);

        //获得物料单位列表
        List<ItemUnit> units = DataDictManager.getInstance().findItemUnitList();
        request.setAttribute("units",units);

        //转发给jsp
        request.getRequestDispatcher("/basedata/item_modify.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
