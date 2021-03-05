package org.icecream;

import org.icecream.exceptions.IceCreamException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.icecream.IceCream.ic;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IceCreamTest {

    @Test
    @Order(1)
    public void simplePrintTest() {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        ic();
        // 🍦 org.icecream.IceCreamTest > simplePrintTest:L23
        String result = baos.toString().trim();
        assertTrue(result.contains("\uD83C\uDF66 org.icecream.IceCreamTest > simplePrintTest:L"));
    }

    @Test
    @Order(2)
    public void withParamsTest() {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        ic(1, 2, 3);
        // "🍦 org.icecream.IceCreamTest > withParamsTest:L25 > param_0 = 1 | param_1 = 2 | param_2 = 3"
        String result = baos.toString().trim();
        assertTrue(result.contains("\uD83C\uDF66 org.icecream.IceCreamTest > withParamsTest:L"));
        assertTrue(result.contains(" > param_0 = 1 | param_1 = 2 | param_2 = 3"));
    }

    @Test
    @Order(3)
    public void withMethodAsParamTest() {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        ic(concat("1", "2"));
        // 🍦 org.icecream.IceCreamTest > withMethodAsParamTest:L33 > param_0 = 12
        String result = baos.toString().trim();
        assertTrue(result.contains("\uD83C\uDF66 org.icecream.IceCreamTest > withMethodAsParamTest:L"));
        assertTrue(result.contains(" > param_0 = 12"));
    }

    @Test
    @Order(4)
    public void includeFileNameTest() {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        IceCream.includeFilename(true);
        ic();
        IceCream.includeFilename(false);
        String result = baos.toString().trim();
        assertTrue(result.contains("\uD83C\uDF66 IceCreamTest.java > org.icecream.IceCreamTest > includeFileNameTest:L"));
    }

    @Test
    @Order(5)
    public void disableContextTest() {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        IceCream.includeClassName(false);
        IceCream.includeMethodNameAndLineNumber(false);
        IceCream.includeContext(false);
        IceCream.setPrefix("icc |");
        ic();
        assertEquals("icc |", baos.toString().trim());
    }

    @Test
    @Order(6)
    public void verifyThrowsTest() {
        IceCream.includeContext(false);
        assertThrows(IceCreamException.class, () -> IceCream.includeClassName(false));
        assertThrows(IceCreamException.class, () -> IceCream.includeFilename(false));
        assertThrows(IceCreamException.class, () -> IceCream.includeMethodNameAndLineNumber(false));
    }

    String concat(String a, String b) {
        return a + b;
    }

}
