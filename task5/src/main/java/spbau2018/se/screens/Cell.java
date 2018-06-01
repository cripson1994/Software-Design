package spbau2018.se.screens;

import spbau2018.se.items.Item;

import javax.swing.*;
import java.awt.*;

/**
 * Класс, реализующий отдельные ячейки инвентаря
 */
public class Cell extends JPanel {

    /**
     * Хранимый предмет
     */
    private Item item;
    /**
     * Выбрана ли ячейка пользователем
     */
    private boolean isSelect = false;
    /**
     * Координаты для отрисовки
     */
    private int x;
    private int y;
    /**
     * Отрисовываемое изображение
     */
    private ImageIcon cell;

    public Cell(String path, int x, int y) {
        this.x = x;
        this.y = y;
        cell = new ImageIcon(path);
    }

    /**
     * Кладёт предмет в ячейку
     *
     * @param item добавляемый предмет
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Проверяет,не  пуста ли ячейка
     *
     * @return true, если непуста, иначе false
     */
    public boolean hasItem() {
        return item != null;
    }

    /**
     * Удаляет предмет из чейки
     *
     * @return удаляемый предмет
     */
    public Item removeItem() {
        Item res = item;
        this.item = null;
        return res;
    }

    /**
     * Выбрать ячейку
     */
    public void select() {
        isSelect = true;
    }

    /**
     * Снять выделение ячейки
     */
    public void unselect() {
        isSelect = false;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(cell.getImage(), x, y, 50, 50, null);
        if (item != null) {
            ImageIcon im = new ImageIcon(item.getPath());
            g.drawImage(im.getImage(), x, y, 50, 50, null);
        }
        if (!isSelect) {
            g.setColor(new Color(0, 0, 0, 60));
            g.fillRect(x, y, 50, 50);
        }
    }
}
