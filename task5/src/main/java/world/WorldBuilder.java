package world;

import com.github.czyzby.noise4j.map.Grid;
import com.github.czyzby.noise4j.map.generator.room.dungeon.DungeonGenerator;

public class WorldBuilder {
    public World build(int level) {
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
        return new World(tiles, level);
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
