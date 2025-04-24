import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SceneTwoController 
{

    @FXML
    private Button easyButton;

    @FXML
    private Button hardButton;

    @FXML
    private Button medButton;

    @FXML
    void handleButton(ActionEvent event) throws IOException
    {
      Button clicked = (Button) event.getSource();
      String mode = "";
      
      if (clicked == easyButton)
      {
         mode = "easy";
      }
      else if (clicked == medButton)
      {
         mode = "medium";
      }
      else if (clicked == hardButton)
      {
         mode = "hard";
      }
      //Loads Scene 3 and passes which mode was clicked
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("SceneThree.fxml"));
      Parent parent = loader.load();
      SceneThreeController controller = loader.getController();
      controller.setMode(mode);
      Scene scene = new Scene(parent);
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
      window.setScene(scene);
      window.show();
    }

}