<%@ page import="java.util.List" %>
<%@ page import="drp.util.datadict.domain.ItemCategory" %>
<%@ page import="drp.util.datadict.domain.ItemUnit" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
	List<ItemCategory> categories = (List<ItemCategory>) request.getAttribute("categories");
	List<ItemUnit> units = (List<ItemUnit>) request.getAttribute("units");
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>添加物料</title>
		<link rel="stylesheet" href="../style/drp.css">
		<script src="../script/client_validate.js"></script>
		<script type="text/javascript">
			function validateForm() {
			   	var massage = "";
				if(trim(document.getElementById("itemNo").value).length==0){
					message = "物料代码为空，请修改！\n";
				}
				if(trim(document.getElementById("itemName").value).length==0){
				    message += "物料名称为空，请修改\n";
				}
				if(message.length>0){
				    alert(message);
				    return false;
				}
				return true;
            }
		</script>
	</head>

	<body class="body1">
		<form name="itemForm" target="_self" id="itemForm" method="post" action="AddItemServlet.servlet" onsubmit="return validateForm()">
			<div align="center">
				<table width="95%" border="0" cellspacing="2" cellpadding="2">
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="../images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>基础数据管理&gt;&gt;物料维护&gt;&gt;添加</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="22%" height="29">
							<div align="right">
								<font color="#FF0000">*</font>物料代码:&nbsp;
							</div>
						</td>
						<td width="78%">
							<input name="itemNo" type="text" class="text1" id="itemNo"
								size="10" maxlength="10">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>物料名称:&nbsp;
							</div>
						</td>
						<td>
							<input name="itemName" type="text" class="text1" id="itemName"
								size="20" maxlength="20">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								物料规格:&nbsp;
							</div>
						</td>
						<td>
							<label>
								<input name="spec" type="text" class="text1" id="spec"
									size="20" maxlength="20">
							</label>
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								物料型号:&nbsp;
							</div>
						</td>
						<td>
							<input name="pattern" type="text" class="text1" id="pattern"
								size="20" maxlength="20">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>类别:&nbsp;
							</div>
						</td>
						<td>
							<select name="category" class="select1" id="category">
								<%
									for (ItemCategory category:
										 categories) {
								%>
								<option value="<%=category.getId()%>">
									<%=category.getName()%>
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
								<font color="#FF0000">*</font>计量单位:&nbsp;
							</div>
						</td>
						<td>
							<select name="unit" class="select1" id="unit">
								<%
									for (ItemUnit unit:
										 units) {
								%>
								<option value="<%=unit.getId()%>">
									<%=unit.getName()%>
								</option>
								<%
									}
								%>
							</select>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<div align="center">
					<input name="btnAdd" class="button1" type="submit" id="btnAdd"
						value="添加">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onClick="self.location= 'item_maint.jsp'">
				</div>
			</div>
		</form>
	</body>
</html>
