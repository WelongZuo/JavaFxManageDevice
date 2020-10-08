package example.controller;

import de.felixroske.jfxsupport.FXMLController;
import example.entity.Device;
import example.entity.User;
import example.mapper.DeviceMapper;
import example.mapper.UserMapper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.*;

@FXMLController
public class DeviceListController implements Initializable {

    @FXML
    private TableView<Device> deviceTable;

    @FXML
    private TableColumn colDeviceId;
    @FXML
    private TableColumn colLabName;
    @FXML
    private TableColumn colDeviceIp;
    @FXML
    private TableColumn colDeviceAlias;
    @FXML
    private TableColumn colDeviceType;
    @FXML
    private TableColumn colDeviceLoginName;
    @FXML
    private TableColumn colDeviceLoginPassword;
    @FXML
    private TableColumn colDeviceHardwareInfo;
    @FXML
    private TableColumn colUserName;
    @FXML
    private TableColumn colUserIps;
    @FXML
    private TableColumn colEstimateReleaseTime;
    @FXML
    private TableColumn colCreateTime;
    @FXML
    private TableColumn colOperation;

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private DeviceMapper deviceMapper;

    private void registerUser() {
        User loginUser = User.getCurrentUser();
        if (userMapper.selectCount(loginUser) == 0) {
            userMapper.insert(loginUser);
        }
    }

    private void refreshDeviceTable() {
        colDeviceId.setCellValueFactory(new PropertyValueFactory("deviceId"));
        colLabName.setCellValueFactory(new PropertyValueFactory("labName"));
        colDeviceIp.setCellValueFactory(new PropertyValueFactory("deviceIp"));
        colDeviceAlias.setCellValueFactory(new PropertyValueFactory("deviceAlias"));
        colDeviceType.setCellValueFactory(new PropertyValueFactory("deviceType"));
        colDeviceLoginName.setCellValueFactory(new PropertyValueFactory("deviceLoginName"));
        colDeviceLoginPassword.setCellValueFactory(new PropertyValueFactory("deviceLoginPassword"));
        colDeviceHardwareInfo.setCellValueFactory(new PropertyValueFactory("deviceHardwareInfo"));
        colUserName.setCellValueFactory(new PropertyValueFactory("userName"));
        colUserIps.setCellValueFactory(new PropertyValueFactory("userIps"));
        colEstimateReleaseTime.setCellValueFactory(new PropertyValueFactory("estimateReleaseTime"));
        colCreateTime.setCellValueFactory(new PropertyValueFactory("createTime"));
        colOperation.setCellValueFactory(new PropertyValueFactory("ocpButton"));

        List<Device> deviceList = deviceMapper.selectAll();

        ObservableList<Device> obDeviceList = FXCollections.observableArrayList();
        obDeviceList.addAll(deviceList);

        deviceTable.setItems(obDeviceList);
    }

    private void startTimer2RefreshTable() {
        new Timer().schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        refreshDeviceTable();
                    }
                });
            }
        }, 0, 1000); //定时器的延迟时间及间隔时间
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registerUser();
        refreshDeviceTable();
        startTimer2RefreshTable();
    }
}
