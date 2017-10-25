<%@ page import="drp.systemmgr.domain.User" %>
<%@ page import="drp.systemmgr.manager.UserManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //调用UserManager保存用户
    UserManager userManager = UserManager.getInstance();
    String userId = "";
    String username = "";
    String contactTel = "";
    String email = "";
    if ("add".equals(request.getParameter("command"))) {                          //判断是否是点击button提交
        if (userManager.findUserById(request.getParameter("userId")) == null) {
            request.setCharacterEncoding("utf-8");
            User user = new User();
            user.setId(request.getParameter("userId"));
            user.setUsername(request.getParameter("userName"));
            user.setPassword(request.getParameter("password"));
            user.setContactTel(request.getParameter("contactTel"));
            user.setEmail(request.getParameter("email"));
            userManager.addUser(user);
            out.println("添加用户成功!");
        } else {
            userId = request.getParameter("userId");
            username = request.getParameter("userName");
            contactTel = request.getParameter("contactTel");
            email = request.getParameter("email");
            out.println("添加用户失败，用户ID:" + request.getParameter("userId") + "已经存在!");
        }
    }
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GB18030">
    <title>添加用户</title>
    <link rel="stylesheet" href="../style/drp.css">
    <script src="../script/client_validate.js"></script>
    <script type="text/javascript">
        function goBack() {
            window.self.location = "user_maint.html"
        }

        function addUser() {
            //检查用户代码
            var vUserID = document.getElementById("userId").value;
            var re = new RegExp(/^[a-z]+[0-9]{4}$/);                  //正则表达式对象
            if (!re.test(trim(vUserID))) {
                alert("用户ID不合法,格式为a1234");
                document.getElementById("userId").focus();
                return;
            }

            //检查用户名称
            var vUsername = document.getElementById("userName").value;
            if (isEmpty(trim(vUsername))) {
                alert("用户名称不能为空");
                document.getElementById("userName").focus();
                return;
            }
            if (trim(vUsername) == trim(vUserID)) {
                alert("用户名称不能与用户代码一致");
                document.getElementById("userName").focus();
                return;
            }
            //检查用户密码
            var vPassword = document.getElementById("password").value;
            if (vPassword.indexOf(" ") != -1) {
                alert("密码中不能包含空格");
                document.getElementById("password").focus();
                return;
            }
            if (vPassword.length < 6) {
                alert("密码至少为6位");
                document.getElementById("password").focus();
                return;
            }

            //检查电话号码
            var vContactTel = document.getElementById("contactTel").value;
            if (trim(vContactTel).length > 0) {
                for (var i = 0; i < trim(vContactTel).length; i++) {
                    if (!(trim(vContactTel).charAt(i) >= '0' && trim(vContactTel).charAt(i) <= '9')) {
                        alert("电话号码不合法");
                        document.getElementById("contactTel").focus();
                        return;
                    }
                }
            }
            //检查邮箱
            var vEmail = document.getElementById("email").value;
            re = new RegExp(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/);
            if (trim(vEmail).length > 0) {
                if (!re.test(trim(vEmail))) {
                    alert("请输入正确的邮箱");
                    document.getElementById("email").focus();
                    return;
                }
            }
            document.getElementById("userForm").action = "user_add.jsp";
            document.getElementById("userForm").method = "post";
            document.getElementById("userForm").submit();
        }

        function init() {
            document.getElementById("userId").focus();
        }

        var xmlHttp;

        function createXMLHttpRequest() {
            if (window.XMLHttpRequest) {
                xmlHttp = new XMLHttpRequest();
            } else if (window.ActiveXObject) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
        }

        function validate(field) {
            //alert("离开焦点");

            //创建Ajax核心对象XMLHttpRequest
            createXMLHttpRequest();

            var url = "../user_validate.servlet?userId=" + trim(field.value) + "&time=" + new Date().getTime();
            //设置请求方式为GET，设置请求的URL，设置为异步提交
            xmlHttp.open("GET", url, true);
            //将函数地址赋值给onreadystatechange属性（响应事件），当Ajax状态发生变化时会调用该函数
            xmlHttp.onreadystatechange = callback;
            //将设置信息发送到Ajax引擎
            xmlHttp.send(null);
        }
        function callback() {
            //引擎状态为成功
            if(xmlHttp.readyState == 4){
                //Http协议为成功
                if(xmlHttp.status == 200){
                    document.getElementById("userIdCk").innerHTML = "<font color='red'>"+xmlHttp.responseText+"</font>";
                }else{
                    alert("请求失败，错误码:"+xmlHttp.status);
                }
            }
        }
    </script>
</head>

<body class="body1" onload="init()">
<form name="userForm" target="_self" id="userForm">
    <input type="hidden" name="command" value="add">
    <div align="center">
        <table width="95%" border="0" cellspacing="2" cellpadding="2">
            <tr>
                <td>&nbsp;

                </td>
            </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="0"
               height="25">
            <tr>
                <td width="522" class="p1" height="25" nowrap>
                    <img src="../images/mark_arrow_03.gif" width="14" height="14">
                    &nbsp;
                    <b>系统管理&gt;&gt;用户维护&gt;&gt;添加</b>
                </td>
            </tr>
        </table>
        <hr width="97%" align="center" size=0>
        <table width="95%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td width="22%" height="29">
                    <div align="right">
                        <font color="#FF0000">*</font>用户代码:&nbsp;
                    </div>
                </td>
                <td width="78%">
                    <input name="userId" type="text" class="text1" id="userId"
                           size="10" maxlength="10" value="<%=userId%>" onblur="validate(this)">
                    <span id="userIdCk"></span>
                </td>
            </tr>
            <tr>
                <td height="26">
                    <div align="right">
                        <font color="#FF0000">*</font>用户名称:&nbsp;
                    </div>
                </td>
                <td>
                    <input name="userName" type="text" class="text1" id="userName"
                           size="20" maxlength="20" value="<%=username%>">
                </td>
            </tr>
            <tr>
                <td height="26">
                    <div align="right">
                        <font color="#FF0000">*</font>密码:&nbsp;
                    </div>
                </td>
                <td>
                    <label>
                        <input name="password" type="password" class="text1"
                               id="password" size="20" maxlength="20">
                    </label>
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
                           id="contactTel" size="20" maxlength="20" value="<%=contactTel%>">
                </td>
            </tr>
            <tr>
                <td height="26">
                    <div align="right">
                        email:&nbsp;
                    </div>
                </td>
                <td>
                    <input name="email" type="text" class="text1" id="email"
                           size="20" maxlength="20" value="<%=email%>">
                </td>
            </tr>
        </table>
        <hr width="97%" align="center" size=0>
        <div align="center">
            <input name="btnAdd" class="button1" type="button" id="btnAdd"
                   value="添加" onClick="addUser()">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <input name="btnBack" class="button1" type="button" id="btnBack"
                   value="返回" onClick="goBack()"/>
        </div>
    </div>
</form>
</body>
</html>
