import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_13144_List_of_Unique_Numbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] count = new int[100001];

        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        long answer = 0;
        int start = 0, end = -1;
        while (start < N) {
            while (end + 1 < N && count[arr[end + 1]] == 0) {
                end += 1;
                count[arr[end]] += 1;
            }

            answer += (end - start + 1);

            count[arr[start]] -= 1;
            start += 1;
        }
        System.out.println(answer);
    }
}
