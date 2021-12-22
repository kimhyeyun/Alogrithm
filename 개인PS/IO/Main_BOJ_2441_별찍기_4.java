package IO;

import java.util.Scanner;

public class Main_BOJ_2441_별찍기_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        for(int i = N ; i > 0; i--){
            for(int k = N ; k > i ; k--){
                System.out.print(" ");
            }
            for(int j = 0 ; j < i ; j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
