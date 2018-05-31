package spbau2018.se.characters.enemys;

import spbau2018.se.characters.Character;
import spbau2018.se.characters.PositionUpdater;
import spbau2018.se.world.World;

public interface Enemy extends Character {
    PositionUpdater updaterCast();

    boolean detectHero(World world, int heroX, int heroY);

    void die();
}
