<%@ page import="drp.systemmgr.manager.UserManager" %>
<%@ page import="drp.util.domian.PageModel" %>
<%@ page import="java.util.List" %>
<%@ page import="drp.systemmgr.domain.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	//request.setCharacterEncoding("utf-8");
	UserManager userManager = UserManager.getInstance();
	if("delete".equals(request.getParameter("command"))){
		String[] userIds = request.getParameterValues("selectFlag");
		userManager.deleteUser(userIds);
	}
	int pageNo = 1;
	int pageSize = 6;
	if(request.getParameter("pageNo")!=null){
	    pageNo = Integer.parseInt(request.getParameter("pageNo"));
	}
	PageModel<User> pageModel = userManager.findUserList(pageNo,pageSize);

%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>用户维护</title>
		<link rel="stylesheet" href="../style/drp.css">
		<script type="text/javascript">
	
	function addUser() {
		window.self.location = "user_add.jsp";
	}
	
	function modifyUser() {
        var checkboxes = document.getElementsByName("selectFlag");
        var flag = 0;
        //记录被选择的checkboxes
        var j = 0;
        for(var i = 0; i<checkboxes.length; i++){
            if(checkboxes[i].checked){
                j = i;
                flag++;
            }
            if(flag>1){
                alert("一次只能修改一个用户!");
                return;
			}
        }
        if(flag == 0){
            alert("请选择需要修改的用户!");
            return;
		}
		window.self.location = "user_modify.jsp?userId="+checkboxes[j].value;
	}
	
	function deleteUser() {
		var checkboxes = document.getElementsByName("selectFlag");
		var flag = false;
        for(var i = 0; i<checkboxes.length; i++){
           if(checkboxes[i].checked){
				flag = true;
				break;
		   }
        }
        if(!flag){
            alert("请选择需要删除的用户");
            return;
		}else {
            if (window.confirm("确认删除吗?")){
               var userform = document.getElementById("userform");
               userform.action="user_maint.jsp?command=delete";
               userform.method="post";
               userform.submit();
            }
		}
	}
		
	function checkAll(field) {
		var checkboxes = document.getElementsByName("selectFlag");
		for(var i = 0; i<checkboxes.length; i++){
		    checkboxes[i].checked = field.checked;
		}

	}

	function topPage() {
		window.self.location = "user_maint.jsp?pageNo=<%=pageModel.getTopPageNo()%>";
	}
	
	function previousPage() {
        window.self.location = "user_maint.jsp?pageNo=<%=pageModel.getPreviousPageNo()%>";
	}
	
	function nextPage() {
        window.self.location = "user_maint.jsp?pageNo=<%=pageModel.getNextPageNo()%>";
	}
	
	function bottomPage() {
        window.self.location = "user_maint.jsp?pageNo=<%=pageModel.getBottomPageNo()%>";
	}

</script>
	</head>

	<body class="body1">
		<form name="userform" id="userform">
			<div align="center">
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="35">
					<tr>
						<td class="p1" height="18" nowrap>&nbsp;
							
						</td>
					</tr>
					<tr>
						<td width="522" class="p1" height="17" nowrap>
							<img src="../images/mark_arrow_02.gif" width="14" height="14">
							&nbsp;
							<b>系统管理&gt;&gt;用户维护</b>
						</td>
					</tr>
				</table>
				<hr width="100%" align="center" size=0>
			</div>
			<table width="95%" height="20" border="0" align="center"
				cellspacing="0" class="rd1" id="toolbar">
				<tr>
					<td width="49%" class="rd19">
						<font color="#FFFFFF">查询列表</font>
					</td>
					<td width="27%" nowrap class="rd16">
						<div align="right"></div>
					</td>
				</tr>
			</table>
			<table width="95%" border="1" cellspacing="0" cellpadding="0"
				align="center" class="table1">
				<tr>
					<td width="55" class="rd6">
						<input type="checkbox" name="ifAll" onClick="checkAll(this)">
					</td>
					<td width="119" class="rd6">
						用户代码
					</td>
					<td width="152" class="rd6">
						用户名称
					</td>
					<td width="166" class="rd6">
						联系电话
					</td>
					<td width="150" class="rd6">
						email
					</td>
					<td width="153" class="rd6">
						创建日期
					</td>
				</tr>
				<!--循环开始-->
				<%
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
					List<User> userList = pageModel.getList();
					for(int i = 0; i<userList.size(); i++){
				%>
				<tr>
					<td class="rd8">
						<input type="checkbox" name="selectFlag" class="checkbox1"
							value=<%=userList.get(i).getId()%>
					</td>
					<td class="rd8">
						<%=userList.get(i).getId()%>
					</td>
					<td class="rd8">
						<%=userList.get(i).getUsername()%>
					</td>
					<td class="rd8">
						<%=userList.get(i).getContactTel()==null?"":userList.get(i).getContactTel()%>
					</td>
					<td class="rd8">
						<%=userList.get(i).getEmail()==null?"":userList.get(i).getEmail()%>
					</td>
					<td class="rd8">
						<%=userList.get(i).getCreateDate()==null?"":
								simpleDateFormat.format(userList.get(i).getCreateDate())%>
					</td>
				</tr>
				<!--循环结束-->
				<%
					}
				%>
			</table>
			<table width="95%" height="30" border="0" align="center"
				cellpadding="0" cellspacing="0" class="rd1">
				<tr>
					<td nowrap class="rd19" height="2">
						<div align="left">
							<font color="#FFFFFF">&nbsp;共&nbsp<%=pageModel.getTotalPageNum()%>&nbsp页</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="#FFFFFF">当前第</font>&nbsp
							<font color="#FF0000"><%=pageModel.getPageNo()%></font>&nbsp
							<font color="#FFFFFF">页</font>
						</div>
					</td>
					<td nowrap class="rd19">
						<div align="right">
							<input name="btnTopPage" class="button1" type="button"
								id="btnTopPage" value="|&lt;&lt; " title="首页"
								onClick="topPage()">
							<input name="btnPreviousPage" class="button1" type="button"
								id="btnPreviousPage" value=" &lt;  " title="上页"
								onClick="previousPage()">
							<input name="btnNextPage" class="button1" type="button"
								id="btnNextPage" value="  &gt; " title="下页" onClick="nextPage()">
							<input name="btnBottomPage" class="button1" type="button"
								id="btnBottomPage" value=" &gt;&gt;|" title="尾页"
								onClick="bottomPage()">
							<input name="btnAdd" type="button" class="button1" id="btnAdd"
								value="添加" onClick="addUser()">
							<input name="btnDelete" class="button1" type="button"
								id="btnDelete" value="删除" onClick="deleteUser()">
							<input name="btnModify" class="button1" type="button"
								id="btnModify" value="修改" onClick="modifyUser()">
						</div>
					</td>
				</tr>
			</table>
			<p>&nbsp;
				
			</p>
		</form>
	</body>
</html>
