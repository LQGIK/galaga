package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static javafx.application.Platform.exit;
import static sample.Game.root;
import static sample.Game.sprites;
import static sample.Type.PLAYER;

enum Type {
    PLAYER, ALIEN, BULLET
}

enum Direction {
    LEFT, RIGHT, UP, DOWN
}

public abstract class Sprite extends Rectangle {

    private Type type;
    private boolean dead = false;
    private final int speed = 5;
    Type[] collisionBlackList;

    Sprite(int x, int y, int w, int h, Color color, Type type){
        super(w, h, color);
        this.type = type;
        setTranslateX(x);
        setTranslateY(y);
        sprites.add(this);
    }
    public void moveLeft(){
        setTranslateX(getTranslateX() - speed);
    }
    public void moveRight(){
        setTranslateX(getTranslateX() + speed);
    }
    public void moveUp(){
        setTranslateY(getTranslateY() - speed);
    }
    public void moveDown(){
        setTranslateY(getTranslateY() + speed);
    }
    public boolean isDead() {
        return dead;
    }
    public void setDead(boolean dead){
        this.dead = dead;
    }
    public Type getType(){
        return type;
    }
    public double x(){
        return getTranslateX();
    }
    public double y(){
        return getTranslateY();
    }

    public boolean overlaps (Sprite r) {
        return x() < r.x() + r.getWidth() && x() + getWidth() > r.x() && y() < r.y() + r.getHeight() && y() + getHeight() > r.y();
    }

    public void setCollisionBlackList(Type[] types){
        this.collisionBlackList = types;
    }

    public boolean checkCollisions(){

        // Check collisions
        boolean collided = false;
        for (Sprite sprite : sprites){
            for (Type type : collisionBlackList){
                if (
                        overlaps(sprite) &&
                        !equals(sprite) &&
                        sprite.getType() == type
                ) collided = true;
            }
        }
        return collided;
    }

    public abstract void update();

    public static void updateAll(){
        for (int i=0; i < sprites.size(); i++){
            Sprite sprite1 = sprites.get(i);

            // Update movements
            sprite1.update();

            // Update collisions
            if (sprite1.checkCollisions()) sprite1.setDead(true);

            // Check Dead
            if (sprite1.isDead()) {

                if (sprite1.type == PLAYER){
                    exit();
                }

                sprites.remove(i);
                root.getChildren().remove(sprite1);
            }
        }
    }
}
