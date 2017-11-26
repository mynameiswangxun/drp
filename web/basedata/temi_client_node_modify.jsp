<%@ page import="drp.basedata.manager.TemiClientManager" %>
<%@ page import="drp.basedata.domain.TemiClient" %>
<%@ page import="drp.util.exception.ApplicationException" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
	request.setCharacterEncoding("utf-8");
	TemiClientManager temiClientManager = new TemiClientManager();
	int id = Integer.parseInt(request.getParameter("id"));
	TemiClient temiClient = temiClientManager.findTemiClientOrAreaById(id);
	String message = "";
	if("modify".equals(request.getParameter("command"))){
		temiClient.setName(request.getParameter("name"));
		message = "修改成功!";
		try{
		    temiClientManager.modifyTemiClientOrArea(temiClient);
		}catch (ApplicationException ape){
		    ape.printStackTrace();
		    message = ape.getMessage();
		}
	}

%>
<%
	if(!"".equals(message) && message!=null){

%>
<script type="text/javascript">
	alert("修改成功!");
    window.parent.temiClientTreeFrame.location.reload();
</script>
<%
	}
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" href="../style/drp.css" />
		<script src="../script/client_validate.js"></script>
		<script type="text/javascript">
			function goModify() {
			    var nameField =  document.getElementById("name");
			    if(trim(nameField.value).length == 0){
			        alert("区域名称不能为空");
			        nameField.focus();
			        return;
				}
				var regionForm = document.getElementById("regionForm");
				regionForm.action = 'temi_client_node_modify.jsp';
				regionForm.submit();
            }
		</script>
		<title>添加区域节点</title>

	</head>

	<body class="body1">
		<form id="regionForm" name="regionForm" method="post">
			<input type="hidden" name="id" value="<%=id%>">
			<input type="hidden" name="command" value="modify">
			<table width="95%" border="0" cellspacing="0" cellpadding="0"
				height="8">
				<tr>
					<td width="522" class="p1" height="2" nowrap="nowrap">
						<img src="../images/mark_arrow_03.gif" width="14" height="14" />
						&nbsp;
						<b>基础数据管理&gt;&gt;终端客户维护&gt;&gt;添加区域节点</b>
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
							<font color="#FF0000">*</font>区域名称：
						</div>
					</td>
					<td width="410">
						<label>
							<input name="name" type="text" class="text1" id="name" value="<%=temiClient.getName()%>"/>
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
				<input name="btnModify" class="button1" type="button" id="btnModify"
					value="修改" onclick="goModify()"/>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="btnBack" class="button1" type="button" id="btnBack"
					value="返回" onclick="self.location='temi_client_node_crud.jsp?=<%=id%>'" />
			</p>
		</form>
	</body>
</html>
