package me.vitoremanoel.vikingsinvader.animation.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationDefense extends AbstractAnimation {

    private static AnimationDefense instance;

    private AnimationDefense(int length, float rate, String pathname, Texture texture){
        super(length, rate, pathname, new Animation<Texture>(rate, texture));
    }

    public static AnimationDefense createAnimation(int length, float rate, String pathname){
        if(AnimationDefense.instance == null){
            AnimationDefense.instance = new AnimationDefense(length, rate, pathname, new Texture("Viking/" + pathname + "_2" + ".png"));
        }
        return AnimationDefense.instance;
    }
}
