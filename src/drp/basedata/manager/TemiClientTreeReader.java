package drp.basedata.manager;

import drp.util.database.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TemiClientTreeReader {
    private StringBuffer treeHtmlString = new StringBuffer();
    private Connection connection = null;

    /**
     * 返回HTML字符串
     *
     * @return
     */
    public String getClientTreeHtmlString() {
        connection = DBUtil.getConnection();
        readTemiClientTree(0,0);
        Connection connection = null;
        return treeHtmlString.toString();
    }

    private void readTemiClientTree(int pid, int level) {
        String sql = "SELECT * FROM temi_client WHERE pid=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pid);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                treeHtmlString.append("<div>\n");
                for (int i = 0; i < level; i++) {
                    treeHtmlString.append("<img src=\"../images/white.gif\">\n");
                }
                if ("N".equals(resultSet.getString("is_leaf"))) {
                    treeHtmlString.append("<img alt=\"展开\" style=\"cursor:hand;\" onClick=\"display('" +
                            resultSet.getInt("id") + "');\"" +
                            "id=\"img" + resultSet.getInt("id") + "\" src=\"../images/plus.gif\">\n");
                    treeHtmlString.append("<img id=\"im" + resultSet.getInt("id") + "\" src=\"../images/closedfold.gif\">\n");
                    treeHtmlString.append("<a href=\"temi_client_node_crud.jsp?id="+resultSet.getInt("id")+"\" target=\"temiClientDispAreaFrame\">"
                            + resultSet.getString("name") + "</a>\n");
                    treeHtmlString.append("<div style=\"display:none;\" id=\"div" + resultSet.getInt("id") + "\">\n");
                    readTemiClientTree(resultSet.getInt("id"), level + 1);
                    treeHtmlString.append("</div>\n");
                } else {
                    treeHtmlString.append("<img src=\"../images/minus.gif\">\n");
                    treeHtmlString.append("<img src=\"../images/openfold.gif\">\n");
                    if ("N".equals(resultSet.getString("is_temi_client"))) {
                        treeHtmlString.append("<a href=\"temi_client_node_crud.jsp?id="+resultSet.getInt("id")+"\" target=\"temiClientDispAreaFrame\">"
                                + resultSet.getString("name") + "</a>\n");
                    }else {
                        treeHtmlString.append("<a href=\"temi_client_crud.jsp?id="+resultSet.getInt("id")+"\" target=\"temiClientDispAreaFrame\">"
                                + resultSet.getString("name") + "</a>\n");
                    }
                }
                treeHtmlString.append("</div>\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
    }
}
