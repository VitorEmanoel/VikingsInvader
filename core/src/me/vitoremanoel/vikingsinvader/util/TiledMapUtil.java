package me.vitoremanoel.vikingsinvader.util;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.physics.box2d.*;

public class TiledMapUtil {

    public static void loadColisions(World world, MapObjects objects, BodyDef.BodyType type){
       loadColisions(world, objects, type, world);
    }

    public static void loadColisions(World world, MapObjects objects, BodyDef.BodyType type, Object userData){
        for(MapObject object : objects){
            if(object instanceof  PolylineMapObject){
                PolylineMapObject polyObject = (PolylineMapObject) object;
                ChainShape chainShape = new ChainShape();
                chainShape.createChain(polyObject.getPolyline().getTransformedVertices());
                Body body;
                BodyDef bodyDef = new BodyDef();
                bodyDef.type = type;
                body = world.createBody(bodyDef);
                Fixture fixture = body.createFixture(chainShape, 1.0f);
                fixture.setUserData(userData);
                chainShape.dispose();
            }
        }
    }
}
