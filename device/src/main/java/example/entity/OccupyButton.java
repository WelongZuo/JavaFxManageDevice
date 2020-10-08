package example.entity;

import example.mapper.DeviceMapper;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class OccupyButton extends Button {

    @Autowired(required = false)
    private DeviceMapper deviceMapper;

    private static OccupyButton staticOccupyButton;

    @PostConstruct
    public void init() {
        staticOccupyButton = this;
        staticOccupyButton.deviceMapper = this.deviceMapper;
    }

    public OccupyButton() { }

    public OccupyButton(Device device) {
        super("使用");
        setOnAction((event) -> {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Hey!");
//            alert.setHeaderText(null);
//            alert.setContentText("You're editing \"" + deviceId + "\"");
//            alert.showAndWait();

            User loginUser = User.getCurrentUser();

            Device update = new Device();
            update.setDeviceId(device.getDeviceId());
            update.setUserIps(loginUser.getUserIps());
            update.setUserName(loginUser.getUserName());

            System.out.println("update:" + update);
            System.out.println("device:" + device);

            staticOccupyButton.deviceMapper.updateByPrimaryKeySelective(update);
        });
    }
}
