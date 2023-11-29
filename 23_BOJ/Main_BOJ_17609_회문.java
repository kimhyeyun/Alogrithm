import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_17609_회문 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            sb = new StringBuilder(s);
            String e = sb.reverse().toString();

            if(s.equals(e)){
                System.out.println(0);
                continue;
            }

            int r = check(s);
            System.out.println(r >= 2 ? r - 1 : r);

        }
    }

    private static int check(String s) {
        int left = 0, right = s.length() - 1;
        int count = 0;

        while (left <= right) {
            if (s.charAt(left) == s.charAt(right)) {
                left += 1;
                right -= 1;
            } else {
                count += 1;

                int l = left + 1;
                int r = right;

                while (l <= r) {
                    if (s.charAt(l) == s.charAt(r)) {
                        l += 1;
                        r -= 1;
                    } else {
                        count += 1;
                        break;
                    }
                }

                l = left;
                r = right - 1;

                while (l <= r) {
                    if (s.charAt(l) == s.charAt(r)) {
                        l += 1;
                        r -= 1;
                    } else {
                        count += 1;
                        break;
                    }
                }
                return count;
            }
        }
        return count;
    }
}
