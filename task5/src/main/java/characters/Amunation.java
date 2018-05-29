package characters;

import items.Item;
import screens.Cell;
import start.MainFrame;

public class Amunation {
    private Cell[] cells = new Cell[3];
    private Item armore = null;
    private Item weapon = null;


    public Amunation() {
        cells[0] = new Cell("sprites/amunationCell.png", 720, 220);
        cells[1] = new Cell("sprites/amunationCell.png", 720, 320);
        cells[2] = new Cell("sprites/amunationCell.png", 720, 420);
    }

    public int getSumDamage() {
        int armoreDmg = armore == null ? 0 : armore.getDamage();
        int weaponDmg = weapon == null ? 0 : weapon.getDamage();
        return armoreDmg + weaponDmg;
    }

    public int getSumArmore() {
        int armoreArm = armore == null ? 0 : armore.getArmore();
        int weaponArm = weapon == null ? 0 : weapon.getArmore();
        return armoreArm + weaponArm;
    }

    public Item putOn(Item item) {
        Item old = null;
        switch (item.type()) {
            case WEAPON:
                cells[0].setItem(item);
                old = weapon;
                weapon = item;
                break;
            case ARMORE:
                cells[1].setItem(item);
                old = armore;
                armore = item;
                break;
        }
        return old;
    }

    public Item takeOffArmore() {
        Item old = armore;
        armore = null;
        return old;
    }

    public Item takeOffWeapon() {
        Item old = weapon;
        armore = null;
        return old;
    }

    public void paint(MainFrame frame) {
        for (int i = 0; i < 3; i++) {
            frame.add(cells[i], 0);
            frame.revalidate();
        }
    }
}
