package spbau2018.se.characters.enemys;

import org.junit.Before;
import org.junit.Test;
import spbau2018.se.characters.Hero;
import spbau2018.se.world.World;
import spbau2018.se.world.WorldBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class EnemyTest {

    int[][] grid;
    WorldBuilder builder = new WorldBuilder();
    World world;

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
        world = builder.build(grid);
    }

    @Test
    public void detectHero() {
        Enemy enemy = new Painkiller(5,4);
        boolean res = enemy.detectHero(world, 1, 2);
        assertEquals(res, false);
        res = enemy.detectHero(world, 3,4);
        assertEquals(res, true);
    }

    @Test
    public void bump() {
        Painkiller enemy = new Painkiller(5,4);
        Hero hero = new Hero(5,4);
        enemy.bump(hero);
        assertEquals(100 - hero.getAttack(), enemy.getHP());
    }
}