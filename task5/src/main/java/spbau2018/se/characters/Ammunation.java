package spbau2018.se.characters;

import spbau2018.se.items.Item;
import spbau2018.se.screens.AmunationCells;
import spbau2018.se.start.MainFrame;

public class Ammunation {
    AmunationCells cells = new AmunationCells();

    public int getSumDamage() {
        int armoreDmg = cells.getArmor() == null ? 0 : cells.getArmor().getDamage();
        int weaponDmg = cells.getWeapon() == null ? 0 : cells.getWeapon().getDamage();
        return armoreDmg + weaponDmg;
    }

    public int getSumArmore() {
        int armoreArm = cells.getArmor() == null ? 0 : cells.getArmor().getArmore();
        int weaponArm = cells.getWeapon() == null ? 0 : cells.getWeapon().getArmore();
        return armoreArm + weaponArm;
    }

    public Item putOn(Item item) {
        return cells.setItem(item);
    }


    public void paint(MainFrame frame) {
        for (int i = 0; i < 3; i++) {
            frame.add(cells, 0);
            frame.revalidate();
        }
    }
}
