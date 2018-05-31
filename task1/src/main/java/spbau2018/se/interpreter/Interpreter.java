package spbau2018.se.interpreter;

import spbau2018.se.exceptions.BadQuotesException;
import spbau2018.se.parse.Lexer;
import spbau2018.se.perform.ComandManager;
import spbau2018.se.prepair.Preparater;

import java.io.IOException;
import java.util.Scanner;

/**
 * Итоговый интерпретатор командыной строки
 */
public class Interpreter {

    /**
     * Запуск интерпретатора
     */
    public static void run() {
        Lexer l = new Lexer();
        Preparater p = new Preparater();
        ComandManager m = new ComandManager();
        Scanner in = new Scanner(System.in);;
        while (true) {
            String inStr = in.nextLine();
            l.setString(inStr);
            try {
                l.parse();
            } catch (BadQuotesException e) {
                finishLine(l);
            }
            p.setBigTokens(l.getBigTokens());
            p.prepair();
            m.setChain(p.getLst());
            try {
                m.manage();
            } catch (IOException e) {
            }
        }
    }

    /**
     * Перевычисляет строку, подаваемую в лексер до тех пор, пока не будет ошибки кавычек
     * @param l - лексер, с ошибкой кавычек
     */
    private static void finishLine(Lexer l) {
        Scanner in = new Scanner(System.in);
        System.out.print("> ");
        String inStr = in.nextLine();
        l.setString(l.getStr() + "\n" + inStr);
        try {
            l.parse();
        } catch (BadQuotesException e) {
            finishLine(l);
        }
    }


}
