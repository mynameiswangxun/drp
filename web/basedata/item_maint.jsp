<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>物料维护</title>
		<link rel="stylesheet" href="../style/drp.css">
		<script src="../script/windows.js"></script>
		<script type="text/javascript">
	function addItem() {
		window.self.location = "ShowAddItemServlet.servlet";
	}
	
	function modifyItem() {
		window.self.location = "item_modify.html";
	}
	
	function deleteItem() {
		
	}
	
	function topPage() {
	
	}
	
	function previousPage() {
	
	}
	
	function nextPage() {
	
	}
	
	function bottomPage() {
	
	}
	
	function checkAll() {
	
	}
	
	function uploadPic4Item() {
		window.self.location = "item_upload.html"
	}
</script>
	</head>

	<body class="body1">
		<form name="itemForm">
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
							<img src="../images/mark_arrow_02.gif" alt="我" width="14"
								height="14">
							&nbsp;
							<b>基础数据管理&gt;&gt;物料维护</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="17%" height="29">
							<div align="left">
								物料代码/名称:
							</div>
						</td>
						<td width="57%">
							<input name="clientIdOrName" type="text" class="text1"
								id="clientId4" size="50" maxlength="50">
						</td>
						<td width="26%">
							<div align="left">
								<input name="btnQuery" type="button" class="button1"
									id="btnQuery" value="查询">
							</div>
						</td>
					</tr>
					<tr>
						<td height="16">
							<div align="right"></div>
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<div align="right"></div>
						</td>
					</tr>
				</table>

			</div>
			<table width="95%" border="0" cellspacing="0" cellpadding="0"
				class="rd1" align="center">
				<tr>
					<td nowrap height="10" class="p2">
						物料信息
					</td>
					<td nowrap height="10" class="p3">
						&nbsp;
					</td>
				</tr>
			</table>
			<table width="95%" border="1" cellspacing="0" cellpadding="0"
				align="center" class="table1">
				<tr>
					<td width="35" class="rd6">
						<input type="checkbox" name="ifAll" onClick="checkAll()">
					</td>
					<td width="155" class="rd6">
						物料代码
					</td>
					<td width="155" class="rd6">
						物料名称
					</td>
					<td width="155" class="rd6">
						物料规格
					</td>
					<td width="155" class="rd6">
						物料型号
					</td>
					<td width="138" class="rd6">
						类别
					</td>
					<td width="101" class="rd6">
						计量单位
					</td>
				</tr>
				<tr>

					<td class="rd8">
						<input type="checkbox" name="selectFlag" class="checkbox1">
					</td>
					<td class="rd8">
						<a href="#"
							onClick="window.open('item_detail.html', '物料详细信息', 'width=400, height=400, scrollbars=no')">2001</a>
					</td>
					<td class="rd8">
						青霉素
					</td>
					<td class="rd8">
						xxxxx
					</td>
					<td class="rd8">
						xxxxx
					</td>
					<td class="rd8">
						西药
					</td>
					<td class="rd8">
						盒
					</td>
				</tr>
				<tr>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
					<td class="rd8">
						&nbsp;
					</td>
				</tr>
			</table>
			<table width="95%" height="30" border="0" align="center"
				cellpadding="0" cellspacing="0" class="rd1">
				<tr>
					<td nowrap class="rd19" height="2" width="36%">
						<div align="left">
							<font color="#FFFFFF">&nbsp;共&nbspxx&nbsp页</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="#FFFFFF">当前第</font>&nbsp
							<font color="#FF0000">x</font>&nbsp
							<font color="#FFFFFF">页</font>
						</div>
					</td>
					<td nowrap class="rd19" width="64%">
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
								value="添加" onClick="addItem()">
							<input name="btnDelete" class="button1" type="button"
								id="btnDelete" value="删除" onClick="deleteItem()">
							<input name="btnModify" class="button1" type="button"
								id="btnModify" value="修改" onClick="modifyItem()">
							<input name="btnUpload" class="button1" type="button"
								id="btnUpload" value="上传图片" onClick="uploadPic4Item()">
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
