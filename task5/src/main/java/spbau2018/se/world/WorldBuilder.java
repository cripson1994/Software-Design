package spbau2018.se.world;

import com.github.czyzby.noise4j.map.Grid;
import com.github.czyzby.noise4j.map.generator.room.dungeon.DungeonGenerator;

/**
 * Класс, реализующий билдера мира
 */
public class WorldBuilder {
    /**
     * Генерирует рандомный мир, в зависимости от уровня
     *
     * @param level уровень
     * @return случайный мир
     */
    public World buildRandom(int level) {
        int size = 50;
        final Grid grid = new Grid(size);
        buildGrid(grid);
        Tile[][] tiles = new Tile[grid.getHeight()][grid.getWidth()];
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                TileType type = grid.get(i, j) == 1 ? TileType.WALL : TileType.FLOOR;
                tiles[i][j] = new Tile(type);
            }
        }
        return new World(tiles, level, true);
    }

    /**
     * Генерирует мир по передаваемой сетке (0 - если свободная ячека и 1 - если занятая)
     *
     * @param grid сетка
     * @return готовый мир
     */
    public World build(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        Tile[][] tiles = new Tile[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                TileType type = grid[i][j] == 1 ? TileType.WALL : TileType.FLOOR;
                tiles[i][j] = new Tile(type);
            }
        }
        return new World(tiles, 0, false);
    }

    private void buildGrid(Grid grid) {
        final DungeonGenerator dungeonGenerator = new DungeonGenerator();
        dungeonGenerator.setRoomGenerationAttempts(30);
        dungeonGenerator.setMaxRoomSize(11);
        dungeonGenerator.setTolerance(3); // Max difference between width and height.
        dungeonGenerator.setMinRoomSize(5);
        dungeonGenerator.generate(grid);
    }
}
