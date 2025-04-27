import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class GoalStopperApp extends Application
{

    private static Stage Astage;
    private static final HashMap<String, Scene> scenes = new HashMap<>();
    private static int ballSpeed = 3; 
    private static GoalStopperModel model;

    @Override
    public void start(Stage stage) throws IOException
    {
        Astage = stage;
        model = new GoalStopperModel();
        
        scenes.put("scene1", new Scene(FXMLLoader.load(getClass().getResource("SceneOneController.fxml"))));
        scenes.put("scene2", new Scene(FXMLLoader.load(getClass().getResource("SceneTwoFXML.fxml"))));
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SceneFourFXML.fxml"));
        Parent root = loader.load();
        SceneFourController controller = loader.getController();
        controller.setModel(model);
        scenes.put("scene4", new Scene(root));
        
        switchScene("scene1");
        stage.setTitle("Goal Stopper");
        stage.show();
    }

    public static void switchScene(String name)
    {
        Astage.setScene(scenes.get(name));
    }

    public static void setBallSpeed(int speed)
    {
        ballSpeed = speed;
    }

    public static int getBallSpeed()
    {
        return ballSpeed;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
