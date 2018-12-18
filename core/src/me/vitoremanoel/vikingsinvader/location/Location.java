package me.vitoremanoel.vikingsinvader.location;

public class Location {

    private float x;
    private float y;
    private float rotate;
    private Direction direction;


    public Location(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Location(int x, int y, Direction direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public float getRotation() {
        return rotate;
    }

    public void setRotation(float degree) {
        this.rotate = degree;
    }
}
