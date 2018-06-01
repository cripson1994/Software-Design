package spbau2018.se.world;

import org.junit.Test;
import spbau2018.se.characters.Hero;
import spbau2018.se.characters.Position;
import spbau2018.se.characters.PositionUpdater;

import java.util.Random;

import static org.junit.Assert.*;

public class WorldBuilderTest {
    private WorldBuilder builder = new WorldBuilder();
    private World world = builder.buildRandom(0);
    private PositionUpdater updater = new PositionUpdater() {
        @Override
        public void update(World world, Hero hero) {
        }
    };


    @Test
    public void buildRandom() {
        for (int i = 0; i < 1000; i++) {
            Position p1 = world.getFreeTile();
            Position p2 = world.getFreeTile();
            Position res = updater.getOtimalOffset(world, p1.x(), p1.y(), p2.x(), p2.y(), 0);
            assertEquals(res != null, true);
        }
    }
}