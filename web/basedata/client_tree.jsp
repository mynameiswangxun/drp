<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GB18030">
    <link rel="stylesheet" href="../style/drp.css">
    <style type="text/css">
        <!--
        a:link {
            text-decoration: none;
            color: #000000;
            font-size: 9pt;
            font-family: 宋体;
        }

        a:visited {
            text-decoration: none;
            color: #000000;
            font-size: 9pt;
            font-family: 宋体;

        }

        a:hover {
            text-decoration: none;
            color: #000000;
            font-size: 9pt;
            font-family: 宋体;

        }

        a:active {
            text-decoration: none;
            color: #000000;
            font-size: 9pt;
            font-family: 宋体;
        }

        -->
    </style>

    <script language="JavaScript">
        <!--
        function display(id) {
            eval("var div=div" + id);
            eval("var img=img" + id);
            eval("var im=im" + id);
            div.style.display = div.style.display == "block" ? "none" : "block";
            img.src = div.style.display == "block" ? "../images/minus.gif" : "../images/plus.gif";
            im.src = div.style.display == "block" ? "../images/openfold.gif" : "../images/closedfold.gif";
            img.alt = div.style.display == "block" ? "关闭" : "展开";
        }

        //-->
    </script>
</head>
<body class="body1">
<table>
    <tr>
        <td valign="top" nowrap="nowrap">
            <div>
                <img alt="展开" style="cursor:hand;" onClick="display('1');"
                     id="img1" src="../images/plus.gif">
                <img id="im1" src="../images/closedfold.gif">
                <a href="client_node_crud.html" target="clientDispAreaFrame">所有分销商</a>
                <div style="display:none;" id="div1">
                    <div>
                        <img src="../images/white.gif">
                        <img alt="展开" style="cursor:hand;" onClick="display('2');"
                             id="img2" src="../images/plus.gif">
                        <img id="im2" src="../images/closedfold.gif">
                        <a href="client_node_crud.html" target="clientDispAreaFrame">华北区</a>
                        <div style="display:none;" id="div2">
                            <div>
                                <img src="../images/white.gif">
                                <img src="../images/white.gif">
                                <img alt="展开" style="cursor:hand;" onClick="display('3');"
                                     id="img3" src="../images/plus.gif">
                                <img id="im3" src="../images/closedfold.gif">
                                <a href="client_node_crud.html" target="clientDispAreaFrame">北京市</a>
                                <div style="display:none;" id="div3">
                                    <div>
                                        <img src="../images/white.gif">
                                        <img src="../images/white.gif">
                                        <img src="../images/white.gif">
                                        <img src="../images/minus.gif">
                                        <img src="../images/openfold.gif">
                                        <a href="client_crud.html" target="clientDispAreaFrame">北京医药股份有限公司</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <img src="../images/white.gif">
                        <img src="../images/minus.gif">
                        <img src="../images/openfold.gif">
                        <a href="client_node_crud.html" target="clientDispAreaFrame">东北区</a>
                    </div>
                    <div>
                        <img src="../images/white.gif">
                        <img src="../images/minus.gif">
                        <img src="../images/openfold.gif">
                        <a href="client_node_crud.html" target="clientDispAreaFrame">华南区</a>
                    </div>
                </div>
            </div>
        </td>
    </tr>
</table>
</body>
</html>

