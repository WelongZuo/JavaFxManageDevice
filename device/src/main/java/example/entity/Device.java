package example.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer   deviceId;
    private String    labName;
    private String    deviceIp;
    private String    deviceAlias;
    private String    deviceType;
    private String    deviceLoginName;
    private String    deviceLoginPassword;
    private String    deviceHardwareInfo;
    private Integer   userId;
//    private String    userName;
    private StringProperty userName;
    private String    userIps;
    private Timestamp estimateReleaseTime;
    private Timestamp createTime;
    private Timestamp deleteTime;
    private Timestamp updateTime;

    @Transient
    private OccupyButton ocpButton;

    public Device() {
        ocpButton = new OccupyButton(this);
        userName = new SimpleStringProperty();
    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }
}
