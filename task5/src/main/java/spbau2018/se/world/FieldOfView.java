package spbau2018.se.world;

import spbau2018.se.screens.Line;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FieldOfView {
    private int viewRadius;
    private Set<Integer> knownTiles;

    private int hash(int x, int y) {
        return x * 10000 + y;
    }

    public FieldOfView(int viewRadius) {
        this.viewRadius = viewRadius;
        this.knownTiles = new HashSet<>();
    }

    public boolean isVisible(int x, int y) {
        return knownTiles.contains(hash(x, y));
    }

    private void updateAlongLine(List<Point> line, World world) {
        for (Point p : line) {
            knownTiles.add(hash(p.x, p.y));
            if (!world.canMoveTo(p.x, p.y))
                break;
        }
    }

    public void updateVisiable(int a, int b, World world) {
        knownTiles = new HashSet<>();
        List<Point> line;
        for (int x = -viewRadius; x <= +viewRadius; x++) {
            for (int y = -viewRadius; y <= +viewRadius; y++) {
                if (x * x + y * y > viewRadius * viewRadius) {
                    continue;
                }
                if (a + x < 0 || a + x >= world.getWidth()
                        || b + y < 0 || b + y >= world.getHeight())
                    continue;
                line = Line.makeLine(a, b, a + x, b + y);
                updateAlongLine(line, world);
            }
        }
    }

    public int getViewRadius() {
        return viewRadius;
    }
}
