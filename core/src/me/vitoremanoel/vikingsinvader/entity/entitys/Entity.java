package me.vitoremanoel.vikingsinvader.entity.entitys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.vitoremanoel.vikingsinvader.animation.animations.AbstractAnimation;
import me.vitoremanoel.vikingsinvader.location.Direction;
import me.vitoremanoel.vikingsinvader.location.Location;
import me.vitoremanoel.vikingsinvader.location.Size;

public abstract class Entity {

    protected Location location;
    protected AbstractAnimation animation;
    private boolean hide;
    protected Size size;

    private boolean paused;
    protected float stateTime;

    public Entity(Size size){
        this.location = new Location(0, 0);
        this.stateTime = 0f;
        this.size = size;
    }

    public void stopAnimation(){
        this.stateTime = 0f;
        this.animation = null;
    }

    public void playAnimation(AbstractAnimation animation){
        this.stateTime = 0f;
        this.animation = animation;
    }

    public void playAnimation(AbstractAnimation animation, Animation.PlayMode playMode){
        animation.getAnimation().setPlayMode(playMode);
        this.playAnimation(animation);
    }

    public Size getSize() {
        return size;
    }

    public boolean inAnimation(){
        return (this.animation != null);
    }

    public boolean inAnimation(AbstractAnimation animation){
        return (this.animation == animation && !this.animation.getAnimation().isAnimationFinished(this.stateTime));
    }

    public void pauseAnimation(){
        this.paused = true;
    }

    public boolean isPaused(){
        return this.paused;
    }

    public void resumeAnimation(){
        this.paused = false;
    }

    public float getStateTime(){
        return this.stateTime;
    }

    public AbstractAnimation getAnimation(){
        return this.animation;
    }

    public Location getLocation(){
        return this.location;
    }

    public void hide(){
        this.hide = true;
    }

    public void show(){
        this.hide = false;
    }

    public boolean isHide(){
        return this.hide;
    }

    public void render(SpriteBatch batch){
        if(this.hide) return;
        if(!this.inAnimation()) return;
        if(!paused)
            this.stateTime += Gdx.graphics.getDeltaTime();
        Sprite spriteFrame = new Sprite(this.animation.getAnimation().getKeyFrame(this.stateTime));
        if(this.location.getDirection() == Direction.LEFT){
            spriteFrame.flip(true, false);
            this.getLocation().setX(this.getLocation().getX() - this.size.getHeight()/2);
        }
        spriteFrame.setRotation(this.getLocation().getRotation());
        spriteFrame.setPosition(this.location.getX() - (this.size.getWidth() + this.size.getWidth()/2), this.location.getY() - (this.size.getHeight() + this.size.getHeight()/2));
        spriteFrame.draw(batch);
    }
}
