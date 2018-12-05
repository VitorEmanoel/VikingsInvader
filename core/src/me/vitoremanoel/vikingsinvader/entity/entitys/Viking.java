package me.vitoremanoel.vikingsinvader.entity.entitys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.vitoremanoel.vikingsinvader.animation.VikingAnimation;
import me.vitoremanoel.vikingsinvader.location.Direction;

public class Viking extends Entity {

    private boolean defense;

    public Viking(){
        this.playAnimation(VikingAnimation.IDLE, Animation.PlayMode.LOOP);
    }

    public boolean isDefense(){
        return this.defense;
    }

    @Override
    public void render(SpriteBatch batch) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && !this.inAnimation(VikingAnimation.ATTACk)) {
            if(this.getLocation().getDirection() != Direction.LEFT)
                this.getLocation().setDirection(Direction.LEFT);
            this.getLocation().setX(this.getLocation().getX() - 1);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !this.inAnimation(VikingAnimation.ATTACk)) {
            if(this.getLocation().getDirection() != Direction.RIGHT)
                this.getLocation().setDirection(Direction.RIGHT);
            this.getLocation().setX(this.getLocation().getX() + 1);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&  !this.inAnimation(VikingAnimation.ATTACk)) {
            if(this.getAnimation() != VikingAnimation.RUN)
                this.playAnimation(VikingAnimation.RUN, Animation.PlayMode.LOOP);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&  !this.inAnimation(VikingAnimation.ATTACk)) {
            if(this.getAnimation() == VikingAnimation.RUN){
                this.playAnimation(VikingAnimation.IDLE, Animation.PlayMode.LOOP);
            }
        }
        super.render(batch);
    }

    public void defense(){
        if(this.inAnimation(VikingAnimation.RUN) || this.inAnimation(VikingAnimation.ATTACk)) return;
        if(this.getAnimation() == VikingAnimation.DEFENSE ) {
            this.playAnimation(VikingAnimation.IDLE, Animation.PlayMode.LOOP);
            this.defense = false;
        } else {
            this.playAnimation(VikingAnimation.DEFENSE);
            this.defense = true;
        }
    }

    public void attack(){
        if(this.inAnimation(VikingAnimation.RUN) || this.inAnimation(VikingAnimation.ATTACk)) return;
        this.playAnimation(VikingAnimation.ATTACk);
    }

    public void run(Direction direction){
        if(this.inAnimation(VikingAnimation.ATTACk)) return;
        this.getLocation().setDirection(direction);
        this.playAnimation(VikingAnimation.RUN, Animation.PlayMode.LOOP);
    }

}
