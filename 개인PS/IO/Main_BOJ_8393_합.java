package IO;

import java.util.Scanner;

public class Main_BOJ_8393_합 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int answer = (n * (n+1)) / 2;

        System.out.println(answer);
    }
}
