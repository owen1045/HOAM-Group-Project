import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
    
public class GoalScorerDatabase
{
   public static void main(String[] args)
   {
      
      try(Connection connection = DriverManager.getConnection("jdbc:sqlite:goalscorer.db"))
      {
         
         Statement statement = connection.createStatement();
         
         statement.setQueryTimeout(30);
         statement.executeUpdate("drop table if exists attempt");
         statement.executeUpdate("create table attempt (number integer, score integer)");
         statement.executeUpdate("insert into attempt values(1, 0)");
         statement.executeUpdate("insert into attempt values(2, 0)");
      }
      catch(SQLException e)
      {
         System.out.printf("SQL ERROR:  %s%n", e.getMessage());
      }                   
   }
}