package start;

import screens.Screen;
import screens.StartScreen;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class MainFrame extends JFrame {
    private Screen screen;

    public MainFrame() {
        super("roguelike");

        playMusic();

        setSize(980, 760);
        setBackground(Color.BLACK);
        screen = new StartScreen();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e != null) {
                    screen = screen.respond(e);
                }
                repaint();
            }
        });
        repaint();
    }

    public void addAndRevalidate(Component comp) {
        add(comp);
        revalidate();
    }

    public void repaint() {
        screen.display(this);
        super.repaint();
    }

    public static void main(String[] args) {
        MainFrame app = new MainFrame();
        app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        app.setVisible(true);
    }

    public static void playMusic() {
        InputStream music;
        try {
            music = new FileInputStream(new File("/Users/vladislavkalinin/IdeaProjects/Software-Design/task5/1.wav"));
            AudioStream audios = new AudioStream(music);
            AudioPlayer.player.start(audios);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
