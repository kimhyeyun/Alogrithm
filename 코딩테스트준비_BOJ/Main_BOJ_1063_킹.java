import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * 12/31
 * */
public class Main_BOJ_1063_킹 {
    static int[] king, rock;
    static Map<String, Integer> dir;
    static int[] dx = {0, 0, 1, -1, -1, -1, 1, 1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        String str = stringTokenizer.nextToken();
        king = setPos(str.toCharArray());

        str = stringTokenizer.nextToken();
        rock = setPos(str.toCharArray());

        dirInit();

        int N = Integer.parseInt(stringTokenizer.nextToken());

        while (N-- > 0) {
            str = br.readLine();
            int d = dir.get(str);

            int nx = king[0] + dx[d];
            int ny = king[1] + dy[d];

            if (nx < 0 || ny < 0 || 7 < nx || 7 < ny) {
                continue;
            }
            if (nx == rock[0] && ny == rock[1]) {
                int nrx = rock[0] +dx[d];
                int nry = rock[1] + dy[d];

                if (nrx < 0 || nry < 0 || 7 < nrx || 7 < nry) {
                    continue;
                }
                rock = new int[]{nrx, nry};
            }
            king = new int[]{nx, ny};
        }

        System.out.println(getPos(king));
        System.out.println(getPos(rock));
    }

    private static String getPos(int[] pos) {
        String result = String.valueOf((char) (pos[1] + 'A'));
        result += String.valueOf(8 - pos[0]);

        return result;
    }

    private static void dirInit() {
        dir = new HashMap<>();
        dir.put("R", 0);
        dir.put("L", 1);
        dir.put("B", 2);
        dir.put("T", 3);
        dir.put("RT", 4);
        dir.put("LT", 5);
        dir.put("RB", 6);
        dir.put("LB", 7);
    }

    private static int[] setPos(char[] pos) {
        int x = 8 - (pos[1] - '0');
        int y = (int) (pos[0] - 'A');

        return new int[]{x, y};
    }
}
