package me.vitoremanoel.vikingsinvader.entity.entitys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import me.vitoremanoel.vikingsinvader.animation.RockAnimation;
import me.vitoremanoel.vikingsinvader.entity.Persons;
import me.vitoremanoel.vikingsinvader.location.Size;
import me.vitoremanoel.vikingsinvader.world.GameWorld;

public class Rock extends Entity {

    private Body body;
    private Fixture fixture;

    public Rock(){
        super(new Size(32, 29));

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.bullet = true;
        bodyDef.position.set(1260, 220);
        bodyDef.gravityScale = 2f;
        this.body = GameWorld.getInstance().getWorld().createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(this.size.getWidth()/2);
        fixtureDef.shape = shape;
        fixtureDef.restitution = 0f;
        this.fixture = this.body.createFixture(fixtureDef);
        this.fixture.setUserData(this);
        this.playAnimation(RockAnimation.IDLE, Animation.PlayMode.LOOP);
        this.show();
    }


    public Body getBody(){
        return this.body;
    }


    public void respawn(){
        this.body.setTransform(1260, 220, 0);
        this.body.setLinearVelocity(new Vector2(0,0));
    }


    @Override
    public void render(SpriteBatch batch) {
        this.getLocation().setX(this.body.getPosition().x);
        this.getLocation().setY(this.body.getPosition().y);
        this.stateTime += Gdx.graphics.getDeltaTime();
        if(this.body.getLinearVelocity().x == 0 && this.body.getLinearVelocity().y == 0){
            Persons.CATAPULT.recharger();
        }
        Sprite spriteFrame = new Sprite(this.animation.getAnimation().getKeyFrame(this.stateTime));
        spriteFrame.setPosition(this.getLocation().getX() - (this.getSize().getWidth()/2), this.getLocation().getY() - (this.getSize().getHeight()/2));
        spriteFrame.draw(batch);
    }

}
