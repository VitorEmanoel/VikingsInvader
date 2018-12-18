package me.vitoremanoel.vikingsinvader.animation;


import me.vitoremanoel.vikingsinvader.animation.animations.AnimationAttack;
import me.vitoremanoel.vikingsinvader.animation.animations.AnimationDefense;
import me.vitoremanoel.vikingsinvader.animation.animations.AnimationIdle;
import me.vitoremanoel.vikingsinvader.animation.animations.AnimationRun;

public class VikingAnimation {

    public static AnimationRun RUN = AnimationRun.createAnimation(6, 0.20f, "Viking/run");
    public static AnimationAttack ATTACk = AnimationAttack.createAnimation(2, 0.25f, "Viking/attack");
    public static AnimationIdle IDLE = AnimationIdle.createAnimation(4, 0.25f, "Viking/idle");
    public static AnimationDefense DEFENSE = AnimationDefense.createAnimation(1, 0.25f, "Viking/defense");

}
