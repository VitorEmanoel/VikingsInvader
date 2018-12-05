package me.vitoremanoel.vikingsinvader;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import me.vitoremanoel.vikingsinvader.entity.Persons;
import me.vitoremanoel.vikingsinvader.entity.entitys.Viking;
import me.vitoremanoel.vikingsinvader.input.MainInputProcess;

public class VikingsInvader extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;
	private Viking viking;

	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.camera.update();

		this.tiledMap = new TmxMapLoader().load("map/map.tmx");
		this.tiledMapRenderer = new OrthogonalTiledMapRenderer(this.tiledMap);

		Gdx.input.setInputProcessor(new MainInputProcess());

		this.viking = Persons.VIKING;
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.camera.update();
		tiledMapRenderer.setView(this.camera);
		tiledMapRenderer.render();
		this.batch.begin();
        this.viking.render(this.batch);
		this.batch.end();
	}
	
	@Override
	public void dispose () {
		this.batch.dispose();
	}
}
