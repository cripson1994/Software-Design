package spbau2018.se.screens;

import spbau2018.se.items.Item;

import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {

    private Item item;
    private boolean isSelect = false;
    private int x;
    private int y;
    private ImageIcon cell;

    public Cell(String path, int x, int y) {
        this.x = x;
        this.y = y;
        cell = new ImageIcon(path);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean hasItem() {
        return item != null;
    }

    public Item removeItem() {
        Item res = item;
        this.item = null;
        return res;
    }

    public void select() {
        isSelect = true;
    }

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
