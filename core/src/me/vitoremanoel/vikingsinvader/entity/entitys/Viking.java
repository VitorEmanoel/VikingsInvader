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
        this.playAnimation(VikingAnimation.IDLE, Animation.PlayMode.LOOP);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(50, 400);

        this.body = GameWorld.getInstance().getWorld().createBody(bodyDef);
        this.body.setFixedRotation(true);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1f;
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(this.size.getWidth(), this.size.getHeight());
        fixtureDef.shape = polygonShape;
        this.fixture = this.body.createFixture(fixtureDef);
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
        moving();
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

    private void moving(){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&  !this.inAnimation(VikingAnimation.ATTACk)) {
            if (this.getAnimation() != VikingAnimation.RUN){
                this.playAnimation(VikingAnimation.RUN, Animation.PlayMode.LOOP);
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&  !this.inAnimation(VikingAnimation.ATTACk)) {
            if(this.getAnimation() == VikingAnimation.RUN){
                this.playAnimation(VikingAnimation.IDLE, Animation.PlayMode.LOOP);
            }

        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && !this.inAnimation(VikingAnimation.ATTACk)) {
            if (this.getLocation().getDirection() != Direction.LEFT)
                this.getLocation().setDirection(Direction.LEFT);
            if(this.body.getLinearVelocity().y >= 0)
                this.body.setLinearVelocity(new Vector2(-50, 0));

        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !this.inAnimation(VikingAnimation.ATTACk)) {
            if(this.getLocation().getDirection() != Direction.RIGHT)
                this.getLocation().setDirection(Direction.RIGHT);
            if(this.body.getLinearVelocity().y >= 0)
                this.body.setLinearVelocity(new Vector2(50, 0));
        }
    }

}
