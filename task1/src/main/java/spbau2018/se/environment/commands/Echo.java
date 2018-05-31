package spbau2018.se.environment.commands;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;

/**
 * Класс-обёртка команды echo
 */
public class Echo extends AbstactCommand {

    public Echo(ArrayList<String> args) {
        this.args = args;
    }

    @Override
    public void eval(PipedOutputStream output, PipedInputStream input, PipedOutputStream errOutput) throws IOException {
        if (args.size() > 0) {
            output.write(args.get(0).getBytes());
            output.flush();

            for (int i = 1; i < args.size(); i++) {
                output.write((" " + args.get(i)).getBytes());
            }
            output.write('\n');
            output.flush();
            output.close();

        }
        output.close();
    }

}
