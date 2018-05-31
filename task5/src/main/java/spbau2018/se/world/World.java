package spbau2018.se.world;

import spbau2018.se.characters.Hero;
import spbau2018.se.characters.enemys.Enemy;
import spbau2018.se.characters.enemys.EnemySet;
import javafx.util.Pair;
import spbau2018.se.items.Item;

import java.util.Random;

public class World {
    private Tile[][] tiles;
    private final boolean knownTiles[][];
    private int width;
    private int height;

    World(Tile[][] tiles, int level) {
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
        this.knownTiles = new boolean[width][height];
        fillItems(level);
        EnemySet.init(this, level);
    }

    private void fillItems(int level) {
        int itemsCount = Item.values().length;
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            Pair<Integer, Integer> freeTail = getFreeTile();
            Item item = Item.values()[rand.nextInt(itemsCount)];
            tiles[freeTail.getKey()][freeTail.getValue()].setItem(item);
        }
    }

    public void setItem(Item item, int x, int y) {
        tiles[x][y].setItem(item);
    }

    public Item getItem(int x, int y) {
        return tiles[x][y].getItem();
    }

    public Item removeItem(int x, int y) {
        return tiles[x][y].removeItem();
    }

    public Pair<Integer, Integer> getFreeTile() {
        int x;
        int y;
        do {
            x = (int) (Math.random() * width);
            y = (int) (Math.random() * height);
        }
        while (!canMoveTo(x, y) && !hasItem(x, y));
        return new Pair<>(x, y);
    }

    public boolean isVisiable(int x, int y) {
        return knownTiles[x][y];
    }

    public void updateVisiable(Hero hero) {
        int radius = hero.getFieldOfView().getViewRadius();
        for (int x = hero.x() - radius; x < hero.x() + radius; x++) {
            for (int y = hero.y() - radius; y < hero.y() + radius; y++) {
                if (hero.getFieldOfView().isVisiable(x, y)) {
                    knownTiles[x][y] = true;
                }
            }
        }
    }

    public boolean hasItem(int x, int y) {
        return getItem(x, y) != null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public TileType getTilePath(int x, int y) {
        return tiles[x][y].getType();
    }

    public boolean canMoveTo(int x, int y) {
        return TileType.isFreeTile(tiles[x][y].getType());
    }

    public Enemy getEnemy(int x, int y) {
        return EnemySet.getEnemy(x, y);
    }
}
