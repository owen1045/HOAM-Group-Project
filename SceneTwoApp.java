import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class SceneTwoApp extends Application
{
   public void start(Stage stage) throws IOException
   {
      Parent root = FXMLLoader.load(getClass().getResource("SceneTwoFXML.fxml"));
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("Goal Stopper Game");
      stage.show();
   }
}