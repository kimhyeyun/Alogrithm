import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_12018_Younsei_TOTO {

    public static void main(String[] args) throws IOException {
        System.out.println(solution());
    }

    private static int solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] points = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            if (b > a) {
                points[i] = 1;
                continue;
            }

            Integer[] arr = new Integer[a];
            for (int j = 0; j < a; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr, Collections.reverseOrder());

            if(arr[b - 1] == 36) points[i] = 36;
            else points[i] = arr[b - 1];
        }

        Arrays.sort(points);
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (m >= points[i]) {
                m -= points[i];
                count += 1;
            } else break;

        }

        return count;
    }

}
