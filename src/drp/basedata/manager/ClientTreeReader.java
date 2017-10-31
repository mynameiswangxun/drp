package drp.basedata.manager;

import drp.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientTreeReader {
    /**
     * 返回HTML字符串
     * @return
     */
    private StringBuffer treeHtmlString = new StringBuffer();
    private Connection connection = DBUtil.getConnection();

    public String getClientTreeHtmlString() {
        readClientTree(0,0);
        return treeHtmlString.toString();
    }
    private void readClientTree(int pid,int level){
        String sql = "SELECT * FROM client WHERE pid=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,pid);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                treeHtmlString.append(resultSet.getString("name"));
                if("N".equals(resultSet.getString("is_leaf"))){
                    readClientTree(resultSet.getInt("id"),level+1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
