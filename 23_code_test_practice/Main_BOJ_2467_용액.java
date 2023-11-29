import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BOJ_2467_용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Long[] waters = new Long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            waters[i] = Long.parseLong(st.nextToken());
        }

        int left = 0, right = N - 1;
        long min = Long.MAX_VALUE;
        long minLeft = 0, minRight = 0;

        while (left < right) {
            long sum = waters[left] + waters[right];

            if (min > Math.abs(sum)) {
                min = Math.abs(sum);
                minLeft = waters[left];
                minRight = waters[right];
            }

            if(sum >= 0) right -= 1;
            else left += 1;
        }

        System.out.println(minLeft + " " + minRight);

    }
}
