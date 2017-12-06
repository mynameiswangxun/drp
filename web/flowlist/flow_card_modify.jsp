<%@ page import="drp.flowlist.domain.FlowList" %>
<%@ page import="drp.basedata.domain.AimClient" %>
<%@ page import="drp.basedata.domain.Item" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    FlowList flowList = (FlowList)request.getAttribute("flowList");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>添加流向单维护</title>
    <link rel="stylesheet" href="../style/drp.css">
    <script src="../script/calendar.js"></script>
    <script src="../script/client_validate.js"></script>
    <script language="javascript">
        var rowIndex = <%=flowList.getFlowDetails().size()%>;


        function selectAimClient(field) {
//            alert(field.getAttribute("index"));
            window.open('aim_client_select.jsp?index=' + field.getAttribute("index"), '请选择需方客户', 'width=700, height=400, scrollbars=no');
        }

        function selectItem(field) {
//            alert(field.getAttribute("index"));
            window.open('item_select.jsp?index=' + field.getAttribute("index"), '请选择物料', 'width=700, height=400, scrollbars=no');
        }

        function addOneLineOnClick() {
            var row = tblFlowCardDetail.insertRow(tblFlowCardDetail.rows.length);
            var col = row.insertCell(0);
            col.innerHTML = "<input type=\"hidden\" name=\"aimInnerId\" id=\"aimInnerId\"><input readonly=\"true\" maxLength=6 size=6 name=aimId><input type=button  value =...   name=btnSelectAimClient index=\"" + rowIndex + "\" onclick=\"selectAimClient(this)\">";
            col = row.insertCell(1);
            col.innerHTML = "<input id=aimName name=aimName size=25 maxlength=25 readonly=\"true\">";
            col = row.insertCell(2);
            col.innerHTML = "<input readonly=\"true\" maxLength=6 size=6 name=itemNo><input type=button  value =...   name=btnSelectItem index=\"" + rowIndex + "\" onclick=\"selectItem(this)\">";
            col = row.insertCell(3);
            col.innerHTML = "<input id=itemName name=itemName size=25 maxlength=25 readonly=\"true\">";
            col = row.insertCell(4);
            col.innerHTML = "<input id=spec name=spec size=10 maxlength=10 readonly=\"true\">";
            col = row.insertCell(5);
            col.innerHTML = "<input id=pattern name=pattern size=10 maxlength=10 readonly=\"true\">";
            col = row.insertCell(6);
            col.innerHTML = "<input id=unit name=unit size=4 maxlength=4 readonly=\"true\">";
            col = row.insertCell(7);
            col.innerHTML = "<input id=qty name=qty size=6 maxlength=6>";
            col = row.insertCell(8);
            col.innerHTML = "<input type='button' value='删除' id=btnDeleteLine name=btnDeleteLine onclick=\"return DeleteRow('row" + rowIndex + "')\">";
            row.setAttribute("id", "row" + rowIndex);
            rowIndex++;
        }

        function DeleteRow(rowTag) {
            var i = tblFlowCardDetail.rows[rowTag].rowIndex;
            var j;
            for (j = i; j < rowIndex; j++) {
//                tblFlowCardDetail.rows[j].cells[0].getElementsByName("btnSelectAimClient").index--;
//                tblFlowCardDetail.rows[j].cells[2].getElementsByName("btnSelectAimClient").index--;
                var btnSelectAimClient = document.getElementsByName("btnSelectAimClient");
                var btnSelectItem = document.getElementsByName("btnSelectItem");
                btnSelectAimClient[j].setAttribute("index", (parseInt(btnSelectAimClient[j].getAttribute("index")) - 1).toString());
                btnSelectItem[j].setAttribute("index", (parseInt(btnSelectItem[j].getAttribute("index")) - 1).toString());
//                alert(btnSelectAimClient[j].getAttribute("index"));
            }
            tblFlowCardDetail.deleteRow(i);
            rowIndex--;
        }

        function modifyFlowCard() {
            var aimInnerId = document.getElementsByName("aimInnerId");
            if (aimInnerId.length == 0) {
                alert("请选择需方客户!");
                return;
            }
            for (var i = 0; i < aimInnerId.length; i++) {
                if (aimInnerId[i].value == "") {
                    alert("请选择需方客户!");
                    return;
                }
            }
            var itemNo = document.getElementsByName("itemNo");
            for (var i = 0; i < itemNo.length; i++) {
                if (itemNo[i].value == "") {
                    alert("请选择物料!");
                    return;
                }
            }
            var qty = document.getElementsByName("qty");
            for (var i = 0; i < qty.length; i++) {
                if (qty[i].value == "") {
                    alert("操作数量不能为空!");
                    return;
                }
                if (!isnumber(qty[i].value)) {
                    alert("操作数量必须为数字!");
                    return;
                }
            }

            var aIdAndiNo = new Array();
            for (var i = 0; i < aimInnerId.length; i++) {
                aIdAndiNo[i] = aimInnerId[i].value + itemNo[i].value;
            }
            for (var i = 0; i < aIdAndiNo.length; i++) {
                for (var j = 0; j < aIdAndiNo.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (aIdAndiNo[i] == aIdAndiNo[j]) {
                        alert("相同需方与物料不能分写两栏!");
                        return;
                    }
                }
            }

            var flowCardModifyForm = document.getElementById("flowCardModifyForm");
            flowCardModifyForm.action = "FlowListServlet.servlet?command=modify";
            flowCardModifyForm.submit();
        }

        function goBack() {
            window.self.location = "FlowListServlet.servlet"
        }
    </script>
</head>

<body class="body1">
<div align="center">
    <form name="flowCardModifyForm" method="post" id="flowCardModifyForm">
        <input type="hidden" name="flowNum" value="<%=flowList.getFlowNum()%>">
        <table width="95%" border="0" cellspacing="2" cellpadding="2">
            <tr>
                <td>
                    &nbsp;
                </td>
            </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="522" class="p1" height="2" nowrap>
                    <img src="../images/mark_arrow_03.gif" width="14" height="14">
                    &nbsp;
                    <b>分销商库存管理&gt;&gt;流向单维护&gt;&gt;修改</b>
                </td>
            </tr>
        </table>
        <hr width="97%" align="center" size=0>
        <table width="95%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td width="11%" height="29">
                    <div align="right">
                        流向单号:
                    </div>
                </td>
                <td width="8%">
                    <%=flowList.getFlowNum()%>
                </td>
                <td width="14%">
                    <div align="right">
                        <font color="#FF0000">*</font>供方分销商代码:&nbsp;
                    </div>
                </td>
                <td width="16%">
                    <input type="hidden" id="cid" name="cid" value="<%=flowList.getClient().getId()%>">
                    <input name="clientId" type="text" class="text1" id="clientId"
                           size="10" maxlength="10" readonly="true" value="<%=flowList.getClient().getClientId()%>">
                </td>
                <td width="13%">
                    <div align="right">
                        <div align="right">
                            供方分销商名称:&nbsp;
                        </div>
                    </div>
                </td>
                <td width="29%">
                    <input name="clientName" type="text" class="text1"
                           id="clientName" size="40" maxlength="40" readonly="true" value="<%=flowList.getClient().getName()%>">
                </td>
                <td width="5%">
                    &nbsp;
                </td>
                <td width="4%">
                    &nbsp;
                </td>
            </tr>
        </table>
        <hr width="97%" align="center" size=0>
        <table width="95%" border="0" cellpadding="0" cellspacing="0"
               id="tblFlowCardDetail">
            <tr>
                <td nowrap>
                    <div align="left">
                        <font color="#FF0000">*</font>需方客户代码
                    </div>
                </td>
                <td nowrap>
                    <div align="left">
                        需方客户名称
                    </div>
                </td>
                <td nowrap>
                    <div align="left">
                        <font color="#FF0000">*</font>物料代码
                    </div>
                </td>
                <td nowrap>
                    <div align="left">
                        物料名称
                    </div>
                </td>
                <td nowrap>
                    规格
                </td>
                <td nowrap>
                    型号
                </td>
                <td nowrap>
                    计量单位
                </td>
                <td nowrap>
                    <font color="#FF0000">*</font>操作数量
                </td>
                <td nowrap>
                    <div align="left">
                        删除
                    </div>
                </td>
            </tr>
            <%
                for(int i=0;i<flowList.getFlowDetails().size();i++){
                    AimClient aimClient = flowList.getFlowDetails().get(i).getAimClient();
                    Item item = flowList.getFlowDetails().get(i).getItem();
            %>
            <tr id="row<%=i %>">
                <td>
                    <input type="hidden" name="aimInnerId" id="aimInnerId" value="<%=aimClient.getId()%>">
                    <input readonly="true" maxLength=6 size=6 name=aimId value="<%=aimClient.getClientID()%>">
                    <input type=button  value =...   name=btnSelectAimClient index="0" onclick="selectAimClient(this)">
                </td>
                <td>
                    <input id=aimName name=aimName size=25 maxlength=25 readonly="true" value="<%=aimClient.getName()%>">
                </td>
                <td>
                    <input readonly="true" maxLength=6 size=6 name=itemNo value="<%=item.getItemId()%>">
                    <input type=button  value =...   name=btnSelectItem index="0" onclick="selectItem(this)">
                </td>
                <td>
                    <input id=itemName name=itemName size=25 maxlength=25 readonly="true" value="<%=item.getItemName()%>">
                </td>
                <td>
                    <input id=spec name=spec size=10 maxlength=10 readonly="true" value="<%=item.getSpec()%>">
                </td>
                <td>
                    <input id=pattern name=pattern size=10 maxlength=10 readonly="true" value="<%=item.getItemPattern()%>">
                </td>
                <td>
                    <input id=unit name=unit size=4 maxlength=4 readonly="true" value="<%=item.getItemUnit()==null?"":item.getItemUnit().getName()%>">
                </td>
                <td>
                    <input id=qty name=qty size=6 maxlength=6 value="<%=flowList.getFlowDetails().get(i).getOpNum()%>">
                </td>
                <td>
                    <input type='button' value='删除' id=btnDeleteLine name=btnDeleteLine onclick="return DeleteRow('row<%=i%>')">
                </td>
            </tr>
            <%
                }
            %>
        </table>
        <p>
            <input name="btnModifyLine" type="button" id="btnModifyLine"
                   onClick="return addOneLineOnClick()" value="加入一行">
            <input name="btnSave" type="button" id="btnSave" value="保存"
                   onClick="modifyFlowCard()">
            <input name="btnBack" type="button" id="btnBack" onClick="goBack()"
                   value="返回">
        </p>
        <p>
            &nbsp;
        </p>
        <p>
            &nbsp;
        </p>
    </form>
    <p>
        &nbsp;
    </p>
</div>
</body>
</html>
