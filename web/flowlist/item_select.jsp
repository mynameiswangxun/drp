<%@ page import="drp.basedata.domain.Item" %>
<%@ page import="drp.util.pagemodel.PageModel" %>
<%@ page import="java.util.List" %>
<%@ page import="drp.util.factory.BeanFactory" %>
<%@ page import="drp.basedata.manager.ItemManager" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    request.setCharacterEncoding("utf-8");
    int pageNo = 1;
    int pageSize = 6;
    String condition = "";
    if (request.getParameter("pageNo") != null) {
        pageNo = Integer.parseInt(request.getParameter("pageNo"));
    }
    if (request.getParameter("condition") != null) {
        condition = request.getParameter("condition");
    }
    PageModel<Item> pageModel =
            ((ItemManager) BeanFactory.getInstance().getServiceObject(ItemManager.class)).findItemList(pageNo, pageSize, condition);
    List<Item> items = pageModel.getList();
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>请选择物料</title>
    <link rel="stylesheet" href="../style/drp.css">
    <script src="../script/client_validate.js"></script>
    <script type="text/javascript">
        function topPage() {
            window.self.location="item_select.jsp?pageNo=<%=pageModel.getTopPageNo()%>&condition=<%=condition%>";
        }

        function previousPage() {
            window.self.location="item_select.jsp?pageNo=<%=pageModel.getPreviousPageNo()%>&condition=<%=condition%>";
        }

        function nextPage() {
            window.self.location="item_select.jsp?pageNo=<%=pageModel.getNextPageNo()%>&condition=<%=condition%>";
        }

        function bottomPage() {
            window.self.location="item_select.jsp?pageNo=<%=pageModel.getBottomPageNo()%>&condition=<%=condition%>";
        }

        function queryClient() {
            window.self.location="item_select.jsp?condition="+document.getElementById("itemNoOrName").value;
        }

        function selectOk() {
            window.close();
        }

    </script>
</head>

<body class="body1">
<form name="selectItemForm">
    <div align="center">
        <table width="95%" border="0" cellspacing="0" cellpadding="0"
               height="34">
            <tr>
                <td width="522" class="p1" height="34" nowrap>
                    <img src="../images/search.gif" width="32" height="32">
                    &nbsp;
                    <b>请选择物料</b>
                </td>
            </tr>
        </table>
        <hr width="100%" align="center" size=0>
        <table width="95%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td width="17%" height="29">
                    <div align="left">
                        物料代码/名称:
                    </div>
                </td>
                <td width="57%">
                    <input name="itemNoOrName" type="text" class="text1"
                           id="itemNoOrName" size="50" maxlength="50">
                </td>
                <td width="26%">
                    <div align="left">
                        <input name="btnQuery" type="button" class="button1"
                               id="btnQuery" value="查询" onclick="queryClient()">
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
                选择
            </td>
            <td width="170" class="rd6">
                物料代码
            </td>
            <td width="222" class="rd6">
                物料名称
            </td>
            <td width="195" class="rd6">
                物料规格
            </td>
            <td width="293" class="rd6">
                物料型号
            </td>
            <td width="293" class="rd6">
                类别
            </td>
            <td width="293" class="rd6">
                计量单位
            </td>
        </tr>
        <%
            for (Item item :
                    items) {

        %>
        <tr>
            <td width="35" class="rd8">
                <input type="radio" name="selectFlag" value="<%=item.getItemId()%>"
                       onDblClick="selectOk()">
            </td>
            <td width="170" class="rd8">
                <%=item.getItemId()%>
            </td>
            <td width="222" class="rd8">
                <%=item.getItemName()%>
            </td>
            <td width="195" class="rd8">
                <%=item.getSpec()==null?"":item.getSpec()%>
            </td>
            <td width="293" class="rd8">
                <%=item.getItemPattern()==null?"":item.getItemPattern()%>
            </td>
            <td width="293" class="rd8">
                <%=item.getItemCategory().getName()%>
            </td>
            <td width="293" class="rd8">
                <%=item.getItemUnit().getName()%>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <table width="95%" height="30" border="0" align="center"
           cellpadding="0" cellspacing="0" class="rd1">
        <tr>
            <td nowrap class="rd19" height="2" width="36%">
                <div align="left">
                    <font color="#FFFFFF">&nbsp;共&nbsp<%=pageModel.getTopPageNo()%>&nbsp页</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <font color="#FFFFFF">当前第</font>&nbsp
                    <font color="#FF0000"><%=pageModel.getPageNo()%>
                    </font>&nbsp
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
                    <input name="btnOk" class="button1" type="button" id="btnOk"
                           value="确定" onClick="selectOk()">
                    <input name="btnClose" class="button1" type="button"
                           id="btnClose" value="关闭" onClick="window.close()">
                </div>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
