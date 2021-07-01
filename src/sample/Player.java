package sample;

import javafx.scene.paint.Color;

import static sample.Direction.RIGHT;
import static sample.Game.PANE_WIDTH;
import static sample.Game.sprites;
import static sample.Type.*;

public class Player extends Sprite {

    Player(int x, int y, int w, int h, Color color){
        super(x, y, w, h, color, PLAYER);
        setCollisionBlackList(new Type[]{ALIEN});
        sprites.add(this);
    }

    public void update(){

    }
}
