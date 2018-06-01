package spbau2018.se.world;

import spbau2018.se.items.Item;

/**
 * Класс, реализующий отдельный тайл
 */
public class Tile {
    /**
     * Тип тайла
     */
    private TileType type;
    /**
     * Предмет, который хранится в данной ячейке
     */
    private Item item = null;

    public Tile(TileType type) {
        this.type = type;
    }


    public TileType getType() {
        return type;
    }


    public Item getItem() {
        return item;
    }

    /**
     * Добавляет предмет в ячейку тайла
     * @param item добавляемый предмет
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Удаляет предмет из ячейки тайла
     * @return ранее хранимый предмет
     */
    public Item removeItem() {
        Item res = item;
        this.item = null;
        return res;
    }
}
