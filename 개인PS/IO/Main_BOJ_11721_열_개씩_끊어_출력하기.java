package IO;

import java.util.Scanner;

public class Main_BOJ_11721_열_개씩_끊어_출력하기 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.next();

        for(int i = 0 ; i < str.length(); i++){
            if(i != 0 && i % 10 == 0) System.out.println();

            System.out.print(str.charAt(i));
        }
    }
}
