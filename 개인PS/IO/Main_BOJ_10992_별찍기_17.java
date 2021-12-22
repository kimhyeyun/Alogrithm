package IO;

import java.util.Scanner;

public class Main_BOJ_10992_별찍기_17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        for (int i = 1; i <= N; i++) {
            for (int j = N ; j > i; j--)
                System.out.print(" ");
            if (i == 1 || i == N) {
                for (int k = 0; k < 2 * i - 1; k++) {
                    System.out.print("*");
                }
            } else {
                System.out.print("*");
                for (int l = 0; l < 2 * (i - 1) - 1; l++) {
                    System.out.print(" ");
                }
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
