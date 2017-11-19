<%@ page import="drp.basedata.manager.FiscalTimeManager" %>
<%@ page import="drp.util.factory.BeanFactory" %>
<%@ page import="java.util.List" %>
<%@ page import="drp.basedata.domain.FiscalTime" %>
<%@ page import="drp.util.pagemodel.PageModel" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
	int pageNo = 1;
	if(request.getParameter("pageNo")!=null && !"".equals(request.getParameter("pageNo"))){
	    pageNo = Integer.parseInt(request.getParameter("pageNo"));
	}
	int pageSize = 6;
	FiscalTimeManager fiscalTimeManager =
			(FiscalTimeManager) BeanFactory.getInstance().getServiceObject(FiscalTimeManager.class);
	PageModel<FiscalTime> pageModel = fiscalTimeManager.findFiscalList(pageNo,pageSize);
	List<FiscalTime> fiscalTimes = pageModel.getList();
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>会计核算期间维护</title>
		<link rel="stylesheet" href="../style/drp.css">
		<script type="text/javascript">
            function topPage() {
                window.self.location = "fiscal_year_period_maint.jsp?pageNo=<%=pageModel.getTopPageNo()%>";
            }

            function previousPage() {
                window.self.location = "fiscal_year_period_maint.jsp?pageNo=<%=pageModel.getPreviousPageNo()%>";
            }

            function nextPage() {
                window.self.location = "fiscal_year_period_maint.jsp?pageNo=<%=pageModel.getNextPageNo()%>";
            }

            function bottomPage() {
                window.self.location = "fiscal_year_period_maint.jsp?pageNo=<%=pageModel.getBottomPageNo()%>";
            }
            function goModify(){
                var checkBoxes = document.getElementsByName("selectFlag");
                var selectId = null;
                for(var i=0;i<checkBoxes.length; i++){
                    if(checkBoxes[i].checked){
                        selectId = checkBoxes[i].value;
					}
				}
				if(selectId==null){
                    alert("请选择一个需要修改的会计核算日期");
                    return;
				}
                window.self.location = "fiscal_year_period_modify.jsp?id="+selectId;
			}
		</script>
	</head>

	<body class="body1">
		<form name="fiscalYearPeriodForm">
			<div align="center">
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="35">
					<tr>
						<td class="p1" height="18" nowrap>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td width="522" class="p1" height="17" nowrap>
							<img src="../images/mark_arrow_02.gif" width="14" height="14">
							&nbsp;
							<b>基础数据管理&gt;&gt;会计核算期间维护</b>
						</td>
					</tr>
				</table>
				<hr width="100%" align="center" size=0>
			</div>
			<table width="95%" height="20" border="0" align="center"
				cellspacing="0" class="rd1" id="toolbar">
				<tr>
					<td width="49%" class="rd19">
						<font color="#FFFFFF">查询列表</font>
					</td>
					<td width="27%" nowrap class="rd16">
						<div align="right"></div>
					</td>
				</tr>
			</table>
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
					fiscalTimes) {

				%>
				<tr>
					<td class="rd8">
						<input type="radio" name="selectFlag" value="<%=fiscalTime.getId()%>">
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
					<td nowrap class="rd19" height="2">
						<div align="left">
							<font color="#FFFFFF">&nbsp;共&nbsp<%=pageModel.getTotalPageNum()%>&nbsp页</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="#FFFFFF">当前第</font>&nbsp
							<font color="#FF0000"><%=pageModel.getPageNo()%></font>&nbsp
							<font color="#FFFFFF">页</font>
						</div>
					</td>
					<td nowrap class="rd19">
						<div align="right">
							<input name="btnTopPage" class="button1" type="button"
								id="btnTopPage" value="|&lt;&lt; " title="首页" onclick=topPage() >
							<input name="btnPreviousPage" class="button1" type="button"
								id="btnPreviousPage" value=" &lt;  " title="上页" onclick=previousPage()>
							<input name="btnNextPage" class="button1" type="button"
								id="btnNextPage" value="  &gt; " title="下页" onclick="nextPage()">
							<input name="btnBottomPage" class="button1" type="button"
								id="btnBottomPage" value=" &gt;&gt;|" title="尾页" onclick="bottomPage()">
							<input name="btnAdd" type="button" class="button1" id="btnAdd"
								value="添加" onClick="self.location='fiscal_year_period_add.jsp'">
							<input name="btnModify" class="button1" type="button"
								id="btnModify" value="修改"
								onClick="goModify()">
						</div>
					</td>
				</tr>
			</table>
			<p>
				&nbsp;
			</p>
		</form>
	</body>
</html>
