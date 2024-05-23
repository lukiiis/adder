package org.example;

public class AdderMethods {

    AdderMethods(){

    };
    static int add(int a, int b) {
        return a + b;
    }

    static int calculateModulo(int value, int modulo) {
        return value % modulo;
    }

    static boolean checkError(String r1, String r2) {
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

    static String toBinary(int n) {
        return Integer.toBinaryString(n);
    }
}
