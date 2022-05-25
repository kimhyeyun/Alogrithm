import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_2749_피보나치_수_3 {
    static long N;
    static final int M = 1000000;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Long.parseLong(br.readLine());
        int P = 1500000;

        arr = new long[P];

        if(N == 0) System.out.println(0);
        else if (N == 1) {
            System.out.println(1);
        } else {
            arr[0] = 0;
            arr[1] = 1;
            for (int i = 2; i < P; i++) {
                arr[i] = (arr[i - 1] + arr[i - 2]) % M;
            }

            System.out.println(arr[(int) (N % P)]);
        }

    }
}
