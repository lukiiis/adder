package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AdderMethods {

    AdderMethods(){};

    public static int add(int a, int b) {
        return a + b;
    }

    public static int calculateModulo(int value, int modulo) {
        return value % modulo;
    }

    public static boolean checkError(String r1, String r2) {
        if (r1.length() != r2.length()) {
            return true;
        }

        for (int i = 0; i < r1.length(); i++) {
            if (r1.charAt(i) != r2.charAt(i)) {
                return true;
            }
        }

        return false;
    }
    public static String toBinary(int n) {
        return Integer.toBinaryString(n);
    }

    public static void saveResultsToFile(String results) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt"))) {
            writer.write(results);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
