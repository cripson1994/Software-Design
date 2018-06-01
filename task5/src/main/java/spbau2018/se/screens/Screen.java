package spbau2018.se.screens;

import spbau2018.se.start.MainFrame;

import java.awt.event.KeyEvent;
import java.util.logging.Logger;

/**
 * Интерфейс отображаемых сцен
 */
public interface Screen {
    Logger log = Logger.getLogger("GameLog");

    /**
     * Отображение сцены на экране
     * @param frame кадр, на котором будет происходить отображение
     */
    void display(MainFrame frame);

    /**
     * обработчик нажатия клавиш на конкретной сцене
     * @param key нажатая клавиша
     * @return сцена, в зависимости от нажатой клавиши
     */
    Screen respond(KeyEvent key);
}
