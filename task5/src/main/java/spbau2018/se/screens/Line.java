package spbau2018.se.screens;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, реализующий прямые линии
 */
public class Line {

    /**
     * Создаёт прямую
     * @param x0 координата x начала
     * @param y0 координата y начала
     * @param x1 координата x конца
     * @param y1 координата y конца
     * @return готовую прямую (последовательность точек, записанную в list)
     */
    public static List<Point> makeLine(int x0, int y0, int x1, int y1) {
        List<Point> points = new ArrayList<>();

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            points.add(new Point(x0, y0));

            if (x0 == x1 && y0 == y1)
                break;

            int e2 = err * 2;
            if (e2 > -dx) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
        return points;
    }
}