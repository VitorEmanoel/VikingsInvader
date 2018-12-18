package me.vitoremanoel.vikingsinvader.entity.entitys;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import me.vitoremanoel.vikingsinvader.location.Size;
import me.vitoremanoel.vikingsinvader.world.GameWorld;

public class Rock extends Entity {

    private Body body;

    public Rock(Size size){
        super(new Size(116, 116));
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.bullet = true;

        this.body = GameWorld.getInstance().getWorld().createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(this.size.getWidth()/2);
        fixtureDef.shape = shape;
        fixtureDef.restitution = 0f;
        this.body.createFixture(fixtureDef);
    }

}
