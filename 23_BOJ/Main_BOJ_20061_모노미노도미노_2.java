import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_20061_모노미노도미노_2 {
    static int N, answer;
    static boolean[][] blue, green;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        answer = 0;

        blue = new boolean[4][6];
        green = new boolean[6][4];

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            setBlockInGreen(t, y);
            setBlockInBlue(t, x);
//            printGreen();
//            printBlue();

            checkFullRowInGreen();
//            printGreen();
            checkFullColInBlue();

            checkLightRowInGreen();
            checkLightColInBlue();
        }

        System.out.println(answer);
        System.out.println(sumOfBlock());
    }

    private static int sumOfBlock() {
        int count = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if(green[i][j]) count += 1;
                if(blue[j][i]) count += 1;
            }
        }
        return count;
    }

    private static void checkLightColInBlue() {
        int count = 0;
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                if (blue[i][j]) {
                    count += 1;
                    break;
                }
            }
        }

        if(count == 0) return;

        for (int j = 5; j >= 2; j--) {
            for (int i = 0; i < 4; i++) {
                blue[i][j] = blue[i][j - count];
            }
        }

        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                blue[i][j] = false;
            }
        }
    }

    private static void checkLightRowInGreen() {
        int count = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if(green[i][j]){
                    count += 1;
                    break;
                }
            }
        }

        if(count == 0) return;

        for (int i = 5; i >= 2; i--) {
            System.arraycopy(green[i - count], 0, green[i], 0, 4);
        }

        for (int i = 0; i < 2; i++) {
            Arrays.fill(green[i], false);
        }
    }

    private static void checkFullColInBlue() {
        int score = 0;
        while (true) {
            boolean flag = true;

            for (int j = 0; j < 6; j++) {
                int count = 0;

                for (int i = 0; i < 4; i++) {
                    if(blue[i][j]) count += 1;
                    else break;
                }

                if (count == 4) {
                    flag = false;
                    score += 1;

                    for (int i = 0; i < 4; i++) {
                        blue[i][j] = false;
                    }

                    for (int k = j - 1; k >= 0; k--) {
                        for (int i = 0; i < 4; i++) {
                            blue[i][k + 1] = blue[i][k];
                        }
                    }

                    for (int i = 0; i < 4; i++) {
                        blue[i][0] = false;
                    }

                    break;
                }
            }
            if(flag) break;
        }
        answer += score;
    }

    private static void checkFullRowInGreen() {
        int score = 0;
        while (true) {
            boolean flag = true;

            for (int i = 5; i > 1; i--) {
                int count = 0;
                for (int j = 0; j < 4; j++) {
                    if(green[i][j]) count += 1;
                    else break;
                }
                if (count == 4) {
                    flag = false;
                    score += 1;

                    Arrays.fill(green[i], false);

                    for (int k = i - 1; k >= 0; k--) {
                        System.arraycopy(green[k], 0, green[k + 1], 0, 4);
                    }

                    Arrays.fill(green[0], false);
                    break;
                }
            }
            if(flag) break;
        }
        answer += score;
    }

    private static void printBlue() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(blue[i][j] ? 1 + " " : 0 + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void setBlockInBlue(int type, int x) {
        int y = 0;
        if (type == 1) {
            for (int j = 0; j < 6; j++) {
                if(blue[x][j]) break;
                y = j;
            }
            blue[x][y] = true;
        } else if (type == 2) {
            for (int j = 0; j < 5; j++) {
                if(blue[x][j] || blue[x][j+1]) break;
                y = j;
            }
            blue[x][y] = blue[x][y + 1] = true;
        } else {
            for (int j = 0; j < 6; j++) {
                if (blue[x][j] || blue[x+1][j]) break;
                y = j;
            }
            blue[x][y] = blue[x + 1][y] = true;
        }
    }

    private static void printGreen() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(green[i][j] ? 1 + " " : 0 + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void setBlockInGreen(int type, int y) {
        int x = 0;
        if (type == 1) {
            for (int i = 0; i < 6; i++) {
                if(green[i][y]) break;
                x = i;
            }
            green[x][y] = true;
        } else if (type == 2) {
            for (int i = 0; i < 6; i++) {
                if(green[i][y] || green[i][y+1]) break;
                x = i;
            }
            green[x][y] = green[x][y + 1] = true;
        } else {
            for (int i = 0; i < 5; i++) {
                if(green[i][y] || green[i+1][y]) break;
                x = i;
            }
            green[x][y] = green[x + 1][y] = true;
        }
    }
}
