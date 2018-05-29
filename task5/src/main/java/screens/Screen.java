package screens;

import start.MainFrame;

import java.awt.event.KeyEvent;

import javax.swing.*;

public interface Screen {
    void display(MainFrame frame);

    Screen respond(KeyEvent key);
}
