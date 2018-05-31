package spbau2018.se.characters.enemys;

import javafx.util.Pair;
import spbau2018.se.world.World;

import java.util.ArrayList;
import java.util.List;

public class EnemySet {
    private static final List<Enemy> enemies = new ArrayList<>();
    ;

    public static void init(World world, int level) {
        for (int i = 0; i < 15; i++) {
            Pair<Integer, Integer> freeTail = world.getFreeTile();
            enemies.add(new Painkiller(freeTail.getKey(), freeTail.getValue(), world));
        }
    }

    public static Enemy getEnemy(int x, int y) {
        for (Enemy character : enemies) {
            if (character.x() == x && character.y() == y) {
                return character;
            }
        }
        return null;
    }

    public static void remove(Enemy enemy) {
        enemies.remove(enemy);
    }

    public static void removeAll() {
        enemies.clear();
    }
}
