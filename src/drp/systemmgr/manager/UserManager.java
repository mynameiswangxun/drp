package drp.systemmgr.manager;

import drp.util.pagemodel.PageModel;
import drp.systemmgr.domain.User;
import drp.util.database.DBUtil;

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
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    /**
     * 查找用户
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
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
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
        PageModel<User> pageModel = new PageModel();
        List<User> pageUserList = new ArrayList();
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
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        pageModel.setList(pageUserList);
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        pageModel.setTotalRecords(getTotalRecords(connection));
        return pageModel;
    }

    /**
     * 获得数据库用户总记录数
     * @param connection
     * @return
     */
    private int getTotalRecords(Connection connection){
        String sql = "SELECT COUNT(*) FROM user_msg WHERE id!='root'";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int totalRecords = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            totalRecords = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return totalRecords;
    }

    /**
     * 修改用户
     * @param user
     */
    public void modifyUser(User user){
        String sql="UPDATE user_msg SET username=?,password=?,contact_tel=?,email=? WHERE id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getContactTel());
            preparedStatement.setString(4,user.getEmail());
            preparedStatement.setString(5,user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    /**
     * 根据用户ID删除用户
     * @param userId
     */
    public void deleteUser(String userId){
        String sql = "DELETE FROM user_msg WHERE id=?";
        PreparedStatement preparedStatement= null;
        Connection connection = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    /**
     * 用户登录
     * @param userId
     * @param password
     * @return
     */
    public User login(String userId,String password) throws UserIdNotExistException,PasswordErrorException{
        User user = findUserById(userId);
        if (user==null){
            throw new UserIdNotExistException("用户ID不存在！");
        }
        if(!password.equals(user.getPassword())){
            throw new PasswordErrorException("用户密码错误");
        }
        return user;
    }

    /**
     * 批量删除用户
     * @param userIds
     */
    public void deleteUser(String[] userIds){
        StringBuilder sql = new StringBuilder("DELETE FROM user_msg WHERE id in(");
        for (String userId:
             userIds) {
            sql.append("'");
            sql.append(userId);
            sql.append("',");
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(")");
        Statement statement = null;
        Connection connection = null;

        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }
    }

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void modifyPassword(String userId,String newPassword){
        String sql="UPDATE user_msg SET password=? WHERE id=?";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,newPassword);
            preparedStatement.setString(2,userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    /**
     * 根据ID得到密码
     * @param userId
     * @return
     */
    public String getPasswordById(String userId){
        String password = findUserById(userId).getPassword();
        return password;
    }
}
