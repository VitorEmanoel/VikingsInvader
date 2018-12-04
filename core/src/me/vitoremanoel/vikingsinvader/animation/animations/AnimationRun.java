package me.vitoremanoel.vikingsinvader.animation.animations;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationRun extends AbstractAnimation {

    private static AnimationRun instance;

    private AnimationRun(int length, float rate, String pathname, Texture[] textures){
        super(length, rate, pathname, new Animation<Texture>(rate, textures));
    }

    public static AnimationRun createAnimation(int length, float rate, String pathname){
        if(instance == null){
            instance = new AnimationRun(length, rate, pathname, AnimationRun.loadTextures(length, pathname));
        }
        return instance;
    }

}
