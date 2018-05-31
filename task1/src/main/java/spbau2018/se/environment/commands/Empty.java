package spbau2018.se.environment.commands;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Класс-обёртка вспомогательной пустой команды Empty
 * Просто игнорирует оба потока
 */
public class Empty extends AbstactCommand {

    public Empty() {
    }

    @Override
    public void eval(PipedOutputStream output, PipedInputStream input, PipedOutputStream errOutput) throws IOException {
        output.close();
    }

}
