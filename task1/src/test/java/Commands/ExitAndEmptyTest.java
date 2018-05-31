package Commands;

import spbau2018.se.exceptions.BadQuotesException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitAndEmptyTest extends TestCommand{

    @DisplayName("test exit and empty")
    @Test
    void test() throws BadQuotesException, IOException, InterruptedException {

        runManeger("");
        t = m.getPrintThread();
        t.join(mills);
        assertEquals("", outContent.toString());

        runManeger("echo 123 | ");
        outContent.reset();
        t = m.getPrintThread();
        t.join(mills);
        assertEquals("", outContent.toString());

        runManeger("echo 123 | exit");
        outContent.reset();
        t = m.getPrintThread();
        t.join(mills);
        assertEquals("", outContent.toString());

    }

}
