package spbau2018.se.characters;

import spbau2018.se.world.FieldOfView;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public abstract class Drawer extends JPanel {
    final private static List<Drawer> updaters = new LinkedList<>();

    public Drawer() {
        updaters.add(this);
    }

    public void unregister() {
        updaters.remove(this);
    }

    public static void unregisterAll() {
        updaters.clear();
    }

    public void drawLifeBar(Graphics g, int x, int y, int hp) {
        if (hp < 100) {
            int ind = (int) Math.round((double) hp / 10);
            ImageIcon im = new ImageIcon("sprites/life/VIDA_" + ind + ".png");
            g.drawImage(im.getImage(), x, y, 50, 6, null);
        }
    }

    public abstract void draw(JFrame frame, int offsetX, int offsetY, FieldOfView view);

    public static void drawAll(JFrame frame, int offsetX, int offsetY, FieldOfView view) {
        for (Drawer u : updaters) {
            u.draw(frame, offsetX, offsetY, view);
        }
    }
}
