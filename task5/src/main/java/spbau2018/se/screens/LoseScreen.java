package spbau2018.se.screens;

import spbau2018.se.start.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Класс, реализующий сцену проигрыша
 */
public class LoseScreen extends JPanel implements spbau2018.se.screens.Screen {
    /**
     * Компонента, для отрисовки надписи
     */
    private JLabel jlabel = new JLabel();

    public LoseScreen() {
        jlabel.setText("-- press [enter] to start a new game--");
        jlabel.setFont(new Font("Chalkduster", 0, 20));
        jlabel.setForeground(Color.WHITE);
        jlabel.setLocation(235, 650);
        jlabel.setSize(450, 100);
        log.info("game over");
    }

    @Override
    public void display(MainFrame frame) {
        frame.getContentPane().removeAll();
        frame.add(jlabel);
        frame.add(this);
        frame.validate();
    }

    @Override
    public void paintComponent(Graphics g) {
        ImageIcon im = new ImageIcon("sprites/lose.png");
        g.drawImage(im.getImage(), 100, 150, null);
    }

    @Override
    public spbau2018.se.screens.Screen respond(KeyEvent key) {
        return key.getExtendedKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
    }
}
