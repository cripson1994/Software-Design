package spbau2018.se.screens;

import spbau2018.se.items.Item;

import javax.swing.*;
import java.awt.*;

public class AmunationCells extends JPanel {
    private Item armor;
    private Item weapon;
    private ImageIcon cellIcon = new ImageIcon("sprites/amunationCell.png");

    public Item setItem(Item item) {
        Item old = null;
        switch (item.type()) {
            case WEAPON:
                old = weapon;
                weapon = item;
                break;
            case ARMORE:
                old = armor;
                armor = item;
                break;
        }
        return old;
    }

    public Item getArmor() {
        return armor;
    }

    public Item getWeapon() {
        return weapon;
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int i = 0; i < 3; i++) {
            g.drawImage(cellIcon.getImage(), 720, 220 + i * 100, 50, 50, null);
        }
        if (weapon != null) {
            ImageIcon im = new ImageIcon(weapon.getPath());
            g.drawImage(im.getImage(), 720, 220, 50, 50, null);
        }
        if (armor != null) {
            ImageIcon im = new ImageIcon(armor.getPath());
            g.drawImage(im.getImage(), 720, 320, 50, 50, null);
        }
    }
}
