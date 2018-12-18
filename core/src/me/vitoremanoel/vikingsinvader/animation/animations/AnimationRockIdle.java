package me.vitoremanoel.vikingsinvader.animation.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationRockIdle extends AbstractAnimation {

    private static AnimationRockIdle instance;

    private AnimationRockIdle(int length, float rate, String pathname, Texture[] textures){
        super(length, rate, pathname, new Animation<Texture>(rate, textures));
    }

    public static AnimationRockIdle createAnimationint (int length, float rate, String pathname){
        if (instance == null) {
            AnimationRockIdle.instance = new AnimationRockIdle(length, rate, pathname, AnimationRockIdle.loadTextures(length, pathname));
        }
        return instance;
    }
}
