package drp.systemmgr.manager;

import drp.systemmgr.domain.PageModel;
import drp.systemmgr.domain.User;
import drp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 采用单例管理用户
 */
public class UserManager {
    private static UserManager instance = new UserManager();
    private UserManager(){}

    public static UserManager getInstance(){
        return instance;
    }

    /**
     * 添加用户
     * @param user
     */
    public void addUser(User user){
        String sql = "insert into user_msg(id,username,password,contact_tel,email,create_date) values(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getId());
            preparedStatement.setString(2,user.getUsername());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setString(4,user.getContactTel());
            preparedStatement.setString(5,user.getEmail());
            preparedStatement.setTimestamp(6,new Timestamp(new Date().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(preparedStatement);
        }
    }

    /**
     *
     * @param userId
     * @return 如果存在返回User对象，否则返回null
     */
    public User findUserById(String userId){
        String sql = "SELECT * FROM user_msg WHERE id=?";
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userId);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getString("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setContactTel(resultSet.getString("contact_tel"));
                user.setEmail(resultSet.getString("email"));
                user.setCreateDate(resultSet.getTimestamp("create_date"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
        }
        return user;
    }

    /**
     * 分页查询
     * @param pageNo 第几页
     * @param pageSize 每页多少条数据
     * @return pageModel
     */
    public PageModel findUserList(int pageNo,int pageSize){
        String sql = "select * from user_msg where id!='root' order by id limit ?,? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PageModel pageModel = new PageModel();
        List pageUserList = new ArrayList();
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,(pageNo-1)*pageSize);
            preparedStatement.setInt(2,pageSize);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setContactTel(resultSet.getString("contact_tel"));
                user.setEmail(resultSet.getString("email"));
                user.setCreateDate(resultSet.getTimestamp("create_date"));
                pageUserList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
        }
        pageModel.setList(pageUserList);
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        pageModel.setTotalPageSize(0);
        pageModel.setTotalRecords(0);
        return pageModel;
    }
}
