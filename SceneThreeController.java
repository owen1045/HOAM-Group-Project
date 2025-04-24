import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;


public class SceneThreeController
{
    private String mode;
    
    @FXML
    private AnchorPane gamePane;

    @FXML
    private Rectangle goalie;
    private double goalieSpeed = 5;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private ArrayList<Ball> balls = new ArrayList<>();
    private Random random = new Random();
    private int score = 0;
    private int goalScored = 0;
    
    @FXML 
    private Label scoreLabel;
    
    private Image soccerBallImage;
    
    @FXML
    public void initialize()
    {
        soccerBallImage = new Image(getClass().getResourceAsStream("soccerball.png"));
        
        gamePane.setOnMouseMoved(this::handleMouseMovement);
        
        AnimationTimer timer = new AnimationTimer()
        {
            long lastSpawn = 0;

            @Override
            public void handle(long now)
            {
                moveBalls();

                if (now - lastSpawn > 1_000_000_000)
                {
                    spawnBall();
                    lastSpawn = now;
                }
            }
        };
        timer.start();
    }
    //goalie follows moving mouse cursor
    private void handleMouseMovement(MouseEvent event)
    {
        double mouseX = event.getX();
        double newGoalieX = mouseX - goalie.getWidth() / 2;

        newGoalieX = Math.max(0, Math.min(gamePane.getWidth() - goalie.getWidth(), newGoalieX));
        goalie.setLayoutX(newGoalieX);
    }


    private void spawnBall()
    {
        double startX = random.nextDouble() * (gamePane.getWidth() - 40);
        double speedX = random.nextDouble() * 4 - 2; 
        double speedY = 3 + GoalStopperApp.getBallSpeed();

        Ball ball = new Ball(startX, 0, speedX, speedY);
        balls.add(ball);
        gamePane.getChildren().add(ball.imageView);
    }

    private void moveBalls()
    {
        Iterator<Ball> iter = balls.iterator();
        while (iter.hasNext())
        {
            Ball ball = iter.next();
            ball.update();

            
            if (ball.imageView.getBoundsInParent().intersects(goalie.getBoundsInParent())) {
                gamePane.getChildren().remove(ball.imageView);
                iter.remove();
                score++;
                updateScore();
            } 
            else if (ball.imageView.getLayoutY() > gamePane.getHeight())
            {
                gamePane.getChildren().remove(ball.imageView);
                iter.remove();
                goalScored++;
                gameOver();
                
            }
        }
    }
    
    
    private void updateScore()
    {
        scoreLabel.setText("Score: " + score);
    }
    
    void setMode(String mode)
    {
        this.mode = mode;
    }
    
    private void gameOver()
    {
      if (mode == null)
      {
         return;
      }
      if (mode.equals("easy") && goalScored == 5 || mode.equals("medium") && goalScored == 3 || mode.equals("hard") && goalScored == 1)
      {
         try
         {
            switchScene();
         }
         catch (IOException e)
         {
            e.printStackTrace();
         }
      }
    }
    
    private void switchScene() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SceneFourFXML.fxml"));
        Parent parent = loader.load();
        //pass score
        SceneFourController controller = loader.getController();
        controller.setScore(score);
        Scene scene = new Scene(parent);
        Stage window = (Stage)gamePane.getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    private class Ball
    {
        ImageView imageView;
        double dx, dy;

        Ball(double x, double y, double dx, double dy)
        {
            imageView = new ImageView(soccerBallImage);
            imageView.setFitWidth(30);
            imageView.setFitHeight(30);
            imageView.setLayoutX(x);
            imageView.setLayoutY(y);
            this.dx = dx;
            this.dy = dy;
        }

        void update()
        {
            double nextX = imageView.getLayoutX() + dx;
            double nextY = imageView.getLayoutY() + dy;
            if(nextX <= 0 || nextX + imageView.getFitWidth() >= gamePane.getWidth())
            {
                dx = -dx;
            }
            imageView.setLayoutX(imageView.getLayoutX() + dx);
            imageView.setLayoutY(imageView.getLayoutY() + dy);
        }
    }
}


