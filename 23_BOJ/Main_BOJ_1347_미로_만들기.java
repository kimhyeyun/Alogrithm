import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BOJ_1347_미로_만들기 {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static char[][] map;
    static int dir;
    static int leftX ,leftY ,rightX, rightY;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[] commands = br.readLine().toCharArray();

        dir = 0;

        map = new char[101][101];
        for (int i = 0; i < 101; i++) {
            Arrays.fill(map[i], '#');
        }


        leftX = 50;
        leftY = 50;
        rightX = 50;
        rightY = 50;

        map[50][50] = '.';
        int curX = 50;
        int curY = 50;

        for (char command : commands) {
            if (command != 'F') {
                rotate(command);
            } else {
                curX += dx[dir];
                curY += dy[dir];
                map[curX][curY] = '.';
            }
        }

        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                if (map[i][j] == '.') {
                    if(leftX > i) leftX = i;
                    if(leftY > j) leftY = j;
                    if(rightX < i) rightX = i;
                    if(rightY < j) rightY = j;
                }
            }
        }

        for (int i = leftX; i <= rightX; i++) {
            for (int j = leftY; j <= rightY; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    private static void rotate(char command) {
        if (command == 'R') {
            dir = (dir + 3) % 4;
        } else {
            dir = (dir + 1) % 4;
        }
    }
}
