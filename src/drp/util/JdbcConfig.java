package drp.util;

/**
 * 封装数据库连接所用到的信息
 */
public class JdbcConfig {
    private String url;
    private String username;
    private String password;
    private String driverName;

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDrivername(String drivername) {
        this.driverName = drivername;
    }

    @Override
    public String toString() {
        return "JdbcConfig{" +
                "url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", driverName='" + driverName + '\'' +
                '}';
    }
}
