package test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import code.exercise.SpiralPrint;

public class PrintSpiralTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private static Properties resultMapping = new Properties();

    @BeforeTest
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        try {
            resultMapping.load(new FileInputStream("spiral-tests.properties"));
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }
    }

    @AfterTest
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    @Parameters("myNum")
    public void f(String myNum) {
        String result = "";
        String expected = ((String) resultMapping.get(myNum)).trim();
        SpiralPrint spiral = new SpiralPrint();
        spiral.spiralPrint(Integer.valueOf(myNum));
        result = replaceCtrlChars(outContent.toString());
        Assert.assertEquals(result, expected);
    }

    private String replaceCtrlChars(String input) {
        String output = input.trim();
        output = output.replaceAll(" ", "");
        output = output.replaceAll("\\t", " ");
        output = output.replaceAll("\\n", "#");
        output = output.replaceAll("\\r", "#");
        output = output.replaceAll(" *## *", "##");
        return output.trim();
    }
}
