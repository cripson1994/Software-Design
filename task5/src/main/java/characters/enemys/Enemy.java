package characters.enemys;

import characters.Character;
import characters.Hero;
import characters.PositionUpdater;
import world.World;

public interface Enemy extends Character {
    PositionUpdater updaterCast();

    boolean detectHero(World world, int heroX, int heroY);

    void die();
}
