package sample;

import javafx.scene.paint.Color;

import static java.lang.Math.round;
import static sample.Direction.LEFT;
import static sample.Direction.RIGHT;
import static sample.Game.PANE_WIDTH;
import static sample.Game.sprites;
import static sample.Type.*;

public class Alien extends Sprite {

    public int margin = 10;
    public int column_size = (int) round(PANE_WIDTH - (2 * margin) / 3.0);
    Direction direction = RIGHT;
    Alien(int x, int y, int w, int h, Color color){
        super(x, y, w, h, color, ALIEN);
        setCollisionBlackList(new Type[]{BULLET, PLAYER});
        sprites.add(this);
    }

    public void update(){
        // Bounce
        if (getTranslateX() > PANE_WIDTH - getWidth()) direction = LEFT;
        else if (getTranslateX() < getWidth()) direction = RIGHT;

        // Basic Directions
        if (direction == LEFT) moveLeft();
        else moveRight();
    }
}
