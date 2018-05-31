package spbau2018.se.environment.commands;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Класс-обёртка для команды exit
 */
public class Exit extends AbstactCommand {
    /**
     * Флаг, отвечающий за то, нужно ли выходить из программы или нет
     * Выход происходит, если пользователь ввёл только команду exit
     */
    boolean needExit;

    public Exit(boolean needExit) {
        this.needExit = needExit;
    }


    @Override
    public void eval(PipedOutputStream output, PipedInputStream input, PipedOutputStream errOutput) throws IOException {
        if (needExit) {
            System.exit(0);
        }
        output.close();
    }
}
