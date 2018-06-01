package spbau2018.se.characters.enemys;

import spbau2018.se.characters.Character;
import spbau2018.se.characters.PositionUpdater;
import spbau2018.se.world.World;

import java.util.logging.Logger;

/**
 * Общий абстактный класс врагов.
 */
public abstract class Enemy extends Character {

    /**
     * Подписка на обновление позиции
     */
    protected PositionUpdater updater;

    /**
     * Является ли персонаж агрессивным по отношению к герою или нет
     */
    protected boolean agr = false;

    public PositionUpdater updaterCast() {
        return updater;
    }

    /**
     * Проверка, замечен ли герой, так же меняет внутренее состояние агрессии персонажа
     *
     * @param world мир
     * @param heroX координата x героя
     * @param heroY координата y героя
     * @return true, если обнаружен, иначе false
     */
    public boolean detectHero(World world, int heroX, int heroY) {
        fieldOfView.updateVisiable(x, y, world);
        if (fieldOfView.isVisible(heroX, heroY)) {
            agr = true;
        }
        return agr;
    }


    private void die() {
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
