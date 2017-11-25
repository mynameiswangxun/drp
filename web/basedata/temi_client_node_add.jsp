<%@ page import="drp.basedata.domain.TemiClient" %>
<%@ page import="drp.util.database.IdGenerator" %>
<%@ page import="drp.basedata.manager.TemiClientManager" %>
<%@ page import="drp.util.exception.ApplicationException" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
	int pid = Integer.parseInt(request.getParameter("pid"));
	request.setCharacterEncoding("utf-8");
	String errorMessage = "";
	if ("add".equals(request.getParameter("command"))) {
		TemiClient temiClient = new TemiClient();
		temiClient.setId(IdGenerator.generate("temi_client"));
		temiClient.setPid(Integer.parseInt(request.getParameter("pid")));
		temiClient.setName(request.getParameter("name"));
		temiClient.setIsLeaf("Y");
		temiClient.setIsTemiClient("N");
		errorMessage = "添加成功";
		try {
			new TemiClientManager().addTemiClientOrArea(temiClient);
		} catch (ApplicationException ape) {
			ape.printStackTrace();
			errorMessage = ape.getMessage();
		}
	}
%>
<%
	if (errorMessage != null && !"".equals(errorMessage)) {

%>
<script type="text/javascript">
    alert("<%=errorMessage%>");
    window.parent.temiClientTreeFrame.location.reload();
</script>
<%
	}
%>
<html>
	<head>
		<link rel="stylesheet" href="../style/drp.css" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script src="../script/client_validate.js"></script>
		<script type="text/javascript">
            function goAdd() {
                if(trim(document.getElementById("name").value).length==0){
                    alert("区域名称不能为空");
                    document.getElementById("name").focus();
                    return;
                }
                var regionForm = document.getElementById("regionForm");
                regionForm.method = "post";
                regionForm.action = "temi_client_node_add.jsp";
                regionForm.submit();
            }
		</script>
		<title>添加区域节点</title>
	</head>

	<body class="body1">
		<form id="regionForm" name="regionForm" method="post" action="">
			<input type="hidden" name="command" value="add">
			<input type="hidden" name="pid" value="<%=pid%>">
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
							<input name="name" type="text" class="text1" id="name" />
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
				<input name="btnAdd" class="button1" type="button" id="btnAdd"
					value="添加" onclick="goAdd()"/>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="btnBack" class="button1" type="button" id="btnBack"
					value="返回" onclick="self.location = 'temi_client_node_crud.jsp?id=<%=pid%>'" />
			</p>
		</form>
	</body>
</html>
