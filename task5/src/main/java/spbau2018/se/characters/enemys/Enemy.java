package spbau2018.se.characters.enemys;

import spbau2018.se.characters.Character;
import spbau2018.se.characters.PositionUpdater;
import spbau2018.se.world.World;

import java.util.logging.Logger;

public abstract class Enemy extends Character {
    protected PositionUpdater updater;
    protected boolean agr = false;

    public PositionUpdater updaterCast() {
        return updater;
    }

    public boolean detectHero(World world, int heroX, int heroY) {
        fieldOfView.updateVisiable(x, y, world);
        if (fieldOfView.isVisible(heroX, heroY)) {
            agr = true;
        }
        return agr;
    }

    public void die() {
        drawer.unregister();
        updater.unregister();
        EnemySet.remove(this);
        log.info(this.getClass().getSimpleName() + " was killed");
    }

    @Override
    public void bump(Character character) {
        hp -= character.getAttack();
        if (hp <= 0) {
            die();
        }
    }
}
