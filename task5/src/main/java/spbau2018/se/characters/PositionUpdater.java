package spbau2018.se.characters;

//import javafx.util.Pair;
import spbau2018.se.world.World;

import java.util.*;

public abstract class PositionUpdater {
    private static List<PositionUpdater> updaters = new LinkedList<>();

    public PositionUpdater() {
        updaters.add(this);
    }

    protected List<Position> BFS(World world, int startX, int startY, int finishX, int finishY) {
        boolean[][] visit = new boolean[world.getHeight()][world.getWidth()];
        visit[startX][startY] = true;
        HashMap<Position, Position> path = new HashMap<>();
        Deque<Position> deq = new ArrayDeque<>();
        deq.addLast(new Position(startX, startY));
        lab1:
        while (deq.size() > 0) {
            Position p = deq.pollFirst();
            for (int offX = -1; offX <= 1; offX++) {
                for (int offY = -1; offY <= 1; offY++) {
                    if (!world.canMoveTo(p.x() + offX, p.y() + offY)
                            || visit[p.x() + offX][p.y() + offY]) {
                        continue;
                    }
                    visit[p.x() + offX][p.y() + offY] = true;
                    path.put(new Position(p.x() + offX, p.y() + offY), new Position(p.x(), p.y()));
                    deq.addLast(new Position(p.x() + offX, p.y() + offY));
                    if (p.x() + offX == finishX && p.y() + offY == finishY) {
                        break lab1;
                    }
                }
            }
        }

        List<Position> res = new ArrayList<>();
        Position cur = new Position(finishX, finishY);
        Position prev = path.get(cur);
        res.add(cur);
        if (prev == null) {
//            return new Position(startX, startY);
            return res;
        }
        res.add(prev);
        while (prev.x() != startX || prev.y() != startY) {
            cur = prev;
            prev = path.get(cur);
            res.add(prev);
        }
        return res;
    }

    public Position getOtimalOffset(World world, int startX, int startY, int finishX, int finishY, int offset) {
        List<Position> res = BFS(world, startX, startY, finishX, finishY);
        if (res.size() < offset) {
            return null;
        }
        return res.get(res.size() - offset - 1);
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