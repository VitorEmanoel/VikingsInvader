package me.vitoremanoel.vikingsinvader.animation.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationAttack extends AbstractAnimation {

    private static AnimationAttack instance;

    private AnimationAttack(int length, float rate, String pathname, Texture[] textures){
        super(length, rate, pathname, new Animation<Texture>(rate, textures));
    }

    public static AnimationAttack createAnimation(int length, float rate, String pathname){
        if(instance == null){
            AnimationAttack.instance = new AnimationAttack(length, rate, pathname, AnimationAttack.loadTextures(length, pathname));
        }
        return instance;
    }
}
