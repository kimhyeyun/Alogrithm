import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_1114_통나무_자르기 {

    static int L, K, C;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[K + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        arr[K] = L;
        Arrays.sort(arr);

        int[] answer = binarySearch();

        System.out.println(answer[0] + " " + answer[1]);
    }

    private static int[] binarySearch() {
        int[] tmp = new int[2];

        int left = 1, right = L;
        while (left <= right) {
            int mid = (left + right) / 2;

            int result = check(mid);
            if (result != -1) {
                tmp[0] = mid;
                tmp[1] = result;
                right = mid - 1;
            } else left = mid + 1;
        }

        return tmp;
    }

    private static int check(int len) {
        int left = K, right = K;
        int count = 0;

        while (true) {
            left -= 1;

            if (arr[right] - arr[left] > len) {
                if(left + 1 == right) return -1;

                right = left + 1;
                left = left + 1;
                count += 1;
            }

            if (left == 0) break;
            if(count == C) break;
        }

        if(arr[right] - arr[left] > len) return -1;
        if(arr[left] > len) return -1;

        return arr[left];
    }
}
