package me.vitoremanoel.vikingsinvader.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import me.vitoremanoel.vikingsinvader.animation.VikingAnimation;
import me.vitoremanoel.vikingsinvader.entity.Direction;
import me.vitoremanoel.vikingsinvader.entity.Persons;
import me.vitoremanoel.vikingsinvader.entity.Viking;

public class MainInputProcess implements InputProcessor {

    private Viking viking = Persons.VIKING;

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.RIGHT) {
            if(this.viking.inAnimation(VikingAnimation.ATTACk)) return false;
            this.viking.setDirection(Direction.RIGHT);
            this.viking.playAnimation(VikingAnimation.RUN, Animation.PlayMode.LOOP);
        } else if(keycode == Input.Keys.LEFT) {
            if(this.viking.inAnimation(VikingAnimation.ATTACk)) return false;
            this.viking.setDirection(Direction.LEFT);
            this.viking.playAnimation(VikingAnimation.RUN, Animation.PlayMode.LOOP);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.RIGHT || keycode == Input.Keys.LEFT){
            if(this.viking.inAnimation()) this.viking.stopAnimation();
            this.viking.playAnimation(VikingAnimation.IDLE, Animation.PlayMode.LOOP);
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == Input.Buttons.LEFT){
            if(this.viking.inAnimation(VikingAnimation.RUN) || this.viking.inAnimation(VikingAnimation.ATTACk)) return false;
            this.viking.playAnimation(VikingAnimation.ATTACk);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(button == Input.Buttons.RIGHT){
            if(this.viking.inAnimation(VikingAnimation.RUN) || this.viking.inAnimation(VikingAnimation.ATTACk)) return false;
            if(this.viking.getNowAnimation() == VikingAnimation.DEFENSE || this.viking.getNowAnimation() == VikingAnimation.ATTACk && this.viking.getNowAnimation().getAnimation().isAnimationFinished(this.viking.getStateTime()))
                this.viking.playAnimation(VikingAnimation.IDLE);
            else
                this.viking.playAnimation(VikingAnimation.DEFENSE);

        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
}
