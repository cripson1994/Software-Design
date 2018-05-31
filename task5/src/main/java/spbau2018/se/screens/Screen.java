package spbau2018.se.screens;

import spbau2018.se.start.MainFrame;

import java.awt.event.KeyEvent;

public interface Screen {
    void display(MainFrame frame);

    Screen respond(KeyEvent key);
}
