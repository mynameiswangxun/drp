package drp.basedata.servlet;

import drp.basedata.domain.Item;
import drp.basedata.manager.ItemManager;
import drp.basedata.manager.ItemManagerImpl;
import drp.util.pagemodel.PageModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SearchItemServlet",urlPatterns = "/basedata/SearchItemServlet.servlet",
        initParams = @WebInitParam(name="pageSize",value = "6"))
public class SearchItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int pageNo = 1;
        int pageSize = Integer.parseInt(this.getInitParameter("pageSize"));

        //取得页码
        String pageNoString = request.getParameter("pageNo");
        if(pageNoString!=null && !"".equals(pageNoString)){
            pageNo = Integer.parseInt(pageNoString);
        }

        //取得条件
        String condition  = request.getParameter("condition");
        if(condition==null){
            condition = "";
        }

        ItemManager itemManager = new ItemManagerImpl();
        PageModel<Item> pageModel = itemManager.findItemList(pageNo,pageSize,condition);

        request.setAttribute("PageModel",pageModel);
        request.getRequestDispatcher("/basedata/item_maint.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
