package drp.basedata.manager;

import drp.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientTreeReader {
    private StringBuffer treeHtmlString = new StringBuffer();
    private Connection connection = DBUtil.getConnection();

    /**
     * 返回HTML字符串
     *
     * @return
     */
    public String getClientTreeHtmlString() {

        readClientTree(0, 0);
        return treeHtmlString.toString();
    }

    private void readClientTree(int pid, int level) {
        String sql = "SELECT * FROM client WHERE pid=?";
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
                    treeHtmlString.append("<img alt=\"展开\" style=\"cursor:hand;\" onClick=\"display('"+
                            resultSet.getInt("id")+"');\"" +
                            "id=\"img"+resultSet.getInt("id")+"\" src=\"../images/plus.gif\">\n");
                    treeHtmlString.append("<img id=\"im"+resultSet.getInt("id")+"\" src=\"../images/closedfold.gif\">\n");
                    treeHtmlString.append("<a href=\"client_node_crud.html\" target=\"clientDispAreaFrame\">"
                            + resultSet.getString("name") + "</a>\n");
                    treeHtmlString.append("<div style=\"display:none;\" id=\"div"+resultSet.getInt("id")+"\">\n");
                    readClientTree(resultSet.getInt("id"), level + 1);
                    treeHtmlString.append("</div>\n");
                }else{
                    treeHtmlString.append("<img src=\"../images/minus.gif\">\n");
                    treeHtmlString.append("<img src=\"../images/openfold.gif\">\n");
                    treeHtmlString.append("<a href=\"client_node_crud.html\" target=\"clientDispAreaFrame\">"
                            +resultSet.getString("name")+"</a>\n");
                }
                treeHtmlString.append("</div>\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
