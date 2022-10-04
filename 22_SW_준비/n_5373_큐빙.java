import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 미해겨류ㅠㅠㅠㅠ 50에서 계속 틀리뮤ㅠ
* */
public class n_5373_큐빙 {
    static final int U = 0, D = 1, F = 2, B = 3, L = 4, R = 5;
    static final String[] colors = {"w", "y", "r", "o", "g", "b"};
    static int T, n;
    static String[][][] cube;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            cube = new String[6][3][3];
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        cube[i][j][k] = colors[i];
                    }
                }
            }
            n = Integer.parseInt(br.readLine());
            String[] str = br.readLine().split(" ");
            for(String s : str){
                solution(s);
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    sb.append(cube[U][i][j]);
                }
                sb.append("\n");
            }

        }
        System.out.print(sb);

    }

    private static void solution(String str) {
        int dir = str.charAt(1) == '+' ? 1 : -1;

        switch (str.charAt(0)) {
            case 'U':
                rotateUp(dir);
                break;
            case 'D':
                rotateDown(dir);
                break;
            case 'F':
                rotateFront(dir);
                break;
            case 'B':
                rotateBack(dir);
                break;
            case 'L':
                rotateLeft(dir);
                break;
            case 'R':
                rotateRight(dir);
                break;
        }
    }

    private static void rotateRight(int dir) {
        String[] tmp = {cube[B][0][2], cube[B][1][2], cube[B][2][2]};
        if (dir == 1) {
            for (int i = 0; i < 3; i++) {
                cube[B][i][2] = cube[U][i][2];
            }
            for (int i = 0; i < 3; i++) {
                cube[U][i][2] = cube[F][i][2];
            }
            for (int i = 0; i < 3; i++) {
                cube[F][i][2] = cube[D][i][2];
            }
            for (int i = 0; i < 3; i++) {
                cube[D][i][2] = tmp[i];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                cube[B][i][2] = cube[D][i][2];
            }
            for (int i = 0; i < 3; i++) {
                cube[D][i][2] = cube[F][i][2];
            }
            for (int i = 0; i < 3; i++) {
                cube[F][i][2] = cube[U][i][2];
            }
            for (int i = 0; i < 3; i++) {
                cube[U][i][2] = tmp[i];
            }
        }
        rotateMe(R, dir);
    }

    private static void rotateLeft(int dir) {
        String[] tmp = {cube[B][0][0], cube[B][1][0], cube[B][2][0]};
        if (dir == 1) {
            for (int i = 0; i < 3; i++) {
                cube[B][i][0] = cube[D][i][0];
            }
            for (int i = 0; i < 3; i++) {
                cube[D][i][0] = cube[F][i][0];
            }
            for (int i = 0; i < 3; i++) {
                cube[F][i][0] = cube[U][i][0];
            }
            for (int i = 0; i < 3; i++) {
                cube[U][i][0] = tmp[i];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                cube[B][i][0] = cube[U][i][0];
            }
            for (int i = 0; i < 3; i++) {
                cube[U][i][0] = cube[F][i][0];
            }
            for (int i = 0; i < 3; i++) {
                cube[F][i][0] = cube[D][i][0];
            }
            for (int i = 0; i < 3; i++) {
                cube[D][i][0] = tmp[i];
            }
        }
        rotateMe(L, dir);
    }

    private static void rotateBack(int dir) {
//        String[] tmp = cube[U][0].clone();
        String[] tmp = {cube[U][0][0], cube[U][0][1], cube[U][0][2]};
        if (dir == 1) {
            for (int i = 0; i < 3; i++) {
                cube[U][0][i] = cube[R][0][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[R][0][i] = cube[D][2][2 - i];
            }
            for (int i = 0; i < 3; i++) {
                cube[D][2][2 - i] = cube[L][0][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[L][0][i] = tmp[i];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                cube[U][0][i] = cube[L][0][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[L][0][i] = cube[D][2][2 - i];
            }
            for (int i = 0; i < 3; i++) {
                cube[D][2][i] = cube[R][0][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[R][0][i] = tmp[i];
            }
        }
        rotateMe(B, dir);

    }

    private static void rotateFront(int dir) {
//        String[] tmp = cube[L][2].clone();
        String[] tmp = {cube[L][2][0], cube[L][2][1], cube[L][2][2]};
        if (dir == 1) {
            for (int i = 0; i < 3; i++) {
                cube[L][2][2 - i] = cube[D][0][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[D][0][i] = cube[R][2][2 - i];
            }
            for (int i = 0; i < 3; i++) {
                cube[R][2][i] = cube[U][2][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[U][2][i] = tmp[i];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                cube[L][2][i] = cube[U][2][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[U][2][i] = cube[R][2][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[R][2][i] = cube[D][0][2 - i];
            }
            for (int i = 0; i < 3; i++) {
                cube[D][0][i] = tmp[2 - i];
            }
        }
        rotateMe(F, dir);
    }

    private static void rotateDown(int dir) {
        String[] tmp = {cube[L][0][0], cube[L][1][0], cube[L][2][0]};
        if (dir == 1) {
            for (int i = 0; i < 3; i++) {
                cube[L][2 - i][0] = cube[B][0][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[B][0][i] = cube[R][i][2];
            }
            for (int i = 0; i < 3; i++) {
                cube[R][i][2] = cube[F][2][2 - i];
            }
            for (int i = 0; i < 3; i++) {
                cube[F][2][i] = tmp[i];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                cube[L][i][0] = cube[F][2][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[F][2][i] = cube[R][2 - i][2];
            }
            for (int i = 0; i < 3; i++) {
                cube[R][i][2] = cube[B][0][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[B][0][i] = tmp[2 - i];
            }
        }
        rotateMe(D, dir);
    }



    private static void rotateUp(int dir) {
        String[] tmp = {cube[B][2][0], cube[B][2][1], cube[B][2][2]};
        if (dir == 1) {
            // 시계방향
//            String[] tmp = cube[B][2].clone();

            for (int i = 0; i < 3; i++) {
                cube[B][2][2 - i] = cube[L][i][2];
            }
            for (int i = 0; i < 3; i++) {
                cube[L][i][2] = cube[F][0][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[F][0][i] = cube[R][2 - i][0];
            }
            for (int i = 0; i < 3; i++) {
                cube[R][i][0] = tmp[i];
            }
        } else {
//            String[] tmp = cube[B][2].clone();
            for (int i = 0; i < 3; i++) {
                cube[B][2][i] = cube[R][i][0];
            }
            for (int i = 0; i < 3; i++) {
                cube[R][i][0] = cube[F][0][2 - i];
            }
            for (int i = 0; i < 3; i++) {
                cube[F][0][i] = cube[L][i][2];
            }
            for (int i = 0; i < 3; i++) {
                cube[L][i][2] = tmp[2 - i];
            }
        }
        rotateMe(U, dir);
    }

    private static void rotateMe(int side, int dir) {
        String[][] mySide = cube[side];

        String[][] tmp = new String[3][3];
        if (dir == 1) {
            for (int i = 0; i < 3; i++) {
                tmp[i][2] = mySide[0][i];
            }
            for (int i = 0; i < 3; i++) {
                tmp[2][2 - i] = mySide[i][2];
            }
            for (int i = 0; i < 3; i++) {
                tmp[i][0] = mySide[2][i];
            }
            for (int i = 0; i < 3; i++) {
                tmp[0][2 - i] = mySide[i][0];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                tmp[2-i][0] = mySide[0][i];
            }
            for (int i = 0; i < 3; i++) {
                tmp[2][i] = mySide[i][0];
            }
            for (int i = 0; i < 3; i++) {
                tmp[2-i][2] = mySide[2][i];
            }
            for (int i = 0; i < 3; i++) {
                tmp[0][i] = mySide[i][2];
            }
        }
        tmp[1][1] = mySide[1][1];
        cube[side] = tmp;
    }
}
