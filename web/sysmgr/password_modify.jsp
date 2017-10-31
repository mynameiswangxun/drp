<%@ page import="drp.systemmgr.domain.User" %>
<%@ page import="drp.systemmgr.manager.UserManager" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
    if("modify".equals(request.getParameter("command"))){
        UserManager userManager = UserManager.getInstance();
        User user = (User)session.getAttribute("user_info");
        userManager.modifyPassword(user.getId(),request.getParameter("newPassword"));
        session.setAttribute("user_info",userManager.findUserById(user.getId()));
        out.print("密码修改成功");
    }
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GB18030">
    <title>修改密码</title>
    <link rel="stylesheet" href="../style/drp.css">
    <script type="text/javascript">

        function modifyPassword() {
            var newPassword = document.getElementById("newPassword").value;
            if (newPassword.indexOf(" ") != -1) {
                alert("新密码中不能包含空格");
                document.getElementById("newPassword").focus();
                return;
            }
            if (newPassword.length < 6) {
                alert("新密码至少为6位");
                document.getElementById("newPassword").focus();
                return;
            }
            if(newPassword!=document.getElementById("affirmNewPassword").value){
                alert("两次密码必须一致！")
                document.getElementById("affirmNewPassword").focus();
                return;
            }
            if(document.getElementById("oldPasswordChecked").innerHTML.indexOf("错误")> 0){
                alert("原密码错误，请重新输入！")
                document.getElementById("oldPassword").focus();
                return;
            }
            document.getElementById("userForm");
            document.getElementById("userForm").action="password_modify.jsp";
            document.getElementById("userForm").method="post";
            document.getElementById("userForm").submit();
        }
        function oldPasswordValidate(field){
            //离开焦点
            var xmlHttp;
            if (window.XMLHttpRequest) {
                xmlHttp = new XMLHttpRequest();
            } else if (window.ActiveXObject) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }

            var url = "../oldPassword_validate.servlet?password=" + field.value+ "&time=" + new Date().getTime();
            //设置请求方式为GET，设置请求的URL，设置为异步提交
            xmlHttp.open("GET", url, true);
            //将函数地址赋值给onreadystatechange属性（响应事件），当Ajax状态发生变化时会调用该函数
            xmlHttp.onreadystatechange = function(){
                //引擎状态为成功
                if(xmlHttp.readyState == 4){
                    //Http协议为成功
                    if(xmlHttp.status == 200){
                        document.getElementById("oldPasswordChecked").innerHTML = "<font color='red'>"+xmlHttp.responseText+"</font>";
                    }else{
                        alert("请求失败，错误码:"+xmlHttp.status);
                    }
                }
            };
            //将设置信息发送到Ajax引擎
            xmlHttp.send(null);
        }
    </script>
</head>

<body class="body1">
<form name="userForm" id="userForm"><input type="hidden" name="command" value="modify">
    <div align="center">
        <table width="100%" border="0" cellspacing="0" cellpadding="0"
               height="51">
            <tr>
                <td class="p1" height="16" nowrap>
                    &nbsp;
                </td>
            </tr>
            <tr>
                <td class="p1" height="35" nowrap>
                    &nbsp&nbsp
                    <img src="../images/mark_arrow_02.gif" width="14" height="14">
                    <b><strong>系统管理&gt;&gt;</strong>修改密码</b>
                </td>
            </tr>
        </table>
        <hr width="100%" align="center" size=0>
        <table width="50%" height="91" border="0" cellpadding="0"
               cellspacing="0">
            <tr>
                <td height="30">
                    <div align="right">
                        <font color="#FF0000">*</font>原密码:
                    </div>
                </td>
                <td>
                    <input name="oldPassword" type="password" class="text1"
                           id="oldPassword" size="25" onblur="oldPasswordValidate(this)">
                    <div id="oldPasswordChecked"></div>
                </td>
            </tr>
            <tr>
                <td height="27">
                    <div align="right">
                        <font color="#FF0000">*</font>新密码:
                    </div>
                </td>
                <td>
                    <input name="newPassword" type="password" class="text1"
                           id="newPassword" size="25">
                </td>
            </tr>
            <tr>
                <td height="34">
                    <div align="right">
                        <font color="#FF0000">*</font>确认密码:
                    </div>
                </td>
                <td>
                    <input name="affirmNewPassword" type="password" class="text1"
                           id="affirmNewPassword" size="25">
                </td>
            </tr>
        </table>
        <hr width="100%" align="center" size=0>
        <p>
            <input name="btnModify" class="button1" type="button"
                   id="btnModify" value="修改" onClick="modifyPassword()">
            &nbsp; &nbsp;&nbsp;
        </p>
    </div>
</form>
</body>
</html>
