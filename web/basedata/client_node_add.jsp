<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
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
            var regionForm = document.getElementById("regionForm");
            regionForm.submit();
        }
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>添加区域节点</title>
</head>

<body class="body1">
<form name="regionForm" method="post" action="client_node_add.jsp">
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
               onclick="history.go(-1)"/>
    </p>
</form>
</body>
</html>
