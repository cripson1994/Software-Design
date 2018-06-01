package spbau2018.se.characters.enemys;

import spbau2018.se.characters.Position;
import spbau2018.se.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс - фабрика для всех врагов
 */
public class EnemySet {
    private static final List<Enemy> enemies = new ArrayList<>();

    /**
     * инициализация врогов на уровне
     * @param world мир, где надо инициализировать
     * @param level номер уровня
     */
    public static void init(World world, int level) {
        for (int i = 0; i < 15; i++) {
            Position freeTail = world.getFreeTile();
            enemies.add(new Painkiller(freeTail.x(), freeTail.y()));
        }
    }

    /**
     * @param x координавта x
     * @param y координата y
     * @return врага, стоящего на координате x, y
     */
    public static Enemy getEnemy(int x, int y) {
        for (Enemy character : enemies) {
            if (character.x() == x && character.y() == y) {
                return character;
            }
        }
        return null;
    }

    /**
     * убирает убитого врага из списка всех врагов
     * @param enemy враг, которого надо убоать
     */
    public static void remove(Enemy enemy) {
        enemies.remove(enemy);
    }

    /**
     * убирает всех врагов
     */
    public static void removeAll() {
        enemies.clear();
    }
}
