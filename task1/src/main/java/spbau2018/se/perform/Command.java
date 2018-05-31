package spbau2018.se.perform;

import spbau2018.se.environment.commands.AbstactCommand;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Класс, реализующий интерфейс Thread
 */
public class Command extends Thread {

    /**
     * Входной поток команды
     */
    private final PipedInputStream input;
    /**
     * Выходной поток команды
     */
    private final PipedOutputStream output;
    /**
     * Полиморфный тип команды
     */
    private final PipedOutputStream errOutput;

    private AbstactCommand cmd;

    public Command(AbstactCommand cmd, PipedOutputStream output, PipedInputStream input, PipedOutputStream errOutput) {
        this.input = input;
        this.output = output;
        this.errOutput = errOutput;
        this.cmd = cmd;
    }

    /**
     * Запускает выполнение команды
     */
    @Override
    public void run() {
        try {
            cmd.eval(output, input, errOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}