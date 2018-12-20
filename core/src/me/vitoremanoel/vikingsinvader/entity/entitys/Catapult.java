package me.vitoremanoel.vikingsinvader.entity.entitys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import me.vitoremanoel.vikingsinvader.animation.CatapultAnimation;
import me.vitoremanoel.vikingsinvader.entity.Persons;
import me.vitoremanoel.vikingsinvader.location.Direction;
import me.vitoremanoel.vikingsinvader.location.Size;
import me.vitoremanoel.vikingsinvader.world.GameWorld;



public class Catapult extends Entity {

    private Body body;
    private Fixture fixture;
    private Rock rock = Persons.ROCK;
    private boolean launched;
    private boolean inRecharger;
    private float launchTime;

    public Catapult(){
        super(new Size(64, 32));
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(1190, 180);
        this.body = GameWorld.getInstance().getWorld().createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.size.getWidth(), this.size.getHeight());
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        this.fixture = this.body.createFixture(fixtureDef);
        this.fixture.setUserData(this);
        this.getLocation().setDirection(Direction.LEFT);
        this.playAnimation(CatapultAnimation.IDLE);
    }

    @Override
    public void render(SpriteBatch batch) {
        this.getLocation().setX(this.body.getPosition().x);
        this.getLocation().setY(this.body.getPosition().y);
        if(this.inRecharger && this.animation == CatapultAnimation.LAUNCH && this.animation.getAnimation().isAnimationFinished(this.stateTime) && this.animation.getAnimation().getPlayMode() == Animation.PlayMode.REVERSED) {
            this.launched = false;
            this.inRecharger = false;
            this.rock.respawn();
        }
        super.render(batch);
    }

    public void recharger(){
        if(this.isPaused() && this.inAnimation(CatapultAnimation.LAUNCH) && this.animation.getAnimation().getPlayMode() == Animation.PlayMode.REVERSED){
            this.resumeAnimation();
            return;
        }
        if(launched) {
            this.playAnimation(CatapultAnimation.LAUNCH, Animation.PlayMode.REVERSED);
            this.inRecharger = true;
        }
    }

    public void stopRecharger(){
        if(launched && this.inAnimation(CatapultAnimation.LAUNCH) && this.animation.getAnimation().getPlayMode() == Animation.PlayMode.REVERSED)
            this.pauseAnimation();
    }

    public void startLaunch(){
        if(launched) return;
        this.launchTime = System.currentTimeMillis();
    }

    public void finishLaunch(){
        if(launched) return;
        this.launchTime = 0;
        float finalTime = System.currentTimeMillis() - this.launchTime;
        this.launch(finalTime);
        this.launched = true;
    }

    public void launch(float force){
        this.rock.getBody().applyForce(new Vector2(force * -1, force), this.rock.getBody().getLocalCenter(), true);
        this.launched = true;
        this.playAnimation(CatapultAnimation.LAUNCH, Animation.PlayMode.NORMAL);
    }

    public void setLaunched(boolean launched){
        this.launched = launched;
    }

    public boolean isLaunched(){
        return this.launched;
    }
}
