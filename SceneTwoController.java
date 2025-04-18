import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SceneTwoController 
{

    @FXML
    private Button easyButton;

    @FXML
    private Button hardButton;

    @FXML
    private Button medButton;

    @FXML
    void handleButton(ActionEvent event)
    {
      Button clicked = (Button) event.getSource();
      if (clicked == easyButton)
      {
         System.out.println("easybutton");
      }
      else if (clicked == medButton)
      {
         System.out.println("medbutton");
      }
      else if (clicked == hardButton)
      {
         System.out.println("hardbutton");
      }
    }

}