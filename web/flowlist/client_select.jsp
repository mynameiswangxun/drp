<%@ page import="drp.basedata.manager.ClientManager" %>
<%@ page import="drp.util.pagemodel.PageModel" %>
<%@ page import="drp.basedata.domain.Client" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%
	int pageNo = 1;
	int pageSize = 6;
	String condition = "";
	request.setCharacterEncoding("utf-8");
	if(request.getParameter("pageNo")!=null){
	    pageNo = Integer.parseInt(request.getParameter("pageNo"));
	}
	if(request.getParameter("condition")!=null){
	    condition = request.getParameter("condition");
	}
	ClientManager clientManager = ClientManager.getInstance();
	PageModel<Client> pageModel = clientManager.findClientList(pageNo,pageSize,condition);
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>请选择分销商</title>
		<link rel="stylesheet" href="../style/drp.css">
		<script src="../script/client_validate.js"></script>
		<script type="text/javascript">
	function topPage() {
	    window.self.location="client_select.jsp?pageNo=<%=pageModel.getTopPageNo()%>&condition=<%=condition%>";
	}
	
	function previousPage() {
        window.self.location="client_select.jsp?pageNo=<%=pageModel.getPreviousPageNo()%>&condition=<%=condition%>";
	}
	
	function nextPage() {
        window.self.location="client_select.jsp?pageNo=<%=pageModel.getNextPageNo()%>&condition=<%=condition%>";
	}
	
	function bottomPage() {
        window.self.location="client_select.jsp?pageNo=<%=pageModel.getBottomPageNo()%>&condition=<%=condition%>";

	}
	
	function queryClient() {
        window.self.location="client_select.jsp?condition="+document.getElementById("clientIdOrName").value;
	}
	
	function selectOk() {
		var selectFlag = document.getElementsByName("selectFlag");
        var selected;
		for(var i=0;i<selectFlag.length;i++){
		    if(selectFlag[i].checked){
		        selected = selectFlag[i];
			}
		}
		var aboutClient = selected.value.split(",");
		window.opener.document.getElementById("cid").value=aboutClient[0];
        window.opener.document.getElementById("clientId").value=aboutClient[1];
        window.opener.document.getElementById("clientName").value=aboutClient[2];
        window.close();

	}
	
</script>
	</head>

	<body class="body1">
		<form name="clientForm">
			<div align="center">
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="34">
					<tr>
						<td width="522" class="p1" height="34" nowrap>
							<img src="../images/search.gif" width="32" height="32">
							&nbsp;
							<b>请选择分销商</b>
						</td>
					</tr>
				</table>
				<hr width="100%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="17%" height="29">
							<div align="left">
								分销商代码/名称:
							</div>
						</td>
						<td width="57%">
							<input name="clientIdOrName" type="text" class="text1"
								id="clientIdOrName" size="50" maxlength="50">
						</td>
						<td width="26%">
							<div align="left">
								<input name="btnQuery" type="button" class="button1"
									id="btnQuery" value="查询" onclick="queryClient()">
							</div>
						</td>
					</tr>
					<tr>
						<td height="16">
							<div align="right"></div>
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<div align="right"></div>
						</td>
					</tr>
				</table>

			</div>
			<table width="95%" border="0" cellspacing="0" cellpadding="0"
				class="rd1" align="center">
				<tr>
					<td nowrap height="10" class="p2">
						分销商信息
					</td>
					<td nowrap height="10" class="p3">
						&nbsp;
					</td>
				</tr>
			</table>
			<table width="95%" border="1" cellspacing="0" cellpadding="0"
				align="center" class="table1">
				<tr>
					<td class="rd6">
						选择
					</td>
					<td class="rd6">
						分销商代码
					</td>
					<td class="rd6">
						分销商名称
					</td>
					<td class="rd6">
						分销商类型
					</td>
				</tr>
				<%
					for (Client client:pageModel.getList()
						 ) {

				%>
				<tr>
					<td class="rd8">
						<input type="radio" name="selectFlag" value="<%=client.getId()%>,<%=client.getClientId()%>,<%=client.getName()%>"
							onDblClick="selectOk()">
					</td>
					<td class="rd8">
						<%=client.getClientId()%>
					</td>
					<td class="rd8">
						<%=client.getName()%>
					</td>
					<td class="rd8">
						<%=client.getClientLevel().getName()%>
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
							<font color="#FFFFFF">&nbsp;共&nbsp<%=pageModel.getTotalPageNum()%>&nbsp页</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
