package 기초수학;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.Scanner;

public class Main_BOJ_2089_마이너스_2진수 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = scanner.nextInt();
        if (N == 0) {
            System.out.println(0);
            return;
        }

        while(N != 0){
            if (N % -2 == 0) {
                sb.append("0");
                N /= -2;
            } else {
                sb.append("1");
                N--;
                N /= -2;
            }
        }
        System.out.println(sb.reverse());
    }
}
