package IO;

import java.util.Scanner;

public class Main_BOJ_2522_별찍기_12{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        for(int i = 1; i <= N ;i++){
            for(int j = N ; j > i ; j--)
                System.out.print(" ");
            for(int k = 1; k <= i; k++)
                System.out.print("*");
            System.out.println();
        }
        for(int i = 1; i < N ; i++){
            for(int j = 1; j <= i; j++)
                System.out.print(" ");
            for(int k = N; k > i ; k--)
                System.out.print("*");
            System.out.println();
        }
    }
}
