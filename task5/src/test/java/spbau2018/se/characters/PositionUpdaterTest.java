package spbau2018.se.characters;

import org.junit.Before;
import org.junit.Test;
import spbau2018.se.world.World;
import spbau2018.se.world.WorldBuilder;

import java.io.File;
import java.io.IOException;
import java.lang.Character;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.IntConsumer;

import static org.junit.Assert.*;

public class PositionUpdaterTest {

    private PositionUpdater updater = new PositionUpdater() {
        @Override
        public void update(World world, Hero hero) {
        }
    };

    int[][] grid;

    @Before
    public void init() throws IOException {
        grid = new int[10][8];
        List<String> lines = Files.readAllLines(Paths.get("src/test/testResourses/map.txt"), StandardCharsets.UTF_8);
        int row = 0;
        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                grid[row][i] = line.charAt(i) - 48;
            }
            row++;
        }
    }

    @Test
    public void BFS() throws IOException {
        WorldBuilder builder = new WorldBuilder();
        World world = builder.build(grid);
        Position res = updater.getOtimalOffset(world, 1, 2, 7, 3, 1);
        assertEquals(new Position(2, 2), res);

        res = updater.getOtimalOffset(world, 4, 6, 1, 6, 3);
        assertEquals(new Position(1, 6), res);
    }
}