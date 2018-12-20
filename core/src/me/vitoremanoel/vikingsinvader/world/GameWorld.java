package me.vitoremanoel.vikingsinvader.world;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import me.vitoremanoel.vikingsinvader.entity.Persons;
import me.vitoremanoel.vikingsinvader.entity.entitys.Viking;
import me.vitoremanoel.vikingsinvader.location.Direction;
import me.vitoremanoel.vikingsinvader.util.TiledMapUtil;
import me.vitoremanoel.vikingsinvader.world.physics.WorldContactListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class GameWorld {

    private static GameWorld instance;
    private Box2DDebugRenderer debugRenderer;
    private TiledMap map;
    private TiledMapRenderer mapRenderer;
    private MapLayer ground;
    private Body groundBody;
    private World world;
    private long time = 0;
    private final int MAX_VIKING = 10;
    private int vikingsCont = 0;

    private ArrayList<Viking> vikings = new ArrayList<Viking>();

    private GameWorld(){
        this.map = new TmxMapLoader().load("map/map.tmx");
        this.mapRenderer = new OrthogonalTiledMapRenderer(this.map);

        this.world = new World(new Vector2(0, -10f), false);
        this.world.setContactListener(new WorldContactListener());
        this.debugRenderer = new Box2DDebugRenderer(); // Apenas versão de teste
        this.debugRenderer.setDrawBodies(true);
        this.debugRenderer.setDrawVelocities(true);
        TiledMapUtil.loadColisions(this.world, this.map.getLayers().get("Colision").getObjects(), BodyDef.BodyType.StaticBody);
        TiledMapUtil.loadColisions(this.world, this.map.getLayers().get("Objetos").getObjects(), BodyDef.BodyType.StaticBody, "Chest");
    }

    public void kill(final Viking viking){
        this.vikings.remove(viking);
        Persons.ROCK.hide();

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                world.destroyBody(viking.getBody());
                Persons.CATAPULT.recharger();
            }
        });

        viking.hide();
    }

    public static GameWorld getInstance(){
        if(instance == null)
            instance = new GameWorld();
        return instance;
    }

    public World getWorld(){
        return this.world;
    }

    public ArrayList<Viking> getVikings(){ return this.vikings; }

    public void render(OrthographicCamera camera){
        this.world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        if(time == 0) time = System.currentTimeMillis();
        if(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - time ) >= 1){
            time = System.currentTimeMillis();
            if((Math.random() * 100) <= 10 && vikingsCont <= MAX_VIKING){
                this.vikings.add(new Viking());
                vikingsCont++;
            }
            for(Viking v : vikings){
                v.run(Direction.RIGHT);
                v.getBody().setLinearVelocity(new Vector2(10, 0));
            }
        }
        this.mapRenderer.setView(camera);
        this.mapRenderer.render();
        this.debugRenderer.render(this.world, camera.combined);//Apenas para versão de teste
    }

    private void removeBodySafely(Body body) {
        //to prevent some obscure c assertion that happened randomly once in a blue moon
        final Array<JointEdge> list = body.getJointList();
        while (list.size > 0) {
            world.destroyJoint(list.get(0).joint);
        }
        // actual remove
        this.world.destroyBody(body);
    }
}
