import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_20061_모노미노도미노_2_re {
    static final int BLOCK = 1, EMPTY = 0;
    static int N, x, y, t, score;
    static int[][] green, blue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());

        green = new int[6][4];
        blue = new int[4][6];

        score = 0;

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            t = Integer.parseInt(stringTokenizer.nextToken());
            x = Integer.parseInt(stringTokenizer.nextToken());
            y = Integer.parseInt(stringTokenizer.nextToken());

            moveToGreen(t, x, y);
            moveToBlue(t, x, y);

            removeGreenRow();
            removeBlueCol();

            lightGreen();
            lightBlue();
        }

        System.out.println(score);
        System.out.println(sumOfBlocks());
    }

    private static int sumOfBlocks() {
        int green = cntOfGreen();
        int blue = cntOfBlue();

        return green + blue;
    }

    private static int cntOfBlue() {
        int cnt = 0;
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 4; i++) {
                if(blue[i][j] == BLOCK) cnt += 1;
            }
        }

        return cnt;
    }

    private static int cntOfGreen() {
        int cnt = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if(green[i][j] == BLOCK) cnt += 1;
            }
        }
        return cnt;
    }

    private static void lightBlue() {
        int cntOfBlockOfLight = 0;
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                if (blue[i][j] == BLOCK) {
                    cntOfBlockOfLight += 1;
                    break;
                }
            }
        }

        for (int k = 5; k >= 2; k--) {
            for (int i = 0; i < 4; i++) {
                blue[i][k] = blue[i][k - cntOfBlockOfLight];
            }
        }

        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                blue[i][j] = EMPTY;
            }
        }
    }

    private static void lightGreen() {
        int cntOfBlockOfLight = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (green[i][j] == BLOCK) {
                    cntOfBlockOfLight += 1;
                    break;
                }
            }
        }

        for (int k = 5; k >= 2; k--) {
            for (int j = 0; j < 4; j++) {
                green[k][j] = green[k - cntOfBlockOfLight][j];
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                green[i][j] = EMPTY;
            }
        }
    }

    private static void removeBlueCol() {
        while (true) {
            boolean flag = true;
            for (int j = 0; j < 6; j++) {
                int cntOfBlock = 0;
                for (int i = 0; i < 4; i++) {
                    if(blue[i][j] == BLOCK) cntOfBlock += 1;
                    else break;
                }

                if (cntOfBlock == 4) {
                    flag = false;
                    score += 1;
                    for (int i = 0; i < 4; i++) blue[i][j] = EMPTY;
                    for (int k = j - 1; k >= 0; k--) {
                        for (int i = 0; i < 4; i++) {
                            blue[i][k + 1] = blue[i][k];
                        }
                    }

                    for (int i = 0; i < 4; i++) blue[i][0] = EMPTY;
                    break;
                }
            }
            if(flag) break;
        }
    }

    private static void removeGreenRow() {
        while (true) {
            boolean flag = true;
            for (int i = 5; i > 1; i--) {
                int cntOfBlock = 0;
                for (int j = 0; j < 4; j++) {
                    if(green[i][j] == BLOCK) cntOfBlock += 1;
                    else break;
                }

                if (cntOfBlock == 4) {
                    flag = false;
                    score += 1;
                    for (int j = 0; j < 4; j++) green[i][j] = EMPTY;
                    for (int k = i - 1; k >= 0; k--) {
                        for (int j = 0; j < 4; j++) {
                            green[k + 1][j] = green[k][j];
                        }
                    }
                    for (int j = 0; j < 4; j++) {
                        green[0][j] = EMPTY;
                    }
                    break;
                }
            }
            if(flag) break;
        }
    }

    private static void moveToBlue(int t, int x, int y) {
        int row = x, col = 0;
        switch (t) {
            case 1:
                for (int j = 0; j < 6; j++) {
                    if(blue[row][j] == BLOCK) break;
                    col = j;
                }
                blue[row][col] = BLOCK;
                break;
            case 2:
                for (int j = 1; j < 6; j++) {
                    if(blue[row][j-1] == BLOCK || blue[row][j] == BLOCK) break;
                    col = j;
                }
                blue[row][col] = blue[row][col - 1] = BLOCK;
                break;
            case 3:
                for (int j = 0; j < 6; j++) {
                    if(blue[row][j] == BLOCK || blue[row+1][j] == BLOCK) break;
                    col = j;
                }
                blue[row][col] = blue[row + 1][col] = BLOCK;
                break;
        }
    }

    private static void moveToGreen(int t, int x, int y) {
        int col = y, row = 0;
        switch (t) {
            case 1:
                for (int i = 0; i < 6; i++) {
                    if(green[i][col] == BLOCK) break;
                    row = i;
                }
                green[row][col] = BLOCK;
                break;
            case 2:
                for (int i = 0; i < 6; i++) {
                    if(green[i][col] == BLOCK || green[i][col+1] == BLOCK) break;
                    row = i;
                }
                green[row][col] = green[row][col + 1] = BLOCK;
                break;
            case 3:
                for (int i = 1; i < 6; i++) {
                    if(green[i-1][col] == BLOCK || green[i][col] == BLOCK) break;
                    row = i;
                }
                green[row][col] = green[row - 1][col] = BLOCK;
                break;
        }
    }
}
