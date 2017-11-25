<%@ page import="drp.util.datadict.manager.DataDictManager" %>
<%@ page import="drp.util.datadict.domain.TemiClientLevel" %>
<%@ page import="drp.basedata.domain.TemiClient" %>
<%@ page import="drp.util.database.IdGenerator" %>
<%@ page import="drp.util.exception.ApplicationException" %>
<%@ page import="drp.basedata.manager.TemiClientManager" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
    int pid = Integer.parseInt(request.getParameter("pid"));
    request.setCharacterEncoding("utf-8");
    String errorMessage = "";
    if ("add".equals(request.getParameter("command"))) {
        TemiClient temiClient = new TemiClient();
        temiClient.setId(IdGenerator.generate("temi_client"));
        temiClient.setPid(Integer.parseInt(request.getParameter("pid")));
        temiClient.setTemiClientId(request.getParameter("temiId"));
        temiClient.setName(request.getParameter("temiName"));
        temiClient.setContactTel(request.getParameter("contactTel"));
        temiClient.setContactor(request.getParameter("contactor"));
        temiClient.setAddress(request.getParameter("address"));
        temiClient.setZipCode(request.getParameter("zipCode"));
        temiClient.setIsLeaf("Y");
        temiClient.setIsTemiClient("Y");
        DataDictManager dataDictManager = DataDictManager.getInstance();
        TemiClientLevel temiClientLevel =
                (TemiClientLevel) dataDictManager.findAbstractDataDictById(request.getParameter("temiType"));
        temiClient.setTemiClientLevel(temiClientLevel);
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
    <meta http-equiv="Content-Type" content="text/html; charset=GB18030">
    <title>添加终端客户</title>
    <link rel="stylesheet" href="../style/drp.css">
    <script src="../script/client_validate.js"></script>
    <script type="text/javascript">
        function validateTemiClientId(field) {
            if (trim(field.value).length > 0) {
                var xmlHttp = null;
                //表示当前浏览器不是ie，如ns，firefox
                if (window.XMLHttpRequest) {
                    xmlHttp = new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                var url = "TemiClientIdValidateServlet.servlet?TemiClientId=" + trim(field.value);
                xmlHttp.open("GET", url, true);
                xmlHttp.onreadystatechange = function () {
                    if (xmlHttp.readyState == 4) {
                        if (xmlHttp.status == 200) {
                            document.getElementById("TemiClientIdExistSpan").innerHTML
                                = "<font color='red'>" + xmlHttp.responseText + "</font>";
                        }
                        else {
                            alert("请求失败,错误码：" + xmlHttp.status)
                        }
                    }
                };
                xmlHttp.send(null);
            } else {
                document.getElementById("TemiClientIdExistSpan").innerHTML = "";
            }
        }
        function goAdd() {
            if(trim(document.getElementById("temiId").value).length==0){
                alert("终端客户ID不能为空");
                document.getElementById("temiId").focus();
                return;
            }
            if(document.getElementById("TemiClientIdExistSpan").innerHTML.indexOf("存在")>=0){
                alert("终端客户ID已经存在！");
                document.getElementById("temiId").focus();
                return;
            }
            if(trim(document.getElementById("temiName").value).length<1){
                alert("终端客户名称不能为空");
                document.getElementById("temiName").focus();
                return;
            }
            var temiClientForm = document.getElementById("temiClientForm");
            temiClientForm.method = "post";
            temiClientForm.action = "temi_client_add.jsp";
            temiClientForm.submit();
        }

        function goBack() {
            window.self.location = "temi_client_node_crud.jsp?id=<%=pid%>";
        }
    </script>
</head>

<body class="body1">
<form name="temiClientForm" target="_self" id="temiClientForm">
    <input type="hidden" name="command" value="add">
    <input type="hidden" name="pid" value="<%=pid%>">
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
                    <b>基础数据管理&gt;&gt;分销商维护&gt;&gt;添加终端客户</b>
                </td>
            </tr>
        </table>
        <hr width="97%" align="center" size=0>
        <table width="95%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td width="22%" height="29">
                    <div align="right">
                        <font color="#FF0000">*</font>终端客户代码:&nbsp;
                    </div>
                </td>
                <td width="78%">
                    <input name="temiId" type="text" class="text1" id="temiId"
                           size="10" maxlength="10" onblur="validateTemiClientId(this)">
                    <span id="TemiClientIdExistSpan"></span>
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
                           size="10" maxlength="10">
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
                            for (TemiClientLevel temiClientLevel :
                                    DataDictManager.getInstance().findTemiClientLevelList()) {

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
                           size="10" maxlength="10">
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
                           id="contactTel" size="10" maxlength="10">
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
                           size="10" maxlength="10">
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
                           size="10" maxlength="10">
                </td>
            </tr>
        </table>
        <hr width="97%" align="center" size=0>
        <div align="center">
            <input name="btnAdd" class="button1" type="button" id="btnAdd"
                   value="添加" onclick="goAdd()">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <input name="btnBack" class="button1" type="button" id="btnBack"
                   value="返回" onClick="goBack()">
        </div>
    </div>
</form>
</body>
</html>
