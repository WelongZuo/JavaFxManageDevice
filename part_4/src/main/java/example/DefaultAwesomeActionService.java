package example;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

@Component
public class DefaultAwesomeActionService implements AwesomeActionService {

    private ResourceBundle bundle = ResourceBundle.getBundle("example.helloworld", new Locale("de"));

    @Override
    public String processName(final String name) {
        return MessageFormat.format(bundle.getString("greeting"), name);
//        return MessageFormat.format(bundle.getString("go"), name);
    }

}
