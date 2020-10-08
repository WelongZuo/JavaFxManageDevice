package example.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer userId;
    private String userName;
    private String userAlias;
    private String userIps;
    private String userMacs;
    private Timestamp createTime;
    private Timestamp deleteTime;
    private Timestamp updateTime;

    public User() { }

    public User(Integer userId, String userName, String userAlias, String userIps, String userMacs) {
        this.userId = userId;
        this.userName = userName;
        this.userAlias = userAlias;
        this.userIps = userIps;
        this.userMacs = userMacs;
    }

    static public User getCurrentUser() {
        String ip = null;
        String hostName = null;

        try {
            InetAddress addr = InetAddress.getLocalHost();

            ip = addr.getHostAddress(); //获取本机ip
//            hostName = addr.getHostName(); //获取本机计算机名称
            hostName = new Date().toString();
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        User loginUser = new User(null, hostName, null, ip, null);
        System.out.println(loginUser);

        return loginUser;
    }

}
