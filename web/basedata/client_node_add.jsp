<%@ page import="drp.basedata.manager.ClientManager" %>
<%@ page import="drp.basedata.domain.*" %>
<%@ page import="drp.util.database.IdGenerator" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    int pid = Integer.parseInt(request.getParameter("pid"));
    if("add".equals(request.getParameter("command"))){
        ClientManager clientManager = ClientManager.getInstance();
        if(clientManager.isExistAreaName(request.getParameter("name"))){
            out.print("添加失败，区域名已存在");
        }else{
            Client newArea = new Client();
            newArea.setId(IdGenerator.generate("client"));
            newArea.setPid(pid);
            newArea.setName(request.getParameter("name"));
            newArea.setIsLeaf("Y");
            newArea.setIsClient("N");
            clientManager.addClientOrArea(newArea);
%>
<script type="text/javascript">
    alert("添加成功");
    window.parent.clientTreeFrame.location.reload();
</script>
<%
        }
    }
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link rel="stylesheet" href="../style/drp.css"/>
    <script src="../script/client_validate.js"></script>
    <script type="text/javascript">
        function doAdd() {
            if (trim(document.getElementById("name").value).length < 1) {
                alert("区域名称不能为空！");
                document.getElementById("name").focus();
                return;
            }
            document.getElementById("name").value = trim(document.getElementById("name").value);
            var regionForm = document.getElementById("regionForm");
            regionForm.submit();
        }
        function goBack() {
            window.self.location = "client_node_crud.jsp?id=<%=pid%>";
        }
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>添加区域节点</title>
</head>

<body class="body1">
<form name="regionForm" method="post" action="client_node_add.jsp" id="regionForm">
    <input type="hidden" name="command" value="add">
    <input type="hidden" name="pid" value="<%=pid%>">
    <table width="95%" border="0" cellspacing="0" cellpadding="0"
           height="8">
        <tr>
            <td width="522" class="p1" height="2" nowrap="nowrap">
                <img src="../images/mark_arrow_03.gif" width="14" height="14"/>
                &nbsp;
                <b>基础数据管理&gt;&gt;分销商维护&gt;&gt;添加区域节点</b>
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
                    <font color="#FF0000">*</font>区域名称：
                </div>
            </td>
            <td width="410">
                <label>
                    <input name="name" type="text" class="text1" id="name"/>
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
        <input name="btnAdd" class="button1" type="button" value="添加" onclick="doAdd()"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input name="btnBack" class="button1" type="button" value="返回"
               onclick="goBack()"/>
    </p>
</form>
</body>
</html>
