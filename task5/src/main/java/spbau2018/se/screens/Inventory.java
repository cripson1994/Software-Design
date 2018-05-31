package spbau2018.se.screens;

import spbau2018.se.characters.Amunation;
import spbau2018.se.items.Item;
import spbau2018.se.start.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Inventory extends JPanel {

    private Cell[][] cells = new Cell[5][3];

    private Cell selectCell;
    private int dimX;
    private int dimY;
    private int itemCount = 0;
    private final int size = 15;
    private ImageIcon background = new ImageIcon("sprites/inventory.png");
    private Amunation amunation = new Amunation();

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
        amunation.paint(frame);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background.getImage(), 100, 100, 780, 500, null);
    }

    public Amunation getAmunation() {
        return amunation;
    }

    public boolean isFool() {
        return itemCount == size;
    }

    public int dimX() {
        return dimX;
    }

    public int dimY() {
        return dimY;
    }
}
