import java.util.ArrayList;
import java.util.List;

public class GoalStopperModel {
   private int score;
   private int attemptNumber = 1;
   private String mode;
   private List<String> history;
   
   public GoalStopperModel() {
      history = new ArrayList<>();
   }
   
   public void setScore(int score) {
      this.score = score;
   }
   
   public void addAttemptNumber() {
      this.attemptNumber++;
   }
   
   public int getAttemptNumber() {
      return attemptNumber;
   }
   
   public void setMode(String mode) {
      this.mode = mode;
   }
   
   public int getScore() {
      return score;
   }
   
   public String getMode() {
      return mode;
   }
   
   public void addHistory(String entry) {
      history.add(entry);
   }
   
   public String getHistory() {
      return String.join("\n", history);
   }
}