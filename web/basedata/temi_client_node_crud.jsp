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
        } catch (ApplicationException ape) {
            ape.printStackTrace();
            message = ape.getMessage();
        }
    }
%>
<%
    if (message != null && !"".equals(message)) {
%>
<script type="text/javascript">
    alert("<%=message%>");
    window.parent.temiClientTreeFrame.location.reload();
</script>
<html>
<head>
    <link rel="stylesheet" href="../style/drp.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>

<body class="body1">
</body>
</html>

<%

} else {
%>
<html>
<head>
    <link rel="stylesheet" href="../style/drp.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>终端客户维护</title>
</head>

<body class="body1">
<form id="regionForm" name="regionForm" method="post" action="">
    <table width="95%" border="0" cellspacing="0" cellpadding="0"
           height="8">
        <tr>
            <td width="522" class="p1" height="2" nowrap="nowrap">
                <img src="../images/mark_arrow_02.gif" width="14" height="14"/>
                &nbsp;
                <b>基础数据管理&gt;&gt;终端客户维护</b>
            </td>
        </tr>
    </table>
    <hr width="97%" align="center" size="0"/>
    <p></p>
    <p></p>
    <table width="95%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td width="213">
                <div align="right">
                    当前区域名称：
                </div>
            </td>
            <td width="410">
                <label>
                    <input name="name" type="text" class="text1" id="name" size="40"
                           maxlength="40" readonly="true" value="<%=temiClient.getName()%>"/>
                </label>
            </td>
        </tr>
    </table>
    <p></p>
    <label>
        <br/>
    </label>
    <hr/>
    <p align="center">
        <input name="btnAddRegion" type="button" class="button1"
               id="btnAddRegion"
               onClick="self.location='temi_client_node_add.jsp?pid=<%=id%>'" value="添加区域"/>
        &nbsp;
        <input name="btnDeleteRegion" type="button" class="button1"
               id="btnDeleteRegion" value="删除区域"
               onClick="self.location='temi_client_node_crud.jsp?id=<%=id%>&command=delete'"/>
        &nbsp;
        <input name="btnModifyRegion" type="button" class="button1"
               id="btnModifyRegion"
               onClick="self.location='temi_client_node_modify.jsp?id=<%=id%>'" value="修改区域"/>
        &nbsp;
        <input name="btnAddTemiClient" type="button" class="button1"
               id="btnAddTemiClient"
               onClick="self.location='temi_client_add.jsp?pid=<%=id%>'" value="添加终端客户"/>
    </p>
</form>
</body>
</html>
<%
    }
%>