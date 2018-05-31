package spbau2018.se.characters;

public interface Character {
    Drawer drawerCast();

    void bump(Character character);

    int getAttack();

    int getHP();

    int x();

    int y();
}
