package example;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import example.view.DeviceListView;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("example.mapper")
public class Main extends AbstractJavaFxApplicationSupport {
    public static void main(String[] args) {
        launch(Main.class, DeviceListView.class, args);
    }
}
