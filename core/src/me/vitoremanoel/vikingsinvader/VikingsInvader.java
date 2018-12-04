package me.vitoremanoel.vikingsinvader;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.vitoremanoel.vikingsinvader.animation.VikingAnimation;
import me.vitoremanoel.vikingsinvader.location.Direction;
import me.vitoremanoel.vikingsinvader.entity.Persons;
import me.vitoremanoel.vikingsinvader.entity.entitys.Viking;
import me.vitoremanoel.vikingsinvader.input.MainInputProcess;

public class VikingsInvader extends ApplicationAdapter {
	private SpriteBatch batch;

	private Viking viking;

	@Override
	public void create () {
		this.batch = new SpriteBatch();
		Gdx.input.setInputProcessor(new MainInputProcess());

		this.viking = Persons.VIKING;
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.batch.begin();
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && !this.viking.inAnimation(VikingAnimation.ATTACk)) {
			if(this.viking.getLocation().getDirection() != Direction.LEFT)
				this.viking.getLocation().setDirection(Direction.LEFT);
			this.viking.getLocation().setX(this.viking.getLocation().getX() - 1);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !this.viking.inAnimation(VikingAnimation.ATTACk)) {
			if(this.viking.getLocation().getDirection() != Direction.RIGHT)
				this.viking.getLocation().setDirection(Direction.RIGHT);
			this.viking.getLocation().setX(this.viking.getLocation().getX() + 1);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&  !this.viking.inAnimation(VikingAnimation.ATTACk)) {
			if(this.viking.getAnimation() != VikingAnimation.RUN)
				this.viking.playAnimation(VikingAnimation.RUN, Animation.PlayMode.LOOP);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&  !this.viking.inAnimation(VikingAnimation.ATTACk)) {
			if(this.viking.getAnimation() == VikingAnimation.RUN){
				this.viking.playAnimation(VikingAnimation.IDLE, Animation.PlayMode.LOOP);
			}
		}
        this.viking.render(this.batch);
		this.batch.end();
	}
	
	@Override
	public void dispose () {
		this.batch.dispose();
	}
}
