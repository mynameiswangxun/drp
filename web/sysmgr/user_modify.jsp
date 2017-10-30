<%@ page import="drp.systemmgr.domain.User" %>
<%@ page import="drp.systemmgr.manager.UserManager" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
	//request.setCharacterEncoding("utf-8");
	String userId = request.getParameter("userId");
	User user = UserManager.getInstance().findUserById(userId);
	if("modify".equals(request.getParameter("command"))){
		user.setUsername(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));
		user.setContactTel(request.getParameter("contactTel"));
		user.setEmail(request.getParameter("email"));
		UserManager.getInstance().modifyUser(user);
		out.print("修改用户成功");
	}
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>修改用户</title>
		<link rel="stylesheet" href="../style/drp.css">
		<script src="../script/client_validate.js"></script>
		<script type="text/javascript">
	function goBack() {
		window.self.location ="user_maint.jsp"
	}
	
	function modifyUser() {
        var vUserID = document.getElementById("userId").value;
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
        var re = new RegExp(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/);
        if (trim(vEmail).length > 0) {
            if (!re.test(trim(vEmail))) {
                alert("请输入正确的邮箱");
                document.getElementById("email").focus();
                return;
            }
        }
       	var userForm = document.getElementById("userForm");
        userForm.action="user_modify.jsp";
        userForm.method="post";
        userForm.submit();
	}
	
</script>

	</head>

	<body class="body1">
		<form name="userForm" id="userForm"><input type="hidden" name="command" value="modify">
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
							<b>系统管理&gt;&gt;用户维护&gt;&gt;修改</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="22%" height="29">
							<div align="right">
								用户代码:&nbsp;
							</div>
						</td>
						<td width="78%">
							<input name="userId" type="text" class="text1" id="userId"
								size="10" maxlength="10" readonly="true" value="<%=user.getId()%>">
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
								size="20" maxlength="20" value="<%=user.getUsername()%>">
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
									id="password" size="20" maxlength="20" value="<%=user.getPassword()%>">
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
								id="contactTel" size="20" maxlength="20" value="<%=user.getContactTel()%>">
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
								size="20" maxlength="20" value="<%=user.getEmail()%>">
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<div align="center">
					<input name="btnModify" class="button1" type="button"
						id="btnModify" value="修改" onClick="modifyUser()">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onClick="goBack()" />
				</div>
			</div>
		</form>
	</body>
</html>
