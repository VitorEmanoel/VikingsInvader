package me.vitoremanoel.vikingsinvader.animation.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationCatapultIdle extends AbstractAnimation{

    private static AnimationCatapultIdle instance;

    private AnimationCatapultIdle(int length, float rate, String pathname, Texture[] textures){
        super(length, rate, pathname, new Animation<Texture>(rate, textures));
    }

    public static AnimationCatapultIdle createAnimationint (int length, float rate, String pathname){
        if (instance == null) {
            AnimationCatapultIdle.instance = new AnimationCatapultIdle(length, rate, pathname, AnimationCatapultIdle.loadTextures(length, pathname));
        }
        return instance;
    }
}
