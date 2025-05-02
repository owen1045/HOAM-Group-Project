import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
    
public class GoalScorerDatabase
{
   public static void insertAttempt(GoalStopperModel model) {
      try(Connection connection = DriverManager.getConnection("jdbc:sqlite:goalscorer.db"))
      {
         String updateString = "INSERT INTO attempts (attemptNumber, mode, score) VALUES (?, ?, ?)";
         
         PreparedStatement ps = connection.prepareStatement(updateString);
         
         ps.setInt(1, model.getAttemptNumber());
         ps.setString(2, model.getMode());
         ps.setInt(3, model.getScore());
         
         ps.executeUpdate();
      }
      catch(SQLException e)
      {
         System.out.printf("SQL ERROR:  %s%n", e.getMessage());
      }
   } 
}