package characters.enemys;

import characters.Character;
import characters.Drawer;
import characters.Hero;
import characters.PositionUpdater;
import javafx.util.Pair;
import world.FieldOfView;
import world.World;

import javax.swing.*;
import java.awt.*;

public class Painkiller implements Enemy {
    private int hp = 100;
    private int attack = 10;
    private int x;
    private int y;
    private Drawer drawer;
    private PositionUpdater updater;
    private boolean agr = false;
    private FieldOfView fieldOfView;

    private class SkeletonDrawer extends Drawer {
        int offsetX;
        int offsetY;

        @Override
        public void draw(JFrame frame, int offsetX, int offsetY, FieldOfView heroView) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            if (heroView.isVisiable(x, y)) {
                frame.add(this);
                frame.validate();
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            ImageIcon im = new ImageIcon("sprites/painkiller.gif");
            g.drawImage(im.getImage(), x * 32 - offsetX * 32,
                    y * 32 - offsetY * 32, null);
            drawLifeBar(g, x * 32 - offsetX * 32 - 5, y * 32 - offsetY * 32 + 40, hp);
        }
    }

    private class SkeletonPositionUpdater extends PositionUpdater {

        @Override
        public void update(World world, Hero hero) {
            if (!detectHero(world, hero.x(), hero.y())) {
                return;
            }
            Pair<Integer, Integer> res = BFS(world, x, y, hero.x(), hero.y());
            if (world.canMoveTo(res.getKey(), res.getValue()) && EnemySet.getEnemy(res.getKey(), res.getValue()) == null) {
                if (res.getKey() == hero.x() && res.getValue() == hero.y()) {
                    hero.bump(Painkiller.this);
                } else {
                    x = res.getKey();
                    y = res.getValue();
                }
            }
        }
    }

    public Painkiller(int x, int y, World world) {
        this.x = x;
        this.y = y;
        drawer = new Painkiller.SkeletonDrawer();
        updater = new Painkiller.SkeletonPositionUpdater();
        fieldOfView = new FieldOfView(world);
    }


    @Override
    public PositionUpdater updaterCast() {
        return updater;
    }

    @Override
    public boolean detectHero(World world, int heroX, int heroY) {
        fieldOfView.updateVisiable(x, y, world);
        if (fieldOfView.isVisiable(heroX, heroY)) {
            agr = true;
        }
        return agr;
    }

    @Override
    public void die() {
        drawer.unregister();
        updater.unregister();
        EnemySet.remove(this);
    }

    @Override
    public void bump(Character character) {
        hp -= character.getAttack();
        if (hp <= 0) {
            die();
        }
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getHP() {
        return hp;
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
