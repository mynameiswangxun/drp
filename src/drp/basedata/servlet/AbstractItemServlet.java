package drp.basedata.servlet;

import drp.basedata.manager.ItemManager;
import drp.util.factory.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "AbstractItemServlet")
public abstract class AbstractItemServlet extends HttpServlet {
    protected ItemManager itemManager;

    @Override
    public void init() throws ServletException {
        super.init();
        BeanFactory beanFactory = (BeanFactory)getServletContext().getAttribute("BeanFactory");
//        itemManager = (ItemManager) beanFactory.getBean("itemManager");
//        itemManager = new ItemManagerImpl();
        itemManager = (ItemManager) beanFactory.getServiceObject(ItemManager.class);
    }
}
