import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_2885_초콜릿_식사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());
        int N = 0, size = (int) Math.pow(2, N);

        while (size < K) {
            N += 1;
            size = (int) Math.pow(2, N);
        }

        System.out.print(size + " ");

        int count = 0;
        while (K > 0) {
            if (K < size) {
                count += 1;
                size /= 2;
            } else K -= size;
        }

        System.out.println(count);

    }
}
