<%@ page import="drp.basedata.manager.FiscalTimeManager" %>
<%@ page import="drp.util.factory.BeanFactory" %>
<%@ page import="drp.basedata.domain.FiscalTime" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
	FiscalTimeManager fiscalTimeManager =
			(FiscalTimeManager) BeanFactory.getInstance().getServiceObject(FiscalTimeManager.class);
	String id = request.getParameter("id");
	FiscalTime fiscalTime = fiscalTimeManager.findFiscalTimeById(Integer.parseInt(id));
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	String errorMessage = "";
	if("modify".equals(request.getParameter("command"))){
	    fiscalTime.setFiscalYear(Integer.parseInt(request.getParameter("fiscalYear")));
		fiscalTime.setFiscalMonth(Integer.parseInt(request.getParameter("fiscalPeriod")));
		fiscalTime.setBeginDate(simpleDateFormat.parse(request.getParameter("beginDate")));
		fiscalTime.setEndDate(simpleDateFormat.parse(request.getParameter("endDate")));
		fiscalTime.setStatus("checked".equals(request.getParameter("periodSts"))?"Y":"N");
		try{
			fiscalTimeManager.modifyFiscalTime(fiscalTime);
		} catch (Exception e){
		    e.printStackTrace();
		    errorMessage = e.getMessage();
		}
		if("".equals(errorMessage)){
		    errorMessage = "修改会计核算期成功";
		}
	}
%>
<%
	if(!("".equals(errorMessage) || errorMessage==null)){
%>
<script type="text/javascript">
	alert("<%=errorMessage%>");
</script>
<%
	}
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>修改会计核算期间</title>
		<link rel="stylesheet" href="../style/drp.css">
		<script src="../script/calendar.js"></script>
		<script src="../script/client_validate.js"></script>
		<script type="text/javascript">
            function goModify(){
                var fiscalYear = document.getElementById("fiscalYear");
                if(isEmpty(trim(fiscalYear.value))){
                    alert("会计核算年不能为空!");
                    fiscalYear.focus();
                    return;
				}
				if(!isinteger(trim(fiscalYear.value))){
                    alert("会计核算年格式不正确!");
                    fiscalYear.focus();
                    return;
				}
                if(parseInt(trim(fiscalYear.value))<0 || parseInt(trim(fiscalYear.value))>9999){
                    alert("会计核算年格式不正确!");
                    fiscalYear.focus();
                    return;
                }
                var fiscalPeriod = document.getElementById("fiscalPeriod");
                if(isEmpty(trim(fiscalYear.value))){
                    alert("会计核算月不能为空!");
                    fiscalPeriod.focus();
                    return;
                }
                if(!isinteger(trim(fiscalPeriod.value))){
                    alert("会计核算月格式不正确!");
                    fiscalPeriod.focus();
                    return;
                }
                if(parseInt(trim(fiscalPeriod.value))<1 || parseInt(trim(fiscalPeriod.value))>12){
                    alert("会计核算月格式不正确!");
                    fiscalPeriod.focus();
                    return;
				}
				var beginDate = document.getElementById("beginDate");
                var endDate = document.getElementById("endDate");
                if(beginDate.value>endDate.value){
                    alert("开始日期在结束日期之前!");
                    return;
				}
				var yearOfBeginDate = beginDate.value.substring(0,beginDate.value.indexOf("-"));
                var monthOfBeginDate = beginDate.value.substring(beginDate.value.indexOf("-")+1,beginDate.value.lastIndexOf("-"));
                var yearOfEndDate = endDate.value.substring(0,endDate.value.indexOf("-"));
                var monthOfEndDate = endDate.value.substring(endDate.value.indexOf("-")+1,endDate.value.lastIndexOf("-"));


                if(!(parseInt(yearOfBeginDate)==parseInt(fiscalYear.value) && parseInt(yearOfEndDate)==parseInt(fiscalYear.value)
						&& parseInt(monthOfBeginDate)==parseInt(fiscalPeriod.value) && parseInt(monthOfEndDate)==parseInt(fiscalPeriod.value))){
                    alert("开始日期或结束日期不在会计核算期内!");
                    return;
				}

				var fiscalYearPeriodForm = document.getElementById("fiscalYearPeriodForm");
                fiscalYearPeriodForm.method = "post";
                fiscalYearPeriodForm.action = "fiscal_year_period_modify.jsp";
                fiscalYearPeriodForm.submit();
			}
		</script>
	</head>

	<body class="body1">
		<form name="fiscalYearPeriodForm" target="_self"
			id="fiscalYearPeriodForm">
			<input type="hidden" name="command" value="modify">
			<input type="hidden" name="id" value="<%=id%>">
			<div align="center">
				<table width="95%" border="0" cellspacing="2" cellpadding="2">
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="../images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>基础数据管理&gt;&gt;会计核算期间维护&gt;&gt;修改</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="22%" height="29">
							<div align="right">
								核算年:&nbsp;
							</div>
						</td>
						<td width="78%">
							<input name="fiscalYear" type="text" class="text1"
								id="fiscalYear" size="10" maxlength="10" readonly="true" value="<%=fiscalTime.getFiscalYear()%>">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								核算月:&nbsp;
							</div>
						</td>
						<td>
							<input name="fiscalPeriod" type="text" class="text1"
								id="fiscalPeriod" size="10" maxlength="10" readonly="true" value="<%=fiscalTime.getFiscalMonth()%>">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>开始日期:&nbsp;
							</div>
						</td>
						<td>
							<label>
								<input type="text" name="beginDate" size="10" maxlength="10" id="beginDate"
									 readonly="true" value="<%=simpleDateFormat.format(fiscalTime.getBeginDate())%>" onClick=SelectDate(this)>
							</label>
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>结束日期:&nbsp;
							</div>
						</td>
						<td>
							<input name="endDate" type="text" id="endDate" size="10" id="endDate"
								maxlength="10" readonly="true" value="<%=simpleDateFormat.format(fiscalTime.getEndDate())%>" onClick=SelectDate(this)>
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>是否可用:&nbsp;
							</div>
						</td>
						<td>
							<input name="periodSts" type="checkbox" class="checkbox1"
								id="periodSts" value="checked" <%="Y".equals(fiscalTime.getStatus())?"checked":""%>>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<div align="center">
					<input name="btnModify" class="button1" type="button"
						id="btnModify" value="修改" onclick="goModify()">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onClick="self.location='fiscal_year_period_maint.jsp'">
				</div>
			</div>
		</form>
	</body>
</html>
