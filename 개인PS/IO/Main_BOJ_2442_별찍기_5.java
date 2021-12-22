package IO;

import java.util.Scanner;

public class Main_BOJ_2442_별찍기_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        for(int i = 0 ; i < N ; i++){
            for(int j = N-1 ; j > i ; j--)
                System.out.print(" ");
            for(int k = 0; k < 2*i+1 ; k++)
                System.out.print("*");
            System.out.println();
        }

    }
}
