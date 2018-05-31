package spbau2018.se.world;

import spbau2018.se.items.Item;

public class Tile {
    private TileType type;
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

    public void setItem(Item item) {
        this.item = item;
    }

    public Item removeItem() {
        Item res = item;
        this.item = null;
        return res;
    }
}
