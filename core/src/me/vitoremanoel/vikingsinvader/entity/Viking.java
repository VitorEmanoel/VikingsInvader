package me.vitoremanoel.vikingsinvader.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.vitoremanoel.vikingsinvader.animation.VikingAnimation;
import me.vitoremanoel.vikingsinvader.animation.animations.AbstractAnimation;
import me.vitoremanoel.vikingsinvader.animation.animations.AnimationIdle;


public class Viking {

    private int x;
    private AbstractAnimation nowAnimation;
    private float stateTime;
    private Direction direction;

    public Viking(){
        this.x = 0;
        this.stateTime = 0f;
        AnimationIdle idle = VikingAnimation.IDLE;
        idle.getAnimation().setPlayMode(Animation.PlayMode.LOOP);
        this.nowAnimation = idle;
    }

    public void stopAnimation(){
        this.stateTime = 0f;
        this.nowAnimation = null;
    }

    public void playAnimation(AbstractAnimation animation){
        this.stateTime = 0f;
        this.nowAnimation = animation;
    }

    public void playAnimation(AbstractAnimation animation, Animation.PlayMode playMode){
        AbstractAnimation newAnimation = animation;
        newAnimation.getAnimation().setPlayMode(playMode);
        this.playAnimation(newAnimation);
    }

    public boolean inAnimation(){
        return (this.nowAnimation != null);
    }

    public boolean inAnimation(AbstractAnimation animation){
        return (this.nowAnimation == animation && !this.nowAnimation.getAnimation().isAnimationFinished(this.stateTime));
    }

    public int getX() {
        return x;
    }

    public float getStateTime(){
        return this.stateTime;
    }

    public void setX(int x) {
        this.x = x;
    }

    public AbstractAnimation getNowAnimation(){
        return this.nowAnimation;
    }


    public void render(SpriteBatch batch){
        if(!this.inAnimation()) return;
        this.stateTime += Gdx.graphics.getDeltaTime();
        Sprite spriteFrame = new Sprite(this.nowAnimation.getAnimation().getKeyFrame(this.stateTime));
        if(this.direction == Direction.LEFT){
            spriteFrame.flip(true, false);
        }
        spriteFrame.setPosition(this.x, 0);
        spriteFrame.draw(batch);
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }

    public Direction getDirection(){
        return this.direction;
    }




}
