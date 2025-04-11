import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class HOAMSoccer extends Application {
   Pane root;
   Ball ball;
   Goal goal;
   int goals = 0;
   int savedGoals = 0;
   
   public class Ball extends Circle {
      int ballSpeed = 1;
      
      Ball(int x, int y, int radius, Color color) {
         super(radius, color);
         setTranslateX(x);
         setTranslateY(y);
      }
      
      public void ballMove(double dx, double dy) {
         setTranslateX(getTranslateX() + dx * ballSpeed);
         setTranslateY(getTranslateY() + dy * ballSpeed);
      }
   }
   
   public class Goal extends Rectangle {
      Goal(int x, int y, int w, int h, Color color) {
         super(w, h, color);
         setTranslateX(x);
         setTranslateY(y);
      }
   }
   
   public class Player extends Rectangle {
      Player(int x, int y, int w, int h, Color color) {
         super(w, h, color);
         setTranslateX(x);
         setTranslateY(y);
      }
      
      void moveLeft() {
         setTranslateX(getTranslateX() - 5);
      }
      void moveRight() {
         setTranslateX(getTranslateX() + 5);
      }
      void moveUp() {
         setTranslateY(getTranslateY() - 5);
      }
      void moveDown() {
         setTranslateY(getTranslateY() + 5);
      }
   }
   //spawns ball
   public void nextBall() {
      ball = new Ball((int)(Math.random() * (600 - 150 + 1)), 250, 10, Color.RED);
      root.getChildren().add(ball);
   }
   
   public void start(Stage stage) {
      root = new Pane();
      root.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
      
      Player player = new Player(300, 500, 20, 20, Color.BLUE);
      goal = new Goal(230, 550, 175, 75, Color.BLACK);
      Label score = new Label("Score: " + savedGoals);
      score.relocate(400, 150);
      
      root.getChildren().addAll(goal, player, score);
      nextBall();
      
      Scene scene = new Scene(root, 600, 630);
      //keyboard movement for player
      scene.setOnKeyPressed(e -> {
         switch (e.getCode()) {
            case A:
               player.moveLeft();
               break;
            case D:
               player.moveRight();
               break;
            case S:
               player.moveDown();
               break;
            case W:
               player.moveUp();
               break;
         }
      });
      
      stage.setScene(scene);
      stage.setTitle("Soccer Game");
      
      PauseTransition pause = new PauseTransition(Duration.seconds(.75));
      //game loop
      AnimationTimer timer = new AnimationTimer() {
         @Override
         public void handle(long now) {
            //calculates the direction the ball should go in to end up in the goal
            double ballX = ball.getTranslateX();
            double ballY = ball.getTranslateY();
            double goalX = goal.getTranslateX() + 115;
            double goalY = goal.getTranslateY() + 75;
            
            double dx = goalX - ballX;
            double dy = goalY - ballY;
               
            double distance = Math.sqrt(dx * dx + dy * dy);
            
            dx /= distance;
            dy /= distance;
            
            ball.ballMove(dx, dy);
            
            //debug collision
            //System.out.println("Ball Y: " + ball.getTranslateY());
            //System.out.println("Goal Y: " + goal.getTranslateY());
            //System.out.println("Goal Height: " + goal.getHeight());
            
            //if player saves ball
            if (ball.getBoundsInParent().intersects(player.getBoundsInParent())) {
            
               savedGoals++;
               score.setText("Score: " + savedGoals);
               //short pause when a save or goal happens
               pause.setOnFinished(e -> {
                  root.getChildren().remove(ball); 
                  nextBall();
                  start();
               });
               pause.play();
               stop();
            }
            
            //if ball is scored
            if (
                ball.getTranslateY() >= goal.getTranslateY() + 65 &&
                ball.getTranslateY() <= goal.getTranslateY() + goal.getHeight()) {
                
                goals++;
                System.out.println("Goal Scored");
                pause.setOnFinished(e -> {
                  root.getChildren().remove(ball); 
                  nextBall();
                  start();
               });
                pause.play();
                stop();
            }
         }
      };
      timer.start();
      stage.show();
   }
}
