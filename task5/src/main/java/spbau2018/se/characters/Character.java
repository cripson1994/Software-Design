package spbau2018.se.characters;

import spbau2018.se.world.FieldOfView;

import java.util.logging.Logger;

public abstract class Character {
    protected static Logger log = Logger.getLogger("GameLog");
    protected int x;
    protected int y;
    protected int hp;
    protected int attack;
    /**
     * Область видимости
     */
    protected FieldOfView fieldOfView;
    /**
     * Отрисовщик
     */
    protected Drawer drawer;

    /**
     * Метод, принимающий атаку сос стороны другого персонажа.
     *
     * @param character атакующий персонаж
     */
    public abstract void bump(Character character);

    public int getAttack() {
        return attack;
    }

    public int getHP() {
        return hp;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public FieldOfView getFieldOfView() {
        return fieldOfView;
    }
}
