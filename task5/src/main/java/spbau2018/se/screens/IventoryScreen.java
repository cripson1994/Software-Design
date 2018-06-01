package spbau2018.se.screens;

import spbau2018.se.characters.Hero;
import spbau2018.se.items.Item;
import spbau2018.se.start.MainFrame;
import spbau2018.se.world.World;

import java.awt.event.*;

import static java.awt.event.KeyEvent.*;

/**
 * Класс, для отображения сцены с открытым инвентарём
 */
public class IventoryScreen implements Screen {

    private Screen prev;
    private World world;
    private Hero hero;
    private int x = 0;
    private int y = 0;

    public IventoryScreen(Screen prev, Hero hero, World world) {
        this.prev = prev;
        this.hero = hero;
        this.world = world;
    }

    @Override
    public void display(MainFrame frame) {
        hero.getInventory().paint(frame, x, y);
    }

    @Override
    public Screen respond(KeyEvent key) {
        int offsetY = 0;
        int offsetX = 0;
        Item item;
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
                item = hero.getInventory().remove();
                if (item != null) {
                    item.action(hero);
                }
                break;
            case VK_BACK_SPACE:
                if (!world.hasItem(hero.x(), hero.y())) {
                    item = hero.getInventory().remove();
                    world.setItem(item, hero.x(), hero.y()); //TODO выбрасывать на ближайшую свободную
                    log.info("throw out " + item.type() + ": " + item.name());
                }
                break;
        }
        x = (x + offsetX >= hero.getInventory().dimX() || x + offsetX < 0) ? x : x + offsetX;
        y = (y + offsetY >= hero.getInventory().dimY() || y + offsetY < 0) ? y : y + offsetY;
        return key.getExtendedKeyCode() == KeyEvent.VK_Q ? prev : this;
    }
}
