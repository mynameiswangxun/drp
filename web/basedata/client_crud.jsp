<%@ page import="drp.basedata.manager.ClientManager" %>
<%@ page import="drp.basedata.domain.Client" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
	request.setCharacterEncoding("utf-8");
	int id = Integer.parseInt(request.getParameter("id"));
	ClientManager clientManager = ClientManager.getInstance();
	Client client = clientManager.findClientOrAreaById(id);
	if("delete".equals(request.getParameter("command"))){
		clientManager.delClientOrArea(id);
%>
<script type="text/javascript">
	alert("删除成功!");
    window.parent.clientTreeFrame.location.reload();
    self.location = "client_display_area.html";
</script>
<%
	}

%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link rel="stylesheet" href="../style/drp.css" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript">
			function doDel() {
			    if(window.confirm("确认删除吗？")){
                    var clientForm = document.getElementById("clientForm");
                    clientForm.method = "post";
                    clientForm.action = "client_crud.jsp";
                    clientForm.submit();
				}
            }
		</script>
		<title>分销商维护</title>
	</head>

	<body class="body1">
		<form id="clientForm" name="clientForm">
			<input type="hidden" name="command" value="delete">
			<input type="hidden" name="id" value="<%=id%>">
			<table width="95%" border="0" cellspacing="0" cellpadding="0"
				height="8">
				<tr>
					<td width="522" class="p1" height="2" nowrap="nowrap">
						<img src="../images/mark_arrow_02.gif" width="14" height="14" />
						&nbsp;
						<b>基础数据管理&gt;&gt;分销商维护</b>
					</td>
				</tr>
			</table>
			<hr width="97%" align="center" size="0" />
			<p>
			<p>
			<table width="95%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="213">
						<div align="right">
							当前分销商名称：
						</div>
					</td>
					<td width="410">
						<label>
							<input name="clientName" type="text" class="text1"
								id="clientName" readonly="true" value="<%=client.getName()%>"/>
						</label>
					</td>
				</tr>
			</table>
			<p>
				<label>
					<br />
				</label>
			<hr />
			<p align="center">
				<input name="btnModifyClient" type="button" class="button1"
					id="btnModifyClient" onClick="self.location='client_modify.jsp?id='+<%=id%>"
					value="修改分销商" />
				&nbsp;
				<input name="btinDeleteClient" type="button" class="button1"
					id="btinDeleteClient" value="删除分销商" onclick="doDel()"/>
				&nbsp;
				<input name="btnViewDetail" type="button" class="button1"
					id="btnViewDetail" onClick="self.location='client_detail.jsp?id=<%=id%>'"
					value="查看详细信息" />
			</p>
		</form>
	</body>
</html>
