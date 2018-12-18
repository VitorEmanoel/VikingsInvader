package me.vitoremanoel.vikingsinvader.world;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import me.vitoremanoel.vikingsinvader.util.TiledMapUtil;
import me.vitoremanoel.vikingsinvader.world.physics.WorldContactListener;


public class GameWorld {

    private static GameWorld instance;
    private Box2DDebugRenderer debugRenderer;
    private TiledMap map;
    private TiledMapRenderer mapRenderer;
    private MapLayer ground;
    private Body groundBody;
    private World world;

    private GameWorld(){
        this.map = new TmxMapLoader().load("map/map.tmx");
        this.mapRenderer = new OrthogonalTiledMapRenderer(this.map);

        this.world = new World(new Vector2(0, -100f), true);
        this.world.setContactListener(new WorldContactListener());
        this.debugRenderer = new Box2DDebugRenderer(); // Apenas versão de teste
        this.debugRenderer.setDrawBodies(true);
        this.debugRenderer.setDrawVelocities(true);
        MapObjects ab = this.map.getLayers().get("Objetos").getObjects();
        TiledMapUtil.loadColisions(this.world, this.map.getLayers().get("Colision").getObjects(), BodyDef.BodyType.StaticBody);
        TiledMapUtil.loadColisions(this.world, this.map.getLayers().get("Objetos").getObjects(), BodyDef.BodyType.StaticBody);
    }



    public static GameWorld getInstance(){
        if(instance == null)
            instance = new GameWorld();
        return instance;
    }

    public World getWorld(){
        return this.world;
    }

    public void render(OrthographicCamera camera){
        this.mapRenderer.setView(camera);
        this.mapRenderer.render();
        this.debugRenderer.render(this.world, camera.combined);//Apenas para versão de teste
        this.world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }
}
