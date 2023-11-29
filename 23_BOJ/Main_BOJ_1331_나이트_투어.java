import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1331_나이트_투어 {
    static final int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static final int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] commands = new String[37];
        for (int i = 0; i < 36; i++) {
            commands[i] = br.readLine();
        }
        commands[36] = commands[0];

        int index = 0;
        boolean[][] isVisited = new boolean[6][6];
        boolean checked = true;

        while (index < 36) {
            String cur = commands[index];
            String next = commands[index + 1];

            char curX = cur.charAt(0);
            char curY = cur.charAt(1);

            isVisited[curX - 'A'][curY - '0' - 1] = true;

            char nextX = next.charAt(0);
            char nextY = next.charAt(1);

            int diffX = Math.abs(nextX - curX);
            int diffY = Math.abs(nextY - curY);

            boolean isPossible = false;
            for (int d = 0; d < 8; d++) {
                if (dx[d] == diffX && dy[d] == diffY) {
                    isPossible = true;
                    break;
                }
            }

            if (!isPossible) {
                checked = false;
                System.out.println("Invalid");
                return;
            }

            if (isVisited[nextX - 'A'][nextY - '0' - 1]) {
                if (index != 35) {
                    checked = false;
                    System.out.println("Invalid");
                    return;
                }
            }

            index += 1;
        }

        if(checked) System.out.println("Valid");
    }
}
