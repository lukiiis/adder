package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AdderTerminalTest {

    @Test
    void testAdd() {
        assertEquals(14, AdderTerminal.add(5, 9));
        assertEquals(0, AdderTerminal.add(0, 0));
        assertEquals(-3, AdderTerminal.add(-1, -2));
    }

    @Test
    void testCalculateModulo() {
        assertEquals(2, AdderTerminal.calculateModulo(5, 3));
        assertEquals(1, AdderTerminal.calculateModulo(10, 3));
        assertEquals(0, AdderTerminal.calculateModulo(9, 3));
    }

    @Test
    void testCheckError() {
        assertFalse(AdderTerminal.checkError("101", "101"));
        assertTrue(AdderTerminal.checkError("101", "100"));
        assertTrue(AdderTerminal.checkError("11", "10"));
    }

    @Test
    void testToBinary() {
        assertEquals("101", AdderTerminal.toBinary(5));
        assertEquals("1001", AdderTerminal.toBinary(9));
        assertEquals("0", AdderTerminal.toBinary(0));
    }

}