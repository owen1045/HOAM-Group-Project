import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SceneFourController {
    private int score;
    
    @FXML
    private Button playAgain;

    @FXML
    private Label scoreField;

    @FXML
    private Label scoreHistory;

    @FXML
    void playAgain(ActionEvent event) {
      GoalStopperApp.switchScene("scene2");
    }
    
    void setScore(int score) {
      this.score = score;
      scoreField.setText("" + score);
    }
}
