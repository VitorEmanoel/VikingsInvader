package me.vitoremanoel.vikingsinvader.util;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.World;

public class TiledMapUtil {

    public static void loadColisions(World world, MapObjects objects){
        for(MapObject object : objects){
            if(object instanceof  PolylineMapObject){
                PolylineMapObject polyObject = (PolylineMapObject) object;
                float[] vertices = polyObject.getPolyline().getTransformedVertices();
                Vector2[] worldVertices = new Vector2[vertices.length/2];
                for(int i = 0; i < worldVertices.length; i++){
                    worldVertices[i] = new Vector2(vertices[i * 2]/32, vertices[ i * 2 + 1]/32);
                }
                ChainShape chainShape = new ChainShape();
                chainShape.createChain(worldVertices);
                Body body;
                BodyDef bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.StaticBody;
                body = world.createBody(bodyDef);
                body.createFixture(chainShape, 1.0f);
                chainShape.dispose();
            }
        }
    }
}
