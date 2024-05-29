package org.example;

import static org.example.AdderMethods.saveResultsToFile;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class AdderMethodsTest {

    @Test
    void testAdd() {
        assertEquals(14, AdderMethods.add(5, 9));
        assertEquals(0, AdderMethods.add(0, 0));
        assertEquals(-3, AdderMethods.add(-1, -2));
    }

    @Test
    void testCalculateModulo() {
        assertEquals(2, AdderMethods.calculateModulo(5, 3));
        assertEquals(1, AdderMethods.calculateModulo(10, 3));
        assertEquals(0, AdderMethods.calculateModulo(9, 3));
    }

    @Test
    void testCheckError() {
        assertFalse(AdderMethods.checkError("101", "101"));
        assertTrue(AdderMethods.checkError("101", "100"));
        assertTrue(AdderMethods.checkError("11", "10"));
    }

    @Test
    void testToBinary() {
        assertEquals("101", AdderMethods.toBinary(5));
        assertEquals("1001", AdderMethods.toBinary(9));
        assertEquals("0", AdderMethods.toBinary(0));
    }

    @Test
    public void testSaveResultsToFile() {
        String expectedResults = "Test results content";
        saveResultsToFile(expectedResults);

        StringBuilder actualResults = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("results.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                actualResults.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!actualResults.isEmpty()) {
            actualResults.setLength(actualResults.length() - 1);
        }

        assertEquals(expectedResults, actualResults.toString());
    }

}