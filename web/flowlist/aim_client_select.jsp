<%@ page import="drp.util.pagemodel.PageModel" %>
<%@ page import="drp.basedata.domain.AimClient" %>
<%@ page import="drp.basedata.manager.AimClientManager" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	int index = Integer.parseInt(request.getParameter("index"));
	int pageNo = 1;
	int pageSize = 6;
	String condition = "";
	if (request.getParameter("pageNo") != null) {
		pageNo = Integer.parseInt(request.getParameter("pageNo"));
	}
	if (request.getParameter("condition") != null) {
		condition = request.getParameter("condition");
	}
	PageModel<AimClient> pageModel = AimClientManager.getInstance().findAimClientManagerListByCondition(pageNo,pageSize,condition);
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>请选择需方客户</title>
		<link rel="stylesheet" href="../style/drp.css">
		<script src="../script/client_validate.js"></script>
		<script type="text/javascript">
	function topPage() {
	    window.self.location = "aim_client_select.jsp?pageNo=<%=pageModel.getTopPageNo()%>&condition=<%=condition%>&index=<%=index%>";
	}
	
	function previousPage() {
        window.self.location = "aim_client_select.jsp?pageNo=<%=pageModel.getTopPageNo()%>&condition=<%=condition%>&index=<%=index%>";
	}
	
	function nextPage() {
        window.self.location = "aim_client_select.jsp?pageNo=<%=pageModel.getTopPageNo()%>&condition=<%=condition%>&index=<%=index%>";
	}
	
	function bottomPage() {
        window.self.location = "aim_client_select.jsp?pageNo=<%=pageModel.getTopPageNo()%>&condition=<%=condition%>&index=<%=index%>";

	}
	
	function queryClient() {
        window.self.location = "aim_client_select.jsp?condition="+document.getElementById("aimClientIdOrName").value+"&index=<%=index%>";
	}
	
	function selectOk() {
        var selectFlag = document.getElementsByName("selectFlag");
        var selected;
        for(var i=0;i<selectFlag.length;i++){
            if(selectFlag[i].checked){
                selected = selectFlag[i];
            }
        }
        var aboutAimClient = selected.value.split(",");
        var rowLength = window.opener.document.all.tblFlowCardDetail.rows.length;
        if (rowLength == 2) {
            window.opener.document.all.aimInnerId.value =aboutAimClient[0];
            window.opener.document.all.aimId.value = aboutAimClient[1];
            window.opener.document.all.aimName.value = aboutAimClient[2];
        }else{
            window.opener.document.all.aimInnerId[<%=index%>].value = aboutAimClient[0];
            window.opener.document.all.aimId[<%=index%>].value = aboutAimClient[1];
            window.opener.document.all.aimName[<%=index%>].value = aboutAimClient[2];
		}

        window.close();
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
							<b>请选择需方客户</b>
						</td>
					</tr>
				</table>
				<hr width="100%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="17%" height="29">
							<div align="left">
								需方客户代码/名称:
							</div>
						</td>
						<td width="57%">
							<input name="aimClientIdOrName" type="text" class="text1"
								id="aimClientIdOrName" size="50" maxlength="50">
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
						需方客户代码
					</td>
					<td class="rd6">
						需方客户名称
					</td>
					<td class="rd6">
						客户类型
					</td>
				</tr>
				<%
					for (AimClient aimClient:pageModel.getList()) {

				%>
				<tr>
					<td class="rd8">
						<input type="radio" name="selectFlag" value="<%=aimClient.getId()%>,<%=aimClient.getClientID()%>,<%=aimClient.getName()%>"
							onDblClick="selectOk()">
					</td>
					<td class="rd8">
						<%=aimClient.getClientID()%>
					</td>
					<td class="rd8">
						<%=aimClient.getName()%>
					</td>
					<td class="rd8">
						<%=aimClient.getLevelName()%>
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
