package spbau2018.se.environment.commands;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Класс-обёртка для команды pwd
 */
public class Pwd extends AbstactCommand {

    public Pwd() {
    }

    @Override
    public void eval(PipedOutputStream output, PipedInputStream input, PipedOutputStream errOutput) throws IOException {
        output.write((System.getProperty("user.dir") + "\n").getBytes());
        output.flush();
        output.close();
    }
}
