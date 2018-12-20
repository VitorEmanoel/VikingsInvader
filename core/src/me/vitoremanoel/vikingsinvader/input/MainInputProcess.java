package me.vitoremanoel.vikingsinvader.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import me.vitoremanoel.vikingsinvader.entity.entitys.Catapult;
import me.vitoremanoel.vikingsinvader.entity.entitys.Rock;
import me.vitoremanoel.vikingsinvader.entity.Persons;

public class MainInputProcess implements InputProcessor {

    private Catapult catapult = Persons.CATAPULT;
    private Rock rock = Persons.ROCK;
    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.SPACE){
            this.catapult.startLaunch();
        } else if(keycode == Input.Keys.R){
            this.catapult.recharger();
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
         if(keycode == Input.Keys.R){
            if(this.catapult.isLaunched()){
                this.catapult.stopRecharger();
            }
        } else if(keycode == Input.Keys.SPACE){
            this.catapult.finishLaunch();
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
        return false;

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
}
