package 기초수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_6588_골드바흐의_추측 {
    static final int MAX = 1000001;
    static boolean[] isPrime;
    static int n;

    public Main_BOJ_6588_골드바흐의_추측() {
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        isPrime = new boolean[MAX];

        Eratos();

        while((n = Integer.parseInt(br.readLine())) != 0){
            boolean flag = true;
            for (int i = 3; i < MAX; i++) {
                if (isPrime[i] && isPrime[n - i]) {
                    System.out.println(n + " = " + i + " + " + (n - i));
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println("Goldbach's conjecture is wrong.");
            }
        }
    }

    private static void Eratos() {
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i < MAX; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i < MAX; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < MAX; j+=i) {
                    isPrime[j] = false;
                }
            }
        }
    }
}
