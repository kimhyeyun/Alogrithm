import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_1082_방_번호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        int min = 50;
        int index = -1;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());

            if (min >= numbers[i]) {
                min = numbers[i];
                index = i;
            }
        }

        int M = Integer.parseInt(br.readLine());
        char[] digits = new char[51];
        int count = 0;

        while (M >= min) {
            digits[count++] = (char) (index + '0');
            M -= min;
        }

        int start = 0;
        for (int i = 0; i < count; i++) {
            for (int j = N - 1; j >= 0; j--) {
                if (numbers[j] <= M + min) {
                    digits[i] = (char) (j + '0');
                    M += min - numbers[j];
                    break;
                }
            }

            if (digits[start] == '0') {
                start += 1;
                M += min;
            }
        }

        if (start == count) {
            System.out.println(0);
            return;
        }

        String answer = "";
        for (int i = start; i < count; i++) {
            answer += digits[i];
        }

        System.out.println(answer);

    }

}
