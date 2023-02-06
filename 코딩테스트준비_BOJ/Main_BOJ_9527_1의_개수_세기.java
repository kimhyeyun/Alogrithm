import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_9527_1의_개수_세기 {
    static long[] bit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        long start = Long.parseLong(stringTokenizer.nextToken());
        long end = Long.parseLong(stringTokenizer.nextToken());

        initBitCount();

        System.out.println(getBitCount(end) - getBitCount(start - 1));
    }

    private static long getBitCount(long x) {
        long ans = x & 1;

        for (int i = 54; i > 0; i--) {
            if ((x & (1L << i)) > 0L) {
                ans += bit[i - 1] + (x - (1L << i) + 1);
                x -= (1L << i);
            }
        }
        return ans;
    }

    private static void initBitCount() {
        bit = new long[55];
        bit[0] = 1;
        for (int i = 1; i < 55; i++) {
            bit[i] = 2 * bit[i - 1] + (1L << i);
        }
    }
}
