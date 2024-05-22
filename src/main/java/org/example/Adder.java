package org.example;

public class Adder {

    public static void main(String[] args) {
        int A = 5;
        int B = 9;
        int m = 3;

        int sum = add(A, B);
        System.out.println("Sum: " + sum);
        System.out.println("Sum binary: " + toBinary(sum));

        int remainderAB = calculateModulo(sum, m);
        System.out.println("(A + B) mod m: " + remainderAB);
        System.out.println("(A + B) mod m binary: " + toBinary(remainderAB));

        int C_A = calculateModulo(A, m);
        System.out.println("C(A): " + C_A);
        System.out.println("C(A) binary: " + toBinary(C_A));

        int C_B = calculateModulo(B, m);
        System.out.println("C(B): " + C_B);
        System.out.println("C(B) binary: " + toBinary(C_B));

        int CACBSum = add(C_A, C_B);
        System.out.println("C(A) + C(B): " + CACBSum);
        System.out.println("C(A) + C(B) binary: " + toBinary(CACBSum));

        int remainderCACB = calculateModulo(CACBSum, m);
        System.out.println("C(A) + C(B) mod m: " + remainderCACB);
        System.out.println("C(A) + C(B) mod m binary: " + toBinary(remainderCACB));


        boolean isError = checkError(toBinary(remainderAB), toBinary(remainderCACB));

        System.out.println("Is error? : " + isError);
    }

    private static int add(int a, int b) {
        return a + b;
    }

    private static int calculateModulo(int value, int modulo) {
        return value % modulo;
    }

    //porownywanie bit po bicie (w gui np. zaswieca sie czerwona lampka jak jest blad)
    private static boolean checkError(String r1, String r2) {
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

    private static String toBinary(int n){
        return Integer.toBinaryString(n);
    }
}