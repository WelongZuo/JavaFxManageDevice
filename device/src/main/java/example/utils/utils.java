package example.utils;

import example.entity.User;
import example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class utils {

    @Autowired
    private UserMapper userMapper;

    //得到计算机的ip,名称,操作系统名称,操作系统版本
    public void Config(String ip, String hostName) {
        try {
            InetAddress addr = InetAddress.getLocalHost();

            ip = addr.getHostAddress(); //获取本机ip
            hostName = addr.getHostName(); //获取本机计算机名称
            System.out.println("本机IP：" + ip + "\n本机名称:" + hostName);

            Properties props = System.getProperties();
            System.out.println("操作系统的名称：" + props.getProperty("os.name"));
            System.out.println("操作系统的版本：" + props.getProperty("os.version"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getIpconfig(){
        Map<String,String> map = System.getenv();
        System.out.println(map.get("USERNAME"));//获取用户名
        System.out.println(map.get("COMPUTERNAME"));//获取计算机名
        System.out.println(map.get("USERDOMAIN"));//获取计算机域名

        System.out.println(map.toString());
    }

    public List<User> selectByEntity(User user) {
        if(Objects.isNull(user)) {
            user = new User();
        }
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(user);
        return userMapper.selectByExample(example);
    }
}

