package me.vitoremanoel.vikingsinvader.world.physics;

import com.badlogic.gdx.physics.box2d.*;
import me.vitoremanoel.vikingsinvader.entity.Persons;
import me.vitoremanoel.vikingsinvader.entity.entitys.Rock;
import me.vitoremanoel.vikingsinvader.entity.entitys.Viking;
import me.vitoremanoel.vikingsinvader.world.GameWorld;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        if(contact.getFixtureA().getUserData() instanceof Viking && contact.getFixtureB().getUserData() instanceof Rock){
            Viking v = (Viking) contact.getFixtureA().getUserData();
            GameWorld.getInstance().kill(v);
        }
        if(contact.getFixtureA().getUserData() instanceof String && contact.getFixtureA().getUserData() == "Chest" && contact.getFixtureB().getUserData() instanceof Viking){
            System.exit(0);
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }
}
