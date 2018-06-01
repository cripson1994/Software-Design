package spbau2018.se.characters;

import spbau2018.se.items.Item;
import spbau2018.se.screens.AmunationCells;
import spbau2018.se.start.MainFrame;

/**
 * Класс, реализующий аммунацию героя
 */
public class Ammunation {
    /**
     * Ячейки аммунации
     */
    private AmunationCells cells = new AmunationCells();

    /**
     * @return суммарный прирост атаки от аммунации
     */
    public int getSumDamage() {
        int armoreDmg = cells.getArmor() == null ? 0 : cells.getArmor().getDamage();
        int weaponDmg = cells.getWeapon() == null ? 0 : cells.getWeapon().getDamage();
        return armoreDmg + weaponDmg;
    }

    /**
     * @return суммарный прирост защиты от аммунации
     */
    public int getSumArmore() {
        int armoreArm = cells.getArmor() == null ? 0 : cells.getArmor().getArmore();
        int weaponArm = cells.getWeapon() == null ? 0 : cells.getWeapon().getArmore();
        return armoreArm + weaponArm;
    }

    /**
     * метод, добавляющий предмет к аммунации
     * @param item предмет, который одевается
     * @return предмет, который был ранее одет (null, если соответствующая ячейка была пуста)
     */
    public Item putOn(Item item) {
        return cells.setItem(item);
    }


    /**
     * отрисовка аммунации
     * @param frame кадр, на котором происходит отрисовка
     */
    public void paint(MainFrame frame) {
        for (int i = 0; i < 3; i++) {
            frame.add(cells, 0);
            frame.revalidate();
        }
    }
}
