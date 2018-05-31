package spbau2018.se.characters;

import javafx.util.Pair;
import spbau2018.se.world.World;

import java.util.*;

public abstract class PositionUpdater {
    private static List<PositionUpdater> updaters = new LinkedList<>();

    public PositionUpdater() {
        updaters.add(this);
    }

    protected Pair<Integer, Integer> BFS(World world, int startX, int startY, int finishX, int finishY) {
        boolean[][] visit = new boolean[world.getHeight()][world.getWidth()];
        visit[startX][startY] = true;
        HashMap<Pair<Integer, Integer>, Pair<Integer, Integer>> path = new HashMap<>();
        Deque<Pair<Integer, Integer>> deq = new ArrayDeque<>();
        deq.addLast(new Pair<>(startX, startY));
        lab1:
        while (deq.size() > 0) {
            Pair<Integer, Integer> p = deq.pollFirst();
            for (int offX = -1; offX <= 1; offX++) {
                for (int offY = -1; offY <= 1; offY++) {
                    if (!world.canMoveTo(p.getKey() + offX, p.getValue() + offY)
                            || visit[p.getKey() + offX][p.getValue() + offY]) {
                        continue;
                    }
                    visit[p.getKey() + offX][p.getValue() + offY] = true;
                    path.put(new Pair<>(p.getKey() + offX, p.getValue() + offY), new Pair<>(p.getKey(), p.getValue()));
                    deq.addLast(new Pair<>(p.getKey() + offX, p.getValue() + offY));
                    if (p.getKey() + offX == finishX && p.getValue() + offY == finishY) {
                        break lab1;
                    }
                }
            }
        }

        Pair<Integer, Integer> cur = new Pair<>(finishX, finishY);
        Pair<Integer, Integer> prev = path.get(cur);
        if (prev == null) {
            return new Pair<>(startX, startY);
        }
        while (prev.getKey() != startX || prev.getValue() != startY) {
            cur = prev;
            prev = path.get(cur);
        }
        return cur;
    }

    public void unregister() {
        updaters.remove(this);
    }

    public static void unregisterAll() {
        updaters.clear();
    }

    public abstract void update(World world, Hero hero);

    public static void updateAll(World world, Hero hero) {
        for (PositionUpdater u : updaters) {
            u.update(world, hero);
        }
    }
}