import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class SceneOneApp extends Application
{
   public void start(Stage stage) throws IOException
   {
      AnchorPane root = FXMLLoader.load(getClass().getResource("SceneOneController.fxml"));
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("Scene One");
      stage.show();
   }
   public static void main(String[] args)
   {
      launch(args);
   }
}