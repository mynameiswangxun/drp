package drp.flowlist.servlet;

import drp.basedata.domain.AimClient;
import drp.flowlist.domain.FlowDetail;
import drp.flowlist.domain.FlowList;
import drp.basedata.domain.*;
import drp.flowlist.manager.FlowListManager;
import drp.util.factory.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "FlowListServlet", urlPatterns = "/flowlist/FlowListServlet.servlet")
public class FlowListServlet extends BaseServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //显示调用父类的service方法
        super.service(request, response);
        if ("add".equals(getCommand())) {
            addFlowList(request,response);
        } else if("showAdd".equals(getCommand())){
            showAdd(request,response);
        }
    }

    /**
     * 添加流向单
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void addFlowList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FlowList flowList = new FlowList();
        Client client = new Client();
        client.setId(Integer.parseInt(request.getParameter("clientId")));
        flowList.setClient(client);
        flowList.setOpDate(new Date());
        flowList.setRecorder(getUser());
        flowList.setVouSts("N");
        flowList.setOpType("A");
        //添加会计核算期
        FiscalTime fiscalTime = new FiscalTime();
        fiscalTime.setId(1);
        flowList.setFiscalTime(fiscalTime);
        List<FlowDetail> flowDetails = new ArrayList();

        String[] aimIds = request.getParameterValues("aimId");
        String[] itemIds = request.getParameterValues("itemNo");
        String[] qtys = request.getParameterValues("qty");

        for(int i = 0; i<aimIds.length; i++){
            FlowDetail flowDetail = new FlowDetail();
            Item item = new Item();
            item.setItemId(itemIds[i]);
            flowDetail.setItem(item);

            //设置操作数量
            flowDetail.setOpNum(new BigDecimal(qtys[i]));

            AimClient aimClient = new AimClient();
            aimClient.setClientID(aimIds[i]);
            flowDetail.setAimClient(aimClient);

            flowDetails.add(flowDetail);
        }
        flowList.setFlowDetails(flowDetails);

        FlowListManager flowListManager = (FlowListManager) BeanFactory.getInstance().getServiceObject(FlowListManager.class);
        flowListManager.addFlowList(flowList);

        response.sendRedirect("flow_card_add.jsp");
    }

    /**
     * 展示添加页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void showAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("flow_card_add.jsp").forward(request,response);
    }
}