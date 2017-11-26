<%@ page import="drp.basedata.manager.TemiClientManager" %>
<%@ page import="drp.basedata.domain.TemiClient" %>
<%@ page import="drp.util.exception.ApplicationException" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
	TemiClientManager temiClientManager = new TemiClientManager();
	int id = Integer.parseInt(request.getParameter("id"));
	TemiClient temiClient = temiClientManager.findTemiClientOrAreaById(id);
	String message = "";
	if ("delete".equals(request.getParameter("command"))) {
		message = "删除成功";
		try {
			temiClientManager.deleteTemiClientOrAreaById(id);
		} catch (ApplicationException ape){
			ape.printStackTrace();
			message = ape.getMessage();
		}
	}
%>
<%if (message!=null&&!"".equals(message)){
%>
<script type="text/javascript">
    alert("<%=message%>");
    window.parent.temiClientTreeFrame.location.reload();
    window.self.location='temi_client_display_area.html';
</script>
<%
	}
%>
<html>
	<head>
		<link rel="stylesheet" href="../style/drp.css" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>终端客户维护</title>
	</head>

	<body class="body1">
		<form id="temiClientForm" name="temiClientForm" method="post"
			action="">
			<table width="95%" border="0" cellspacing="0" cellpadding="0"
				height="8">
				<tr>
					<td width="522" class="p1" height="2" nowrap="nowrap">
						<img src="../images/mark_arrow_02.gif" width="14" height="14" />
						&nbsp;
						<b>基础数据管理&gt;&gt;终端客户维护</b>
					</td>
				</tr>
			</table>
			<hr width="97%" align="center" size="0" />
			<p></p>
			<p></p>
			<table width="95%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="213">
						<div align="right">
							当前终端客户名称：
						</div>
					</td>
					<td width="410">
						<label>
							<input name="temiaName" type="text" class="text1" id="temiaName"
								readonly="true" value="<%=temiClient.getName()%>"/>
						</label>
					</td>
				</tr>
			</table>
			<p></p>
			<label>
				<br />
			</label>
			<hr />
			<p align="center">
				<input name="btnModifyTemiClient" type="button" class="button1"
					id="btnModifyTemiClient"
					onClick="self.location='temi_client_modify.jsp?id=<%=id%>'" value="修改终端客户" />
				&nbsp;
				<input name="btnDeleteTemiClient" type="button" class="button1"
					id="btnDeleteTemiClient" value="删除终端客户" onclick="self.location='temi_client_crud.jsp?id=<%=id%>&command=delete'" />
				&nbsp;
				<input name="btnDetailInfo" type="button" class="button1"
					id="btnDetailInfo"
					onClick="self.location='temi_client_detail.jsp?id=<%=id%>'" value="查看详细信息" />
			</p>
		</form>
	</body>
</html>
