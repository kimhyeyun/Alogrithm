package IO;

import java.util.Scanner;

public class Main_BOJ_2445_별찍기_8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        for(int i = 1 ; i <= N; i++){
            for(int j = 1; j <= i ;j++){
                System.out.print("*");
            }
            for(int k = 1 ; k <= 2*(N-i) ; k++){
                System.out.print(" ");
            }
            for(int l = 1; l <= i; l++){
                System.out.print("*");
            }
            System.out.println();
        }

        for(int i = 1; i < N ; i++){
            for(int j = N-1 ; j >= i; j--)
                System.out.print("*");
            for(int k = 0 ; k < 2 * i ; k++)
                System.out.print(" ");
            for(int l = N-1; l >= i; l--)
                System.out.print("*");
            System.out.println();
        }
    }
}
