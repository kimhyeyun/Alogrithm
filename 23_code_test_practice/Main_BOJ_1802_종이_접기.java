import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.WeakHashMap;

public class Main_BOJ_1802_종이_접기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String s = br.readLine();

            if (s.length() == 1) {
                sb.append("YES").append("\n");
                continue;
            }

            int len = s.length();
            int index = len / 2;
            boolean isStop = false;

            while (index != 0) {
                for (int i = 0, j = len - 1; i < index; i++, j--) {
                    if (s.charAt(i) != s.charAt(j)) continue;

                    isStop = true;
                    break;
                }

                if (isStop) break;

                len /= 2;
                index /= 2;
            }

            if (isStop) sb.append("NO").append("\n");
            else sb.append("YES").append("\n");
        }
        System.out.println(sb);
    }
}
