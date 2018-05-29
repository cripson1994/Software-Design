package characters;

import world.World;

import javax.swing.*;

public interface Character {
    Drawer drawerCast();

    void bump(Character character);

    int getAttack();

    int getHP();

    int x();

    int y();
}
