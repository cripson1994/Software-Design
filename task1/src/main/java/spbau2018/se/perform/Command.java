package spbau2018.se.perform;

import spbau2018.se.environment.commands.CommandInterface;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Класс, реализующий интерфейс Thread, позволяющий выполнять команды в различных потоках
 */
public class Command extends Thread{

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

    private CommandInterface cmd;

    public Command(CommandInterface cmd, PipedOutputStream output, PipedInputStream input, PipedOutputStream errOutput) {
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
        cmd.eval(output,input, errOutput);
    }

}