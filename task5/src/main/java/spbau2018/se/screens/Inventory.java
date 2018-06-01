package spbau2018.se.screens;

import spbau2018.se.characters.Ammunation;
import spbau2018.se.items.Item;
import spbau2018.se.start.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Inventory extends JPanel {

    /**
     * ячейки инвентаря
     */
    private Cell[][] cells = new Cell[5][3];

    /**
     * Выбранная ячейка
     */
    private Cell selectCell;
    /**
     * Размерность инвентаря
     */
    private int dimX;
    private int dimY;
    /**
     * Колличесство предметов в инвентаре
     */
    private int itemCount = 0;
    /**
     * Размер инвентаря
     */
    private final int size = 15;
    /**
     * Изображение для отрисовки
     */
    private ImageIcon background = new ImageIcon("sprites/inventory.png");
    /**
     * Аммунация в инвентаре
     */
    private Ammunation ammunation = new Ammunation();

    /**
     * Добавляет предмет в инвентарь
     * @param item добавляемый предмет
     */
    public void add(Item item) {
        for (int j = 0; j < dimY; j++) {
            for (int i = 0; i < dimX; i++) {
                if (!cells[i][j].hasItem()) {
                    cells[i][j].setItem(item);
                    itemCount++;
                    return;
                }
            }
        }
    }

    /**
     * Удаляет предмет из инвентаря
     * @return ранее хранимый предмет в выбранной чейке
     */
    public Item remove() {
        itemCount--;
        return selectCell.removeItem();
    }

    public Inventory() {
        dimX = 5;
        dimY = 3;
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {
                cells[i][j] = new Cell("sprites/cell.png", 220 + 100 * i, 220 + 100 * j);
            }
        }
        selectCell = cells[0][0];
    }

    /**
     * Отрисовщик
     * @param frame кадр, на котором будет происходить отрисовка
     * @param x координата x выбранной ячейки
     * @param y координата y выбранной ячейки
     */
    public void paint(MainFrame frame, int x, int y) {
        selectCell.unselect();
        cells[x][y].select();
        selectCell = cells[x][y];
        frame.add(this, 0);
        frame.revalidate();
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {
                frame.add(cells[i][j], 0);
                frame.revalidate();
            }
        }
        ammunation.paint(frame);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background.getImage(), 100, 100, 780, 500, null);
    }

    public Ammunation getAmmunation() {
        return ammunation;
    }

    public boolean isFull() {
        return itemCount == size;
    }

    public int dimX() {
        return dimX;
    }

    public int dimY() {
        return dimY;
    }
}
