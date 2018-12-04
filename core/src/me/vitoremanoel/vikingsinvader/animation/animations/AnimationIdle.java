package me.vitoremanoel.vikingsinvader.animation.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationIdle extends AbstractAnimation {

    private static AnimationIdle instance;

    private AnimationIdle(int length, float rate, String pathname, Texture[] textures){
        super(length, rate, pathname, new Animation<Texture>(rate, textures));
    }

    public static AnimationIdle createAnimation(int length, float rate, String pathname){
        if(instance == null){
            AnimationIdle.instance = new AnimationIdle(length, rate, pathname, AnimationAttack.loadTextures(length, pathname));
        }
        return instance;
    }
}
