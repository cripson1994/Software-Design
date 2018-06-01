package spbau2018.se.world;

import spbau2018.se.characters.Hero;
import spbau2018.se.characters.Position;
import spbau2018.se.characters.enemys.Enemy;
import spbau2018.se.characters.enemys.EnemySet;
import javafx.util.Pair;
import spbau2018.se.items.Item;

import java.util.Random;

/**
 * Класс, реализующий мир
 */
public class World {
    /**
     * Тайлы мира
     */
    private Tile[][] tiles;
    /**
     * Известные тайлы
     */
    private final boolean knownTiles[][];
    private int width;
    private int height;
    private Random random = new Random();

    World(Tile[][] tiles, int level, boolean enemys) {
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
        this.knownTiles = new boolean[width][height];
        fillItems(level);
        if (enemys) {
            EnemySet.init(this, level);
        }
    }

    private void fillItems(int level) {
        int itemsCount = Item.values().length;
        for (int i = 0; i < 10; i++) {
            Position freeTail = getFreeTile();
            Item item = Item.values()[random.nextInt(itemsCount)];
            tiles[freeTail.x()][freeTail.y()].setItem(item);
        }
    }

    public void setItem(Item item, int x, int y) {
        tiles[x][y].setItem(item);
    }

    public Item getItem(int x, int y) {
        return tiles[x][y].getItem();
    }

    /**
     * Удаляет предмет из нужной ячейки карты
     *
     * @param x координата x ячейки из которой удаляем
     * @param y координата y ячейки из которой удаляем
     * @return предмет ранее хранимый в ячейке тайла
     */
    public Item removeItem(int x, int y) {
        return tiles[x][y].removeItem();
    }

    /**
     * @return случайную свободную ячейку на карте
     */
    public Position getFreeTile() {
        int x;
        int y;
        do {
            x = (int) (random.nextDouble() * width);
            y = (int) (random.nextDouble() * height);
        }
        while (!canMoveTo(x, y) && !hasItem(x, y));
        return new Position(x, y);
    }

    /**
     * Проверяет, видим ли тайл
     *
     * @param x координата x тайла
     * @param y координата y тайла
     * @return true, если видима, иначе false
     */
    public boolean isVisiable(int x, int y) {
        return knownTiles[x][y];
    }

    /**
     * Обновляет видимую зону мира
     *
     * @param hero герой, относительно которого обновляем видимую зону
     */
    public void updateVisiable(Hero hero) {
        int radius = hero.getFieldOfView().getViewRadius();
        for (int x = hero.x() - radius; x < hero.x() + radius; x++) {
            for (int y = hero.y() - radius; y < hero.y() + radius; y++) {
                if (hero.getFieldOfView().isVisible(x, y)) {
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

    /**
     * Проверяет, можно ли стоять на тайле с передаваемыми координатами
     *
     * @param x координата x тайла
     * @param y координата y тайла
     * @return true, если на тайле можно стоять, иначе false
     */
    public boolean canMoveTo(int x, int y) {
        return TileType.isFreeTile(tiles[x][y].getType());
    }

    /**
     * Возращает врага на карте, стоящего на передаваемой позиции
     * @param x координата x
     * @param y координата y
     * @return Enemy, если на позиции есть враг и null в противном случае
     */
    public Enemy getEnemy(int x, int y) {
        return EnemySet.getEnemy(x, y);
    }
}
