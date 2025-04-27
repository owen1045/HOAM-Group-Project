import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SceneFourController {
    private int score;
    private String mode;
    private int attemptNumber = 1;
    private String history;
    private boolean initialized = false;
    private static GoalStopperModel model;
    
    @FXML
    public void initialize() {
      initialized = true;
      if (model != null && mode != null) {
         history();
      }
    }
    
    @FXML
    private Button playAgain;

    @FXML
    private Label scoreField;

    @FXML
    private Label scoreHistory;

    @FXML
    void playAgain(ActionEvent event) {
      model.addAttemptNumber();
      GoalStopperApp.switchScene("scene2");
    }
    
    public void setModel(GoalStopperModel model) {
      this.model = model;
      if (initialized && mode != null) {
         history();
      }
    }
    
    public void setMode(String mode) {
      this.mode = mode;
      if (initialized && model != null) {
         history();
      }
    }
    
    public void setScore(int score) {
      this.score = score;
      scoreField.setText("" + score);
    }
    
    public void setAttemptNumber(int attemptNumber) {
      this.attemptNumber = attemptNumber;
    }
    
    public void history() {
      if (model == null || mode == null) {
         return;
      }
      model.setScore(score);
      model.setMode(mode);
      int attemptNumber = model.getAttemptNumber();
      String entry = String.format("Attempt: #%d  Mode: %s Score: %d", attemptNumber, mode, score);
      model.addHistory(entry);
      scoreHistory.setText(model.getHistory());
    }
}
