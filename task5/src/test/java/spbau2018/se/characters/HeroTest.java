package spbau2018.se.characters;

import org.junit.Before;
import org.junit.Test;
import spbau2018.se.characters.enemys.Enemy;
import spbau2018.se.characters.enemys.Painkiller;
import spbau2018.se.items.Item;
import spbau2018.se.world.World;
import spbau2018.se.world.WorldBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class HeroTest {

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
    public void updatePosition() {
        Hero hero = new Hero(5, 4);
        hero.updatePosition(world, 0, 1);
        assertEquals(new Position(5, 4), new Position(hero.x, hero.y));

        hero.updatePosition(world, -1, 0);
        System.out.println(hero.x());
        assertEquals(new Position(4, 4), new Position(hero.x, hero.y));
    }

    @Test
    public void bump() {
        Hero hero = new Hero(5, 4);
        Painkiller enemy = new Painkiller(5, 4);
        hero.bump(enemy);
        assertEquals(100 - enemy.getAttack(), hero.getHP());
    }

    @Test
    public void putOnItem() {
        Hero hero = new Hero(5, 4);
        Painkiller enemy = new Painkiller(5, 4);
        hero.putOnItem(Item.LEATHER_ARMOR);
        hero.bump(enemy);
        assertEquals(100 - enemy.getAttack() + Item.LEATHER_ARMOR.getArmore(), hero.getHP());


        int oldHeroAttack = hero.getAttack();
        hero.putOnItem(Item.MACE);
        enemy.bump(hero);
        assertEquals(100 - oldHeroAttack - Item.MACE.getDamage(), enemy.getHP());
    }
}