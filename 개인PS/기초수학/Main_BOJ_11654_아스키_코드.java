package 기초수학;

import java.util.Scanner;

public class Main_BOJ_11654_아스키_코드 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();
        int ascii = (int) str.charAt(0);
        System.out.println(ascii);

    }
}
