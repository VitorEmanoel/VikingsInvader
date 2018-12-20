package me.vitoremanoel.vikingsinvader.entity.entitys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import me.vitoremanoel.vikingsinvader.animation.VikingAnimation;
import me.vitoremanoel.vikingsinvader.location.Direction;
import me.vitoremanoel.vikingsinvader.location.Size;
import me.vitoremanoel.vikingsinvader.world.GameWorld;


public class Viking extends Entity {

    private boolean defense;
    private Body body;
    private Fixture fixture;

    public Viking(){
        super(new Size(20, 20));
        this.getLocation().setDirection(Direction.RIGHT);
        this.playAnimation(VikingAnimation.RUN, Animation.PlayMode.LOOP);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(50, 120);

        this.body = GameWorld.getInstance().getWorld().createBody(bodyDef);
        this.body.setFixedRotation(true);
        this.body.setUserData(this);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1f;
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(this.size.getWidth(), this.size.getHeight());
        fixtureDef.shape = polygonShape;
        this.fixture = this.body.createFixture(fixtureDef);
        this.fixture.setUserData(this);
        polygonShape.dispose();
    }

    public boolean isDefense(){
        return this.defense;
    }

    @Override
    public void render(SpriteBatch batch) {
        this.getLocation().setX(this.body.getPosition().x);
        this.getLocation().setY(this.body.getPosition().y);
        this.getLocation().setRotation((float)Math.toDegrees(this.body.getAngle()));
        super.render(batch);
    }

    public void defense(){
        if(this.inAnimation(VikingAnimation.RUN) || this.inAnimation(VikingAnimation.ATTACk)) return;
        if(this.getAnimation() == VikingAnimation.DEFENSE ) {
            this.playAnimation(VikingAnimation.IDLE, Animation.PlayMode.LOOP);
            this.defense = false;
        } else {
            this.playAnimation(VikingAnimation.DEFENSE);
            this.defense = true;
        }
    }

    public Body getBody(){
        return this.body;
    }

    public void attack(){
        if(this.inAnimation(VikingAnimation.RUN) || this.inAnimation(VikingAnimation.ATTACk)) return;
        this.playAnimation(VikingAnimation.ATTACk);
    }

    public void run(Direction direction){
        if(this.inAnimation(VikingAnimation.ATTACk)) return;
        this.getLocation().setDirection(direction);
        this.playAnimation(VikingAnimation.RUN, Animation.PlayMode.LOOP);
    }


}
