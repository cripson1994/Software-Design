package spbau2018.se.characters;

import spbau2018.se.characters.enemys.Enemy;
import spbau2018.se.items.Item;
import spbau2018.se.screens.Inventory;
import spbau2018.se.world.FieldOfView;
import spbau2018.se.world.World;

import javax.swing.*;
import java.awt.*;

public class Hero implements spbau2018.se.characters.Character {
    private int hp = 100;
    private int attack = 40;
    private int respiteBound = 5;
    private int respite = respiteBound;
    private int x;
    private int y;
    private Drawer drawer;
    private FieldOfView fieldOfView;
    private Inventory inventory = new Inventory();

    private class HeroDrawer extends Drawer {
        int offsetX;
        int offsetY;

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

    public Hero(int x, int y, World world) {
        this.x = x;
        this.y = y;
        drawer = new HeroDrawer();
        fieldOfView = new FieldOfView(world);
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
        return inventory.getAmunation().putOn(item);
    }


    @Override
    public void bump(spbau2018.se.characters.Character character) {
        int dmg = character.getAttack() - inventory.getAmunation().getSumArmore() < 0 ?
                0 : character.getAttack() - inventory.getAmunation().getSumArmore();
        hp -= dmg;
    }

    @Override
    public int getAttack() {
        return attack + inventory.getAmunation().getSumDamage();
    }

    @Override
    public int getHP() {
        return hp;
    }

    public FieldOfView getFieldOfView() {
        return fieldOfView;
    }

    @Override
    public Drawer drawerCast() {
        return drawer;
    }

    @Override
    public int x() {
        return x;
    }

    @Override
    public int y() {
        return y;
    }
}
