package Commands;

import spbau2018.se.exceptions.BadQuotesException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import spbau2018.se.parse.Lexer;
import spbau2018.se.perform.CommandManager;
import spbau2018.se.preparation.Preparater;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public abstract class TestCommand {
    public int mills = 600;
    public Lexer lex = new Lexer();
    public Preparater p = new Preparater();
    public CommandManager m = new CommandManager();
    public Thread t;

    public final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    public final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    public void runManeger(String input) throws BadQuotesException, IOException {
        lex.setString(input);
        lex.parse();
        p.setBigTokens(lex.getBigTokens());
        p.prepair();
        m.setChain(p.getLst());
        m.manage();
    }
}
