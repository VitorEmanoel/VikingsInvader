package me.vitoremanoel.vikingsinvader.animation;

import me.vitoremanoel.vikingsinvader.animation.animations.AnimationCatapultIdle;
import me.vitoremanoel.vikingsinvader.animation.animations.AnimationLaunch;

public class CatapultAnimation {

    public static AnimationLaunch LAUNCH = AnimationLaunch.createAnimationint(4, 0.25f, "Catapult/Catapulta");
    public static AnimationCatapultIdle IDLE = AnimationCatapultIdle.createAnimationint(1, 0.25f, "Catapult/Catapulta");

}
