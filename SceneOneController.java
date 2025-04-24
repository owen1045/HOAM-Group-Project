import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SceneOneController {

    @FXML
    private Button button;

    @FXML
    void handleButton(ActionEvent event) throws IOException {
      GoalStopperApp.switchScene("scene2");
    }

}
