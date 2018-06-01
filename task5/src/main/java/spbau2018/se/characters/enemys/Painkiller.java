package spbau2018.se.characters.enemys;

import spbau2018.se.characters.Drawer;
import spbau2018.se.characters.Hero;
import spbau2018.se.characters.Position;
import spbau2018.se.characters.PositionUpdater;
import javafx.util.Pair;
import spbau2018.se.world.FieldOfView;
import spbau2018.se.world.World;

import javax.swing.*;
import java.awt.*;

public class Painkiller extends Enemy {


    {
        hp = 100;
        attack = 10;
    }

    private class SkeletonDrawer extends Drawer {
        @Override
        public void draw(JFrame frame, int offsetX, int offsetY, FieldOfView heroView) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            if (heroView.isVisible(x, y)) {
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
            Position res = getOtimalOffset(world, x, y, hero.x(), hero.y(), 1);
            if (world.canMoveTo(res.x(), res.y()) && EnemySet.getEnemy(res.x(), res.y()) == null) {
                if (res.x() == hero.x() && res.y() == hero.y()) {
                    hero.bump(Painkiller.this);
                } else {
                    x = res.x();
                    y = res.y();
                }
            }
        }
    }

    public Painkiller(int x, int y) {
        this.x = x;
        this.y = y;
        drawer = new Painkiller.SkeletonDrawer();
        updater = new Painkiller.SkeletonPositionUpdater();
        fieldOfView = new FieldOfView(8);
    }
}
