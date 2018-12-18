package me.vitoremanoel.vikingsinvader.entity.entitys;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import me.vitoremanoel.vikingsinvader.location.Size;
import me.vitoremanoel.vikingsinvader.world.GameWorld;

public class Catapult extends Entity {

    private Body body;

    public Catapult(){
        super(new Size(400, 500));

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(300, 1000);
        this.body = GameWorld.getInstance().getWorld().createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.size.getWidth(), this.size.getHeight());
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        this.body.createFixture(fixtureDef);


    }

    public void launch(float force){

    }
}
