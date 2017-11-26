<%@ page import="drp.basedata.manager.TemiClientManager" %>
<%@ page import="drp.basedata.domain.TemiClient" %>
<%@ page import="drp.util.exception.ApplicationException" %>
<%@ page import="drp.util.datadict.domain.TemiClientLevel" %>
<%@ page import="java.util.List" %>
<%@ page import="drp.util.datadict.manager.DataDictManager" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
	request.setCharacterEncoding("utf-8");
	TemiClientManager temiClientManager = new TemiClientManager();
	int id = Integer.parseInt(request.getParameter("id"));
	TemiClient temiClient = temiClientManager.findTemiClientOrAreaById(id);
	List<TemiClientLevel> temiClientLevelList = DataDictManager.getInstance().findTemiClientLevelList();
	String message = "";
	if("modify".equals(request.getParameter("command"))){
		temiClient.setName(request.getParameter("temiName"));

		TemiClientLevel temiClientLevel = (TemiClientLevel)DataDictManager.getInstance().findAbstractDataDictById(request.getParameter("temiType"));
		temiClient.setTemiClientLevel(temiClientLevel);

		temiClient.setContactor(request.getParameter("contactor"));
		temiClient.setContactTel(request.getParameter("contactTel"));
		temiClient.setAddress(request.getParameter("address"));
		temiClient.setZipCode(request.getParameter("zipCode"));
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
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
		<title>修改终端客户</title>
		<link rel="stylesheet" href="../style/drp.css">
		<script src="../script/client_validate.js"></script>
		<script type="text/javascript">
            function goModify() {
                var nameField =  document.getElementById("temiName");
                if(trim(nameField.value).length == 0){
                    alert("终端客户名称不能为空");
                    nameField.focus();
                    return;
                }
                var regionForm = document.getElementById("temiClientForm");
                regionForm.action = 'temi_client_modify.jsp';
                regionForm.method = 'post';
                regionForm.submit();
            }
		</script>
	</head>

	<body class="body1">
		<form name="temiClientForm" target="_self" id="temiClientForm">
			<input type="hidden" name="id" value="<%=id%>">
			<input type="hidden" name="command" value="modify">
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
							<b>基础数据管理&gt;&gt;分销商维护&gt;&gt;修改终端客户</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="22%" height="29">
							<div align="right">
								终端客户代码:&nbsp;
							</div>
						</td>
						<td width="78%">
							<input name="temiId" type="text" class="text1" id="clientId4"
								size="10" maxlength="10" readonly="true" value="<%=temiClient.getTemiClientId()%>">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>终端客户名称:&nbsp;
							</div>
						</td>
						<td>
							<input name="temiName" type="text" class="text1" id="temiName"
								size="10" maxlength="10" value="<%=temiClient.getName()%>">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>终端客户类型:&nbsp;
							</div>
						</td>
						<td>
							<select name="temiType" class="select1" id="temiType">
								<%
									for (TemiClientLevel temiClientLevel: temiClientLevelList) {

								%>
								<option value="<%=temiClientLevel.getId()%>">
									<%=temiClientLevel.getName()%>
								</option>
								<%
									}
								%>
							</select>
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								联系人:&nbsp;
							</div>
						</td>
						<td>
							<input name="contactor" type="text" class="text1" id="contactor"
								size="10" maxlength="10" value="<%=temiClient.getContactor()==null?"":temiClient.getContactor()%>">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								联系电话:&nbsp;
							</div>
						</td>
						<td>
							<input name="contactTel" type="text" class="text1"
								id="contactTel" size="10" maxlength="10" value="<%=temiClient.getContactTel()==null?"":temiClient.getContactTel()%>">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								联系地址:&nbsp;
							</div>
						</td>
						<td>
							<input name="address" type="text" class="text1" id="address"
								size="10" maxlength="10" value="<%=temiClient.getAddress()==null?"":temiClient.getAddress()%>">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								邮编:&nbsp;
							</div>
						</td>
						<td>
							<input name="zipCode" type="text" class="text1" id="zipCode"
								size="10" maxlength="10" value="<%=temiClient.getZipCode()==null?"":temiClient.getZipCode()%>">
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<div align="center">
					<input name="btnModify" class="button1" type="button"
						id="btnModify" value="修改" onclick="goModify()">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onclick="self.location='temi_client_crud.jsp?id=<%=id%>'" />
				</div>
			</div>
		</form>
	</body>
</html>
