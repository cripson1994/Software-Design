package screens;

import javafx.stage.Screen;
import start.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class LoseScreen extends JPanel implements screens.Screen {
    JLabel jlabel = new JLabel();

    public LoseScreen() {
        jlabel.setText("-- press [enter] to start a new game--");
        jlabel.setFont(new Font("Chalkduster", 0, 20));
        jlabel.setForeground(Color.WHITE);
        jlabel.setLocation(235, 650);
        jlabel.setSize(450, 100);
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
    public screens.Screen respond(KeyEvent key) {
        return key.getExtendedKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
    }
}
