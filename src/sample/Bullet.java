package sample;

import javafx.scene.paint.Color;

import static sample.Type.ALIEN;
import static sample.Type.BULLET;

public class Bullet extends Sprite {

    Bullet(Sprite player){
        super(
                (int) player.getTranslateX() + (int) player.getWidth()/2,
                (int) player.getTranslateY(),
                10,
                10,
                Color.GREEN,
                BULLET
        );
        setCollisionBlackList(new Type[] {ALIEN});
    }
    public void update(){
        // Kill if we've reached bounds
        if (getTranslateY() < 0) setDead(true);

        // Move up
        moveUp();
    }

    /*
    @Override
    public boolean equals(Object obj) {
        Bullet bullet = (Bullet) obj;
        return (this.id == bullet.id);
    }
     */
}
