import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_20061_모노미노도미노_2 {

    static int N, t, x, y, score;
    static int[][] green, blue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        green = new int[6][4];
        blue = new int[4][6];

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());

            t = Integer.parseInt(stringTokenizer.nextToken());
            x = Integer.parseInt(stringTokenizer.nextToken());
            y = Integer.parseInt(stringTokenizer.nextToken());

//            System.out.println("i : " + i);

            moveToGreen(t, y);
            moveToBlue(t, x);

/*            System.out.println("MOVE");
            printGreen();
            System.out.println();
            printBlue();*/

            removeGreenRow();
            removeBlueCol();

/*            System.out.println("REMOVE");
            printGreen();
            System.out.println();
            printBlue();*/

            lightGreen();
            lightBlue();

/*            System.out.println("LIGHT");
            printGreen();
            System.out.println();
            printBlue();
            System.out.println();*/
        }

        int cntOfGreenBlock = countOfGreenBlock();
        int cntOfBlueBlock = countOfBlueBlock();

        System.out.println(score);
        System.out.println(cntOfBlueBlock + cntOfGreenBlock);
    }

    private static int countOfBlueBlock() {
        int cnt = 0;
        for (int[] bl : blue) {
            for (int b : bl) {
                if(b==1) cnt += 1;
            }
        }
        return cnt;
    }

    private static int countOfGreenBlock() {
        int cnt = 0;
        for (int[] gr : green) {
            for (int g : gr) {
                if(g == 1) cnt += 1;
            }
        }
        return cnt;
    }

    private static void lightBlue() {
        int cntOfBlockOfLight = 0;
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                if (blue[i][j] == 1) {
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
                blue[i][j] = 0;
            }
        }
    }

    private static void lightGreen() {
        int cntOfBlockOfLight = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (green[i][j] == 1) {
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
                green[i][j] = 0;
            }
        }
    }

    private static void removeBlueCol() {
        while (true) {
            boolean flag = true;
            for (int j = 0; j < 6; j++) {
                int cntOfBlock = 0;
                for (int i = 0; i < 4; i++) {
                    if (blue[i][j] == 1) cntOfBlock += 1;
                    else break;
                }

                if (cntOfBlock == 4) {
                    flag = false;
                    score += 1;
                    for (int i = 0; i < 4; i++) blue[i][j] = 0;
                    for (int k = j - 1; k >= 0; k--) {
                        for (int i = 0; i < 4; i++) {
                            blue[i][k + 1] = blue[i][k];
                        }
                    }
                    for (int i = 0; i < 4; i++) blue[i][0] = 0;
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
                    if (green[i][j] == 1) cntOfBlock += 1;
                    else break;
                }

                if (cntOfBlock == 4) {
                    flag = false;
                    score += 1;
                    for (int j = 0; j < 4; j++) green[i][j] = 0;
                    for (int k = i - 1; k >= 0; k--) {
                        for (int j = 0; j < 4; j++) {
                            green[k + 1][j] = green[k][j];
                        }
                    }
                    for (int j = 0; j < 4; j++) {
                        green[0][j] = 0;
                    }
                    break;
                }
            }
            if(flag) break;
        }
    }

    private static void printBlue() {
        for (int[] bl : blue) {
            for (int b : bl) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
    }

    private static void moveToBlue(int t, int x) {
        int col = 0;
        if (t == 1) {
            // 1x1
            for (int j = 0; j < 6; j++) {
                if(blue[x][j] != 0) break;
                col = j;
            }
            blue[x][col] = 1;
        } else if (t == 2) {
            // 1x2
            for (int j = 1; j < 6; j++) {
                if (blue[x][j - 1] != 0 || blue[x][j] != 0) break;
                col = j;
            }
            blue[x][col - 1] = 1;
            blue[x][col] = 1;
        } else {
            // 2x1
            for (int j = 0; j < 6; j++) {
                if(blue[x][j] != 0 || blue[x+1][j] != 0) break;
                col = j;
            }
            blue[x][col] = 1;
            blue[x + 1][col] = 1;
        }
    }

    private static void printGreen() {
        for (int[] gr : green) {
            for (int g : gr) {
                System.out.print(g + " ");
            }
            System.out.println();
        }
    }

    private static void moveToGreen(int t, int y) {
        int row = 0;
        if (t == 1) {
            // 1x1
            for (int i = 0; i < 6; i++) {
                if(green[i][y] != 0) break;

                row = i;
            }
            green[row][y] = 1;
        } else if (t == 2) {
            // 1x2
            for (int i = 0; i < 6; i++) {
                if (green[i][y] != 0 || green[i][y + 1] != 0) break;

                row = i;
            }
            green[row][y] = 1;
            green[row][y + 1] = 1;
        } else {
            // 2x1
            for (int i = 1; i < 6; i++) {
                if(green[i-1][y] != 0 || green[i][y] != 0) break;

                row = i;
            }
            green[row - 1][y] = 1;
            green[row][y] = 1;
        }
    }
}
