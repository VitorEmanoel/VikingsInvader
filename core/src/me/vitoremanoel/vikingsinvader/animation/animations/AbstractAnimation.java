package me.vitoremanoel.vikingsinvader.animation.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public abstract class AbstractAnimation {

    protected final int length;
    protected final float rate;
    protected final String pathname;
    protected final Animation<Texture> animation;

    AbstractAnimation(int length, float rate, String pathname, Animation<Texture> animation){
        this.rate = rate;
        this.length = length;
        this.pathname = pathname;
        this.animation = animation;
    }

    protected static Texture[] loadTextures(int length, String pathname){
        Texture[] textures = new Texture[length];
        for(int i = 0; i < length; i++){
            String texturePath = "Viking/" + pathname + "_" + i + ".png";
            textures[i] = new Texture(texturePath);
        }
        return textures;
    }

    public Animation<Texture> getAnimation(){
        return this.animation;
    }

    public int getLength(){
        return this.length;
    }

    public float getRate(){
        return this.rate;
    }

}
