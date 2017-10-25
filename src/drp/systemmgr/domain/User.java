package drp.systemmgr.domain;

import java.util.Date;

/**
 * 用户实体类
 */
public class User {
    //用户id
    private String id;
    //用户姓名
    private String username;
    //用户密码
    private String password;
    //联系电话
    private String contactTel;
    //联系邮箱
    private String email;
    //创建日期
    private Date createDate;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getContactTel() {
        return contactTel;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", contactTel='" + contactTel + '\'' +
                ", email='" + email + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
