<%@ page import="drp.systemmgr.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	User user = (User) session.getAttribute("user_info");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
		<title>Untitled Document</title>
		<link rel="stylesheet" href="style/drp.css">

		<script language="JavaScript">
function changeWin(){
    parent.workaround.cols="172,*";
	parent.toolBar.showMainMenu.style.display='none';	
}

</script>
		<style type="text/css">
<!--
body,td,th {
	color: #FFFFFF;
}
a:link {
	text-decoration: none;
	color: #FFFFFF;
}
a:visited {
	text-decoration: none;
	color: #FFFFFF;
}
a:hover {
	text-decoration: none;
	color: #FFFFFF;
}
a:active {
	text-decoration: none;
	color: #FFFFFF;
}
-->
</style>
	</head>

	<body class="boyd1" topmargin="0" leftmargin="0">
		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0" bgcolor="#333300">
			<tr>
				<td width="5%" nowrap>
					&nbsp;
				</td>
				<td width="58%" nowrap>
					<font color="#FFFFFF">
						<div id="showMainMenu" display='none'>
							<a href="#" onClick="changeWin()">显示主菜单</a>
						</div> </font>
				</td>
				<td width="21%">
					当前用户：<%=user==null?"null":user.getUsername()%>
				</td>
				<td width="8%">
					<font color="#FFFFFF">关于 &nbsp;帮助</font>
				</td>
				<td width="2%">
					&nbsp;
				</td>
				<td width="6%">
					<font color="#FFFFFF">注销</font>
				</td>
			</tr>
		</table>
	</body>
</html>
