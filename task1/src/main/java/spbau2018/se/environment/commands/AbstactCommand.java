package spbau2018.se.environment.commands;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;


/**
 * Общий интерфейс для классов-обёрток команд
 */
public abstract class AbstactCommand {

    /**
     * Список аргументов команды
     */
    protected ArrayList<String> args;

    /**
     * Метод запуска команды
     *
     * @param output    Входной поток
     * @param input     Выходной поток
     * @param errOutput Поток для накапливания ошибок
     */
    public abstract void eval(PipedOutputStream output, PipedInputStream input, PipedOutputStream errOutput) throws IOException;


    /**
     * Вспомогательный метод для выбора стратегии выполнения команды в случаях если считывание должно проходить из файла
     * или с входного потока
     *
     * @param output    Входной поток
     * @param input     Выходной поток
     * @param errOutput Поток для накапливания ошибок
     */
    protected void fork(PipedOutputStream output, PipedInputStream input, PipedOutputStream errOutput) throws IOException {
        if (this.args != null) {
            fromFile(output, errOutput);
        } else {
            fromStream(output, input, errOutput);
        }
        output.close();
    }

    /**
     * Стратегия выполнения команды в случае, если считывание должно проходить с входного потока
     *
     * @param output    Выходной поток
     * @param input     Входной поток
     * @param errOutput Поток для накапливания ошибок
     */
    protected void fromStream(PipedOutputStream output, PipedInputStream input, PipedOutputStream errOutput) throws IOException {
    }

    /**
     * Стратегия выполнения команды в случае, если считывание должно проходить из файла
     *
     * @param output    Выходной поток
     * @param errOutput Поток для накапливания ошибок
     */
    protected void fromFile(PipedOutputStream output, PipedOutputStream errOutput) throws IOException {
    }

}
