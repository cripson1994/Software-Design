package spbau2018.se.screens;

import spbau2018.se.start.MainFrame;

import java.awt.event.KeyEvent;
import java.util.logging.Logger;

public interface Screen {
    Logger log = Logger.getLogger("GameLog");
    void display(MainFrame frame);

    Screen respond(KeyEvent key);
}
