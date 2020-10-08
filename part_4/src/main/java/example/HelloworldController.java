package example;

import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

@FXMLController
public class HelloworldController {

    @FXML
    private Label helloLabel;
  
    @FXML
    private TextField nameField;

    @FXML
    private Button helloButton;
    
    // Be aware: This is a Spring bean. So we can do the following:
    @Autowired
    private AwesomeActionService actionService;

    HelloworldController() {
        ResourceBundle bundle = ResourceBundle.getBundle("example.helloworld", new Locale("de"));

//        helloLabel.setText(bundle.getString("hello"));
//        helloButton.setText(bundle.getString("go"));
    }

    @FXML
    private void setHelloText(final Event event) {
       final String textToBeShown = actionService.processName(nameField.getText());
       helloLabel.setText(textToBeShown); 
    }
}
