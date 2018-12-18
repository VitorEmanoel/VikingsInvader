package me.vitoremanoel.vikingsinvader.animation.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationLaunch extends AbstractAnimation {

    private static AnimationLaunch instance;

    private AnimationLaunch(int length, float rate, String pathname, Texture[] textures){
        super(length, rate, pathname, new Animation<Texture>(rate, textures));
    }

    public static AnimationLaunch createAnimationint (int length, float rate, String pathname){
        if (instance == null) {
            AnimationLaunch.instance = new AnimationLaunch(length, rate, pathname, AnimationLaunch.loadTextures(length, pathname));
        }
        return instance;
    }

}
