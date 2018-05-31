package spbau2018.se.environment.commands;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс-обёртка для комманды wc
 */
public class Wc extends AbstactCommand {

    public Wc(ArrayList<String> file) {
        this.args = file;
    }

    public Wc() {
        this.args = null;
    }

    @Override
    public void eval(PipedOutputStream output, PipedInputStream input, PipedOutputStream errOutput) throws IOException {
        fork(output, input, errOutput);
    }

    @Override
    protected void fromStream(PipedOutputStream output, PipedInputStream input, PipedOutputStream errOutput) throws IOException {
        int data = 0;
        data = input.read();
        StringBuilder buf = new StringBuilder();
        while (data != -1) {
            buf.append((char) data);
            data = input.read();
        }
        input.close();
        int wordsCount = 0;
        int linesCount = 0;
        int bytesCount = 0;
        String str = buf.toString();
        for (String line : str.split("\n")) {
            if (!line.isEmpty()) {
                wordsCount += line.split(" ").length;
            }
            linesCount++;
            bytesCount += line.getBytes("UTF8").length;
        }
        int ind = str.length() - 2;
        while (ind >= 0 && str.charAt(ind) == '\n') {
            linesCount++;
            ind--;
        }
        bytesCount += linesCount;

        output.write((createString(wordsCount, linesCount, bytesCount) + "\n").getBytes());
        output.flush();
    }

    @Override
    protected void fromFile(PipedOutputStream output, PipedOutputStream errOutput) throws IOException {
        int totalWords = 0;
        int totalLines = 0;
        int totalBytes = 0;
        int wordsCount = 0;
        int linesCount = 0;
        int bytesCount = 0;
        for (String arg : args) {
            wordsCount = 0;
            linesCount = 0;
            bytesCount = 0;
            List<String> lines = null;
            try {
                lines = Files.readAllLines(Paths.get(arg), StandardCharsets.UTF_8);
                for (String line : lines) {
                    if (!line.isEmpty()) {
                        wordsCount += line.split(" ").length;
                    }
                    linesCount++;
                    bytesCount += line.getBytes("UTF8").length;
                }
                bytesCount += linesCount;
                output.write((createString(wordsCount, linesCount, bytesCount) + arg + "\n").getBytes());
                output.flush();
            } catch (IOException e) {
                errOutput.write(("wc: " + e.getMessage() + ": No such file or directory\n").getBytes());
                errOutput.flush();
            }
            totalWords += wordsCount;
            totalLines += linesCount;
            totalBytes += bytesCount;
        }
        if (args.size() > 1) {
            output.write((createString(totalWords, totalLines, totalBytes) + "total\n").getBytes());
            output.flush();
        }
    }

    /**
     * Метод для записи строки в формате wc из bash
     *
     * @param w колличество слов
     * @param l колличество строк
     * @param b колличество байт
     * @return готовая строка
     */
    private String createString(int w, int l, int b) {
        StringBuilder buf = new StringBuilder();
        buf.append("\t\t");
        buf.append(Integer.toString(l));
        buf.append("  ");
        buf.append(Integer.toString(w));
        buf.append("  ");
        buf.append(Integer.toString(b));
        buf.append("  ");
        return buf.toString();
    }
}
