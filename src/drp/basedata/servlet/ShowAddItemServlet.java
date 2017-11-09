package drp.basedata.servlet;

import drp.util.datadict.domain.ItemCategory;
import drp.util.datadict.domain.ItemUnit;
import drp.util.datadict.manager.DataDictManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowAddItemServlet",urlPatterns = "/basedata/ShowAddItemServlet.servlet")
public class ShowAddItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataDictManager dataDictManager = DataDictManager.getInstance();
        //取得物料列表
        List<ItemCategory> categories = dataDictManager.findItemCategoryList();
        //取得单位列表
        List<ItemUnit> units = dataDictManager.findItemUnitList();

        //保存到request中
        request.setAttribute("categories",categories);
        request.setAttribute("units",units);

        //转发
        request.getRequestDispatcher("/basedata/item_add.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
