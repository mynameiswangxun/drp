<%@ page import="drp.util.pagemodel.PageModel" %>
<%@ page import="drp.basedata.domain.FiscalTime" %>
<%@ page import="drp.basedata.manager.FiscalTimeManager" %>
<%@ page import="drp.util.factory.BeanFactory" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    int pageNo = 1;
    if(request.getParameter("pageNo")!=null && !"".equals(request.getParameter("pageNo"))){
        pageNo = Integer.parseInt(request.getParameter("pageNo"));
    }
    int pageSize = 6;
    FiscalTimeManager fiscalTimeManager =
            (FiscalTimeManager) BeanFactory.getInstance().getServiceObject(FiscalTimeManager.class);
    PageModel<FiscalTime> pageModel = fiscalTimeManager.findFiscalList(pageNo,pageSize);
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>请选择会计核算期</title>
    <link rel="stylesheet" href="../style/drp.css">
    <script src="../script/client_validate.js"></script>
    <script type="text/javascript">
        function topPage() {
            window.self.location='fiscal_time_select.jsp?pageNo=<%=pageModel.getTopPageNo()%>';
        }

        function previousPage() {
            window.self.location='fiscal_time_select.jsp?pageNo=<%=pageModel.getPreviousPageNo()%>';
        }

        function nextPage() {
            window.self.location='fiscal_time_select.jsp?pageNo=<%=pageModel.getNextPageNo()%>';
        }

        function bottomPage() {
            window.self.location='fiscal_time_select.jsp?pageNo=<%=pageModel.getBottomPageNo()%>';
        }
        function selectOk() {
            var selectFlag = document.getElementsByName("selectFlag");
            var selected;
            for(var i=0;i<selectFlag.length;i++){
                if(selectFlag[i].checked){
                    selected = selectFlag[i];
                }
            }
            var aboutFiscalTime = selected.value.split(",");
            window.opener.document.getElementById("fiscalId").value = aboutFiscalTime[0];
            window.opener.document.getElementById("beginDate").value = aboutFiscalTime[1];
            window.opener.document.getElementById("endDate").value = aboutFiscalTime[2];
            window.close()
        }
    </script>

</head>

<body class="body1">
<form name="aimClientForm">
    <div align="center">
        <table width="95%" border="0" cellspacing="0" cellpadding="0"
               height="34">
            <tr>
                <td width="522" class="p1" height="34" nowrap>
                    <img src="../images/search.gif" width="32" height="32">
                    &nbsp;
                    <b>请选择会计核算期</b>
                </td>
            </tr>
        </table>
        <hr width="100%" align="center" size=0>
        <table width="95%" border="1" cellspacing="0" cellpadding="0"
               align="center" class="table1">
            <tr>
                <td width="79" class="rd6">
                    选择
                </td>
                <td width="123" class="rd6">
                    核算年
                </td>
                <td width="146" class="rd6">
                    核算月
                </td>
                <td width="188" class="rd6">
                    开始日期
                </td>
                <td width="204" class="rd6">
                    结束日期
                </td>
                <td width="172" class="rd6">
                    核算期状态
                </td>
            </tr>
            <%
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                for (FiscalTime fiscalTime:
                        pageModel.getList()) {
            %>
            <tr>
                <td class="rd8">
                    <input type="radio" name="selectFlag" value="<%=fiscalTime.getId()%>,
<%=simpleDateFormat.format(fiscalTime.getBeginDate())%>,<%=simpleDateFormat.format(fiscalTime.getEndDate())%>" ondblclick="selectOk()">
                </td>
                <td class="rd8">
                    <%=fiscalTime.getFiscalYear()%>
                </td>
                <td class="rd8">
                    <%=fiscalTime.getFiscalMonth()%>
                </td>
                <td class="rd8">
                    <%=simpleDateFormat.format(fiscalTime.getBeginDate())%>
                </td>
                <td class="rd8">
                    <%=simpleDateFormat.format(fiscalTime.getEndDate())%>
                </td>
                <td class="rd8">
                    <%="Y".equals(fiscalTime.getStatus())?"可用":"不可用"%>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    <table width="95%" height="30" border="0" align="center"
           cellpadding="0" cellspacing="0" class="rd1">
        <tr>
            <td nowrap class="rd19" height="2" width="36%">
                <div align="left">
                    <font color="#FFFFFF">&nbsp;共&nbsp<%=pageModel.getTopPageNo()%>&nbsp页</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <font color="#FFFFFF">当前第</font>&nbsp
                    <font color="#FF0000"><%=pageModel.getPageNo()%></font>&nbsp
                    <font color="#FFFFFF">页</font>
                </div>
            </td>
            <td nowrap class="rd19" width="64%">
                <div align="right">
                    <input name="btnTopPage" class="button1" type="button"
                           id="btnTopPage" value="|&lt;&lt; " title="首页"
                           onClick="topPage()">
                    <input name="btnPreviousPage" class="button1" type="button"
                           id="btnPreviousPage" value=" &lt;  " title="上页"
                           onClick="previousPage()">
                    <input name="btnNextPage" class="button1" type="button"
                           id="btnNextPage" value="  &gt; " title="下页" onClick="nextPage()">
                    <input name="btnBottomPage" class="button1" type="button"
                           id="btnBottomPage" value=" &gt;&gt;|" title="尾页"
                           onClick="bottomPage()">
                    <input name="btnOk" class="button1" type="button" id="btnOk"
                           value="确定" onClick="selectOk()">
                    <input name="btnClose" class="button1" type="button"
                           id="btnClose" value="关闭" onClick="window.close()">
                </div>
            </td>
        </tr>
    </table>
</form>
</body>
</html>