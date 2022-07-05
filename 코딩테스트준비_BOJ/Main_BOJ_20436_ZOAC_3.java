import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BOJ_20436_ZOAC_3 {
    static Map<String, int[]> conKey, vowelKey;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        conKey = new HashMap<>();
        vowelKey = new HashMap<>();
        makeKeyBoard();

        String startLeft = stringTokenizer.nextToken();
        String startRight = stringTokenizer.nextToken();
        String str = br.readLine();

        int ans = calTime(startLeft, startRight, str);

        System.out.println(ans);
    }

    private static int calTime(String startLeft, String startRight, String str) {
        int time = 0;
        for (char c : str.toCharArray()) {
            int[] s, d;
            if (conKey.containsKey(c + "")) {
                s = conKey.get(startLeft);
                d = conKey.get(c + "");
                startLeft = c + "";
            } else {
                s = vowelKey.get(startRight);
                d = vowelKey.get(c + "");
                startRight = c + "";
            }

            time += Math.abs(s[0] - d[0]) + Math.abs(s[1] - d[1]);
            time += 1;
        }
        return time;
    }

    private static void makeKeyBoard() {
        conKey.put("q", new int[]{0, 0});
        conKey.put("w", new int[]{0, 1});
        conKey.put("e", new int[]{0, 2});
        conKey.put("r", new int[]{0, 3});
        conKey.put("t", new int[]{0, 4});
        vowelKey.put("y", new int[]{0, 5});
        vowelKey.put("u", new int[]{0, 6});
        vowelKey.put("i", new int[]{0, 7});
        vowelKey.put("o", new int[]{0, 8});
        vowelKey.put("p", new int[]{0, 9});

        conKey.put("a", new int[]{1, 0});
        conKey.put("s", new int[]{1, 1});
        conKey.put("d", new int[]{1, 2});
        conKey.put("f", new int[]{1, 3});
        conKey.put("g", new int[]{1, 4});
        vowelKey.put("h", new int[]{1, 5});
        vowelKey.put("j", new int[]{1, 6});
        vowelKey.put("k", new int[]{1, 7});
        vowelKey.put("l", new int[]{1, 8});

        conKey.put("z", new int[]{2, 0});
        conKey.put("x", new int[]{2, 1});
        conKey.put("c", new int[]{2, 2});
        conKey.put("v", new int[]{2, 3});
        vowelKey.put("b", new int[]{2, 4});
        vowelKey.put("n", new int[]{2, 5});
        vowelKey.put("m", new int[]{2, 6});
    }
}
