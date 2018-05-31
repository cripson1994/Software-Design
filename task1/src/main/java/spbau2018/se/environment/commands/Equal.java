package spbau2018.se.environment.commands;

import spbau2018.se.environment.Environment;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Класс-обёртка команды присваивания
 */
public class Equal extends AbstactCommand {
    /**
     * Имя переменной
     */
    private String variable;
    /**
     * Присваиваемое значение
     */
    private String value;
    /**
     * Флаг, отвечающий за то, должно ли проходить присваивания
     * Нужен для имитации присваивания в bash
     */
    private boolean shouldPass;


    public Equal(String variable, String value, boolean shouldPass) {
        this.variable = variable;
        this.value = value;
        this.shouldPass = shouldPass;
    }


    @Override
    public void eval(PipedOutputStream output, PipedInputStream input, PipedOutputStream errOutput) throws IOException {
        if (shouldPass) {
            Environment.set(variable, value);
        }
        output.close();
    }
}
