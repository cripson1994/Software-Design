package spbau2018.se.characters;

import spbau2018.se.characters.enemys.Enemy;
import spbau2018.se.items.Item;
import spbau2018.se.screens.Inventory;
import spbau2018.se.world.FieldOfView;
import spbau2018.se.world.World;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class Hero extends spbau2018.se.characters.Character {
    private int respiteBound = 5;
    private int respite = respiteBound;
    private Inventory inventory = new Inventory();

    {
        hp = 100;
        attack = 40;
    }

    private class HeroDrawer extends Drawer {
        @Override
        public void draw(JFrame frame, int offsetX, int offsetY, FieldOfView view) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            frame.add(this);
            frame.validate();
        }

        @Override
        public void paintComponent(Graphics g) {
            ImageIcon im = new ImageIcon("sprites/barbarian.gif");
            g.drawImage(im.getImage(), x * 32 - offsetX * 32,
                    y * 32 - offsetY * 32, null);
            drawLifeBar(g, x * 32 - offsetX * 32 - 5, y * 32 - offsetY * 32 + 40, hp);
        }
    }

    public Hero(int x, int y) {
        this.x = x;
        this.y = y;
        drawer = new HeroDrawer();
        fieldOfView = new FieldOfView(8);
    }

    public boolean updatePosition(World world, int offsetX, int offsetY) {
        if (world.canMoveTo(x + offsetX, y + offsetY)) {
            Enemy enemy = world.getEnemy(x + offsetX, y + offsetY);
            if (enemy == null) {
                respite = respite < respiteBound ? respite + 1 : respite;
                hp = respite == respiteBound ? hp + 5 : hp;
                hp = hp > 100 ? 100 : hp;
                x += offsetX;
                y += offsetY;
                return true;
            }
            respite = 0;
            enemy.bump(this);
        }
        return false;
    }

    public Inventory getInventory() {
        return inventory;
    }


    public Item putOnItem(Item item) {
        log.info("put on " + item.type() + ": " + item.name());
        return inventory.getAmmunation().putOn(item);
    }


    @Override
    public void bump(Character character) {
        int dmg = character.getAttack() - inventory.getAmmunation().getSumArmore() < 0 ?
                0 : character.getAttack() - inventory.getAmmunation().getSumArmore();
        hp -= dmg;
    }

    @Override
    public int getAttack() {
        return attack + inventory.getAmmunation().getSumDamage();
    }

}
