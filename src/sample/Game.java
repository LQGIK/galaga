package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static javafx.application.Platform.exit;

public class Game extends Application {

    public static final int PANE_WIDTH = 600;
    public static final int PANE_HEIGHT = 600;
    public static Random rand = new Random();
    public static Pane root = new Pane();
    private Sprite player = new Player(PANE_WIDTH/2, PANE_HEIGHT-100, 40, 40, Color.BLUE);

    static List<Sprite> sprites = new ArrayList<Sprite>();

    public void add(Node s){
        root.getChildren().add(s);
    }

    private Parent createContext(){
        root.setPrefSize(PANE_WIDTH, PANE_HEIGHT);
        add(player);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Sprite.updateAll();
            }
        };
        timer.start();
        nextLevel();

        return root;
    }

    private void nextLevel(){
        for (int i=0; i < 5; i++){
            Sprite s = new Alien(90 + i*100, 150, 30, 30, Color.RED);
            add(s);
        }
    }

    private void shoot(Sprite who){
        Sprite s = new Bullet(player);
        add(s);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContext());

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()){
                case A:
                    player.moveLeft();
                    break;
                case D:
                    player.moveRight();
                    break;
                case SPACE:
                    shoot(player);
                    break;
                case Q:
                    exit();
                    break;
            }
        });



        stage.setScene(scene);
        stage.setTitle("poop");
        stage.show();
    }


    public static void main(String[] args){
        launch(args);
    }
}
