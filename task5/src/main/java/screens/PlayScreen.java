package screens;

import characters.Drawer;
import characters.Hero;
import characters.PositionUpdater;
import characters.enemys.EnemySet;
import items.Item;
import javafx.util.Pair;
import start.MainFrame;
import world.TileType;
import world.World;
import world.WorldBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;


public class PlayScreen implements Screen {

    private Window window;
    private World world;
    private final Hero hero;
    private MainDrawer main = new MainDrawer();
    private SmallDrawer small = new SmallDrawer();

    public PlayScreen() {
        world = new WorldBuilder().build(1);
        Pair<Integer, Integer> startCoords = world.getFreeTile();
        hero = new Hero(startCoords.getKey(), startCoords.getValue(), world);
        Pair<Integer, Integer> sc = world.getFreeTile();
        window = new Window(23, 23, startCoords.getKey(), startCoords.getValue());
    }

    @Override
    public void display(MainFrame frame) {
        frame.toFront();
        frame.getContentPane().removeAll();
        hero.getFieldOfView().updateVisiable(hero.x(), hero.y(), world);
        world.updateVisiable(hero);
        Drawer.drawAll(frame, window.x - window.width / 2, window.y - window.height / 2, hero.getFieldOfView());
        frame.addAndRevalidate(small);
        frame.addAndRevalidate(main);
    }

    private class MainDrawer extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            for (int i = 0; i < window.width; i++) {
                for (int j = 0; j < window.height; j++) {
                    int x = i + window.x - window.width / 2;
                    int y = j + window.y - window.height / 2;
                    ImageIcon ii = null;
                    if (world.isVisiable(x, y)) {
                        ii = new ImageIcon(world.getTilePath(x, y).getPath());
                    } else {
                        ii = new ImageIcon(TileType.UNKNOW.getPath());
                    }
                    g.drawImage(ii.getImage(), i * ii.getIconHeight(), j * ii.getIconWidth(), Color.RED, null);
                    if (!hero.getFieldOfView().isVisiable(x, y)) {
                        g.setColor(new Color(0, 0, 0, 200));
                        g.fillRect(i * ii.getIconHeight(), j * ii.getIconWidth(), ii.getIconHeight(), ii.getIconWidth());
                    } else if (world.hasItem(x, y)) {
                        ImageIcon item = new ImageIcon(world.getItem(x, y).getPath());
                        g.drawImage(item.getImage(), i * ii.getIconHeight(), j * ii.getIconWidth(), 32, 32, null);
                    }
                }
            }
        }
    }

    private class SmallDrawer extends JPanel {
        int tileSize = 4;

        @Override
        public void paintComponent(Graphics g) {
            ImageIcon ii = null;
            for (int i = 0; i < world.getWidth(); i++) {
                for (int j = 0; j < world.getHeight(); j++) {
                    if (world.isVisiable(i, j)) {
                        ii = new ImageIcon(world.getTilePath(i, j).getPath());
                    } else {
                        ii = new ImageIcon(TileType.UNKNOW.getPath());
                    }
                    g.drawImage(ii.getImage(), i * tileSize + 750, j * tileSize + 280, tileSize, tileSize, null);
                }
            }
            ii = new ImageIcon("sprites/electricity.gif");
            g.drawImage(ii.getImage(), hero.x() * tileSize + 750, hero.y() * tileSize + 280, tileSize * 2, tileSize * 2, null);

        }
    }

    private class Window {
        int x;
        int y;
        int width;
        int height;

        Window(int width, int height, int x, int y) {
            this.width = width;
            this.height = height;
            if (x <= this.width / 2) {
                this.x = this.width / 2;
            } else if (x >= world.getWidth() - this.width / 2 - 1) {
                this.x = world.getWidth() - this.width / 2 - 1;
            } else {
                this.x = x;
            }
            if (y <= this.height / 2) {
                this.y = this.height / 2;
            } else if (y >= world.getHeight() - this.height / 2 - 1) {
                this.y = world.getHeight() - this.height / 2 - 1;
            } else {
                this.y = y;
            }
        }

        void scroll(int offsetX, int offsetY) {
            if ((hero.x() < world.getWidth() - width / 2 - 1 || (hero.x() == world.getWidth() - width / 2 - 1 && offsetX == 1))
                    && (hero.x() > width / 2 || (hero.x() == width / 2 && offsetX == -1))) {
                x += offsetX;
            }
            if ((hero.y() < world.getHeight() - height / 2 - 1 || (hero.y() == world.getHeight() - height / 2 - 1 && offsetY == 1))
                    && (hero.y() > height / 2 || (hero.y() == height / 2 && offsetY == -1))) {
                y += offsetY;
            }
        }

    }

    private void clearScreen() {
        Drawer.unregisterAll();
        PositionUpdater.unregisterAll();
        EnemySet.removeAll();
    }

    @Override
    public Screen respond(KeyEvent key) {
        if (key.getExtendedKeyCode() == VK_Q) {
            return new IventoryScreen(this, hero, world);
        }

        int offsetX = 0;
        int offsetY = 0;
        switch (key.getExtendedKeyCode()) {
            case VK_UP:
                offsetY = -1;
                break;
            case VK_DOWN:
                offsetY = 1;
                break;
            case VK_RIGHT:
                offsetX = 1;
                break;
            case VK_LEFT:
                offsetX = -1;
                break;
            case VK_ENTER:
                if (world.hasItem(hero.x(), hero.y()) && !hero.getInventory().isFool()) {
                    Item item = world.removeItem(hero.x(), hero.y());
                    hero.getInventory().add(item);
                }
        }
        if (hero.updatePosition(world, offsetX, offsetY)) {
            window.scroll(offsetX, offsetY);
        }
        PositionUpdater.updateAll(world, hero);


        if (hero.getHP() < 0) {
            clearScreen();
            return new LoseScreen();
        }

        return this;
    }
}
