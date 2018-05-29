package screens;

import start.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class StartScreen extends JPanel implements Screen {

    JLabel jlabel = new JLabel();

    public StartScreen() {
        jlabel.setText("-- press [enter] to start --");
        jlabel.setFont(new Font("Chalkduster", 0, 20));
        jlabel.setForeground(Color.WHITE);
        jlabel.setLocation(235, 650);
        jlabel.setSize(350, 100);
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
        ImageIcon im = new ImageIcon("sprites/logo.png");
        g.drawImage(im.getImage(), 100, 150, null);
    }

    @Override
    public Screen respond(KeyEvent key) {
        return key.getExtendedKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
    }
}
