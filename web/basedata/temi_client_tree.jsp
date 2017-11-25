<%@ page import="drp.basedata.manager.TemiClientManager" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" href="../style/drp.css">
		<style type="text/css">
<!--
a:link {
	text-decoration: none;
	color: #000000;
	font-size: 9pt;
	font-family: 宋体;
}
a:visited {
	text-decoration: none;
	color: #000000;
	font-size: 9pt;
	font-family: 宋体;
	
}
a:hover {
	text-decoration: none;
	color: #000000;
	font-size: 9pt;
	font-family: 宋体;

}
a:active {
	text-decoration: none;
	color: #000000;
	font-size: 9pt;
	font-family: 宋体;
}
-->
</style>

		<script language="JavaScript">
<!--
	function display(id) {
	 eval("var div=div"+id);
	 eval("var img=img"+id);
	 eval("var im=im"+id);
	 div.style.display=div.style.display!="none"?"none":"block";
	 img.src=div.style.display!="none"?"../images//minus.gif":"../images//plus.gif";
	 im.src=div.style.display!="none"?"../images//openfold.gif":"../images//closedfold.gif";
	 img.alt=div.style.display!="none"?"关闭":"展开";
	}
//-->
</script>
	</head>
	<body class="body1">
		<table>
			<tr>
				<td valign="top" nowrap="nowrap">
				<%=new TemiClientManager().getTemiClientTreeHtmlString()%>
				</td>
			</tr>
		</table>
	</body>
</html>