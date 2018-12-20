package me.vitoremanoel.vikingsinvader;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2D;
import me.vitoremanoel.vikingsinvader.entity.Persons;
import me.vitoremanoel.vikingsinvader.entity.entitys.Catapult;
import me.vitoremanoel.vikingsinvader.entity.entitys.Rock;
import me.vitoremanoel.vikingsinvader.entity.entitys.Viking;
import me.vitoremanoel.vikingsinvader.input.MainInputProcess;
import me.vitoremanoel.vikingsinvader.location.Direction;
import me.vitoremanoel.vikingsinvader.world.GameWorld;

public class VikingsInvader extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private GameWorld gameWorld;

	private Catapult catapult;
	private Rock rock;

	@Override
	public void create () {
		Box2D.init();
		this.batch = new SpriteBatch();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.camera.update();

		Gdx.input.setInputProcessor(new MainInputProcess());

		this.rock = Persons.ROCK;
		this.catapult = Persons.CATAPULT;
		this.gameWorld = GameWorld.getInstance();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.camera.update();
		this.gameWorld.render(this.camera);
		this.batch.begin();
		for(Viking v : GameWorld.getInstance().getVikings()){
			v.render(this.batch);
		}
		this.catapult.render(this.batch);
		this.rock.render(this.batch);
		this.batch.end();
	}
	
	@Override
	public void dispose () {
		this.batch.dispose();
	}
}
