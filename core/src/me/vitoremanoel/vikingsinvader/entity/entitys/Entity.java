package me.vitoremanoel.vikingsinvader.entity.entitys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.vitoremanoel.vikingsinvader.animation.animations.AbstractAnimation;
import me.vitoremanoel.vikingsinvader.location.Direction;
import me.vitoremanoel.vikingsinvader.location.Location;

public abstract class Entity {

    private Location location;
    private AbstractAnimation animation;
    private float stateTime;

    public Entity(){
        this.location = new Location(0, 0);
        this.stateTime = 0f;
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

    public boolean inAnimation(){
        return (this.animation != null);
    }

    public boolean inAnimation(AbstractAnimation animation){
        return (this.animation == animation && !this.animation.getAnimation().isAnimationFinished(this.stateTime));
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

    public void render(SpriteBatch batch){
        if(!this.inAnimation()) return;
        this.stateTime += Gdx.graphics.getDeltaTime();
        Sprite spriteFrame = new Sprite(this.animation.getAnimation().getKeyFrame(this.stateTime));
        if(this.location.getDirection() == Direction.LEFT){
            spriteFrame.flip(true, false);
        }
        spriteFrame.setPosition(this.location.getX(), this.location.getY());
        spriteFrame.draw(batch);
    }
}
