package spbau2018.se.characters;

import spbau2018.se.world.FieldOfView;

import java.util.logging.Logger;

public abstract class Character {
    protected static Logger log = Logger.getLogger("GameLog");
    protected int x;
    protected int y;
    protected int hp;
    protected int attack;
    protected FieldOfView fieldOfView;
    protected Drawer drawer;

    public Drawer drawerCast() {
        return drawer;
    };

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
