package spbau2018.se.world;

import org.junit.Before;
import org.junit.Test;
import spbau2018.se.characters.Hero;
import spbau2018.se.characters.Position;
import spbau2018.se.characters.PositionUpdater;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class FieldOfViewTest {

    private PositionUpdater updater = new PositionUpdater() {
        @Override
        public void update(World world, Hero hero) {
        }
    };

    int[][] grid;
    Set<Position> exp = new HashSet<>();

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

        for (int i = 0; i < 3; i++) {
            for (int j = 1; j < 4; j++) {
                exp.add(new Position(i, j));
            }
        }
        exp.add(new Position(3, 2));

    }

    @Test
    public void updateVisiable() {
        WorldBuilder builder = new WorldBuilder();
        World world = builder.build(grid);
        FieldOfView fieldOfView = new FieldOfView(2);
        fieldOfView.updateVisiable(1, 2, world);

        Set<Position> res = new HashSet<>();
        for (int i = 0; i < world.getHeight(); i++) {
            for (int j = 0; j < world.getWidth(); j++) {
                if (fieldOfView.isVisible(i, j)) {
                    res.add(new Position(i, j));
                }
            }
        }
        assertEquals(exp, res);
    }
}