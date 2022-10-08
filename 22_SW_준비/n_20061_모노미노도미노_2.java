import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n_20061_모노미노도미노_2 {
    static int N, answer;
    static boolean[][] blue, green;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        answer = 0;

        blue = new boolean[4][6];
        green = new boolean[6][4];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

//            System.out.println(t + " " + x + " " + y);
            setBlockInBlue(t, x, y);
//            printBlue();
            setBlockInGreen(t, x, y);
//            printGreen();

            checkBlue();
//            printBlue();
            checkGreen();
//            printGreen();

            treatLightBlockInBlue();
//            printBlue();
            treatLightBlockInGreen();
//            printGreen();
        }

        System.out.println(answer);
        System.out.println(countOfBlock());
    }

    private static void printGreen() {
        System.out.println("----------green--------------");
        for (boolean[] bl : green) {
            for (boolean b : bl) {
                System.out.print(b+ " ");
            }
            System.out.println();
        }
    }

    private static void printBlue() {
        System.out.println("----------blue--------------");
        for (boolean[] bl : blue) {
            for (boolean b : bl) {
                System.out.print(b+ " ");
            }
            System.out.println();
        }
    }

    private static int countOfBlock() {
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if(blue[i][j]) sum += 1;
            }
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if(green[i][j]) sum += 1;
            }
        }
        return sum;
    }

    private static void treatLightBlockInGreen() {
        int cnt = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (green[i][j]) {
                    cnt += 1;
                    break;
                }
             }
        }

        for (int i = 5; i >= 2; i--) {
            for (int j = 0; j < 4; j++) {
                green[i][j] = green[i - cnt][j];
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                green[i][j] = false;
            }
        }
    }

    private static void treatLightBlockInBlue() {
        int cnt = 0;
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                if (blue[i][j]) {
                    cnt += 1;
                    break;
                }
            }
        }

        for (int j = 5; j >= 2; j--) {
            for (int i = 0; i < 4; i++) {
                blue[i][j] = blue[i][j - cnt];
            }
        }

        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                blue[i][j] = false;
            }
        }
    }

    private static void checkGreen() {
        int score = 0;
        while (true) {
            boolean flag = true;
            for (int i = 5; i > 1; i--) {
                int cnt = 0;
                for (int j = 0; j < 4; j++) {
                    if(green[i][j]) cnt += 1;
                    else break;
                }

                if (cnt == 4) {
                    flag = false;
                    score += 1;
                    for (int j = 0; j < 4; j++) {
                        green[i][j] = false;
                    }
                    for (int k = i - 1; k >= 0; k--) {
                        for (int j = 0; j < 4; j++) {
                            green[k + 1][j] = green[k][j];
                        }
                    }

                    for (int j = 0; j < 4; j++) {
                        green[0][j] = false;
                    }
                    break;
                }
            }
            if(flag) break;
        }
        answer += score;
    }

    private static void checkBlue() {
        int score = 0;
        while (true) {
            boolean flag = true;
            for (int j = 5; j > 1; j--) {
                int cnt = 0;
                for (int i = 0; i < 4; i++) {
                    if (blue[i][j]) cnt += 1;
                    else break;
                }
                if (cnt == 4) {
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

    private static void setBlockInGreen(int t, int x, int y) {
        if (t == 1) {
            for (int i = 0; i < 6; i++) {
                if (green[i][y]) {
                    green[i - 1][y] = true;
                    break;
                }
                if(i == 5) green[i][y] = true;
            }
        } else if (t == 2) {
            for (int i = 0; i < 6; i++) {
                if (green[i][y] || green[i][y + 1]) {
                    green[i - 1][y] = true;
                    green[i - 1][y + 1] = true;
                    break;
                }
                if (i == 5) {
                    green[i][y] = true;
                    green[i][y + 1] = true;
                }
            }
        } else {
            for (int i = 0; i < 6; i++) {
                if (green[i][y]) {
                    green[i - 1][y] = true;
                    green[i - 2][y] = true;
                    break;
                }
                if (i == 5) {
                    green[i][y] = true;
                    green[i - 1][y] = true;
                }
            }
        }
    }

    private static void setBlockInBlue(int t, int x, int y) {
        if (t == 1) {
            for (int j = 0; j < 6; j++) {
                if(blue[x][j]){
                    blue[x][j - 1] = true;
                    break;
                }
                if(j == 5) blue[x][j] = true;
            }
        } else if (t == 2) {
            for (int j = 0; j < 6; j++) {
                if (blue[x][j]) {
                    blue[x][j - 2] = true;
                    blue[x][j - 1] = true;
                    break;
                }
                if (j == 5) {
                    blue[x][j] = true;
                    blue[x][j - 1] = true;
                }
            }
        } else {
            for (int j = 0; j < 6; j++) {
                if (blue[x][j] || blue[x + 1][j]) {
                    blue[x][j - 1] = true;
                    blue[x + 1][j - 1] = true;
                    break;
                }
                if (j == 5) {
                    blue[x][j] = true;
                    blue[x + 1][j] = true;
                }
            }
        }
    }
}
