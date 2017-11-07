<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@page import="drp.basedata.domain.Client" %>
<%@ page import="drp.basedata.manager.ClientManager" %>
<%
	int id = Integer.parseInt(request.getParameter("id"));
	Client client = ClientManager.getInstance().findClientOrAreaById(id);
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
		<title>分销商详细信息</title>
		<link rel="stylesheet" href="../style/drp.css">
	</head>

	<body class="body1">
		<form name="clientForm" target="_self" id="clientForm">
			<div align="center">
				<table width="95%" border="0" cellspacing="2" cellpadding="2">
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="8">
					<tr>
						<td width="522" class="p1" height="2" nowrap>
							<img src="../images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>基础数据管理&gt;&gt;分销商维护&gt;&gt;分销商详细信息</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<table width="95%" height="267" border="0" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="22%">
							<div align="right">
								分销商代码:&nbsp;
							</div>
						</td>
						<td width="78%">
							<%=client.getClientLevel().getId()%>
						</td>
					</tr>
					<tr>
						<td>
							<div align="right">
								分销商名称:&nbsp;
							</div>
						</td>
						<td>
							<%=client.getName()%>
						</td>
					</tr>
					<tr>
						<td>
							<div align="right">
								分销商类型:&nbsp;
							</div>
						</td>
						<td>
							<%=client.getClientLevel().getName()%>
						</td>
					</tr>
					<tr>
						<td>
							<div align="right">
								银行帐号:&nbsp;
							</div>
						</td>
						<td>
							<%=client.getBankAccount()%>
						</td>
					</tr>
					<tr>
						<td>
							<div align="right">
								联系电话:&nbsp;
							</div>
						</td>
						<td>
							<%=client.getContactTel()%>
						</td>
					</tr>
					<tr>
						<td>
							<div align="right">
								地址:&nbsp;
							</div>
						</td>
						<td>
							<%=client.getAddress()%>
						</td>
					</tr>
					<tr>
						<td>
							<div align="right">
								邮编:&nbsp;
							</div>
						</td>
						<td>
							<%=client.getZipCode()%>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<div align="center">
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onClick="self.location = 'client_crud.jsp?id=<%=id%>'">
				</div>
			</div>
		</form>
	</body>
</html>
