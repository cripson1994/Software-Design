package spbau2018.se.start;

import spbau2018.se.screens.Screen;
import spbau2018.se.screens.StartScreen;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Стартовый класс, он же главный кадр, на котором будет происходить отрисовка
 */
public class MainFrame extends JFrame {
    private Screen screen;

    public MainFrame() {
        super("roguelike");

//        playMusic();

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

    /**
     * Перерисовка на кадре
     */
    public void repaint() {
        screen.display(this);
        super.repaint();
    }

    public static void main(String[] args) {
        Logger log = Logger.getLogger("GameLog");
        FileHandler fh;

        try {
            fh = new FileHandler("LogFile.log", true);
            log.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            log.info("start program");

        } catch (IOException e) {
            e.printStackTrace();
        }

        MainFrame app = new MainFrame();
        app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        app.setVisible(true);
    }

    /**
     * Включнение музыкального сопровождения
     */
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
