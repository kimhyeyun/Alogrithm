import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_1114_통나무_자르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] positions = new int[K + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            positions[i] = Integer.parseInt(st.nextToken());
        }

        positions[K] = L;
        Arrays.sort(positions);

        System.out.println(binarySearch(L, K, C, positions));
    }

    private static String binarySearch(int l, int k, int c, int[] positions) {
        int len = -1, index = -1;
        int left = 1, right = l;

        while (left <= right) {
            int mid = (left + right) / 2;
            int result = check(mid, k, c, positions);

            if (result != -1) {
                len = mid;
                index = result;
                right = mid - 1;
            } else left = mid + 1;
        }

        return len + " " + index;
    }

    private static int check(int len, int k, int c, int[] positions) {
        int left = k, right = k;
        int count = 0;

        while (true) {
            left -= 1;
            if (positions[right] - positions[left] > len) {
                if (left + 1 == right) return -1;
                right = left + 1;
                left = left + 1;
                count += 1;
            }

            if (left == 0 || count == c) break;
        }
        if (positions[right] - positions[left] > len) return -1;
        if (positions[left] > len) return -1;
        return positions[left];
    }
}
