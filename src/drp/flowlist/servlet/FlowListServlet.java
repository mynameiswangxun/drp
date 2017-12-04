package drp.flowlist.servlet;

import drp.basedata.domain.AimClient;
import drp.basedata.manager.ClientManager;
import drp.flowlist.domain.FlowDetail;
import drp.flowlist.domain.FlowList;
import drp.basedata.domain.*;
import drp.flowlist.manager.FlowListManager;
import drp.util.factory.BeanFactory;
import drp.util.pagemodel.PageModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "FlowListServlet", urlPatterns = "/flowlist/FlowListServlet.servlet")
public class FlowListServlet extends BaseServlet {
    private FlowListManager flowListManager;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //显示调用父类的service方法
        super.service(request, response);
        if ("add".equals(getCommand())) {
            addFlowList(request,response);
        } else if("showAdd".equals(getCommand())){
            showAdd(request,response);
        } else if("search".equals(getCommand())){
            search(request,response);
        }else if("delete".equals(getCommand())){
            delete(request,response);
        }else if("audit".equals(getCommand())){
            audit(request,response);
        }else{
            search(request,response);
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        flowListManager =  (FlowListManager) BeanFactory.getInstance().getServiceObject(FlowListManager.class);
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
        client.setId(Integer.parseInt(request.getParameter("cid")));
        flowList.setClient(client);
        flowList.setOpDate(new Date());
        flowList.setRecorder(getUser());
        flowList.setVouSts("N");
        flowList.setOpType("A");
        //添加会计核算期
        FiscalTime fiscalTime = new FiscalTime();
        fiscalTime.setId(Integer.parseInt(request.getParameter("fiscalId")));
        flowList.setFiscalTime(fiscalTime);

        List<FlowDetail> flowDetails = new ArrayList();

        String[] aimIds = request.getParameterValues("aimInnerId");
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
            aimClient.setId(Integer.parseInt(aimIds[i]));
            flowDetail.setAimClient(aimClient);

            flowDetail.setAdjustFlag("N");

            flowDetails.add(flowDetail);
        }
        flowList.setFlowDetails(flowDetails);

//        FlowListManager flowListManager = (FlowListManager) BeanFactory.getInstance().getServiceObject(FlowListManager.class);
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

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //给各参数设定默认值
        int pageNo = 1;
        int pageSize = 6;
        String cid = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        try {
            beginDate = simpleDateFormat.parse("2007-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = null;
        try {
            endDate = simpleDateFormat.parse("2027-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(request.getParameter("pageNo")!=null && !"".equals(request.getParameter("pageNo"))){
            pageNo = Integer.parseInt(request.getParameter("pageNo"));
        }
        if(request.getParameter("cid")!=null && !"".equals(request.getParameter("cid"))){
            cid = request.getParameter("cid");
        }
        if(request.getParameter("beginDate")!=null && !"".equals(request.getParameter("beginDate"))){
            try {
                beginDate = simpleDateFormat.parse(request.getParameter("beginDate"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(request.getParameter("endDate")!=null && !"".equals(request.getParameter("endDate"))){
            try {
                endDate = simpleDateFormat.parse(request.getParameter("endDate"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        PageModel<FlowList> pageModel = flowListManager.findFlowLists(pageNo,pageSize,cid,beginDate,endDate);

        request.setAttribute("pageModel",pageModel);
        request.setAttribute("client", "".equals(cid)? null:ClientManager.getInstance().findClientOrAreaById(Integer.parseInt(cid)));
        request.setAttribute("beginDate",beginDate);
        request.setAttribute("endDate",endDate);

        request.getRequestDispatcher("flow_card_maint.jsp").forward(request,response);
    }

    /**
     * 删除流向单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String[] flowNums = request.getParameterValues("selectFlag");

        flowListManager.delFlowList(flowNums);

        response.sendRedirect("FlowListServlet.servlet");
    }

    /**
     * 送审流向单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void audit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] flowNums = request.getParameterValues("selectFlag");

        flowListManager.auditFlowList(flowNums);

        response.sendRedirect("FlowListServlet.servlet");
    }
}
