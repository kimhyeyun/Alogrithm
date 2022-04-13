import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_5373_큐빙 {
    static int U = 0, D = 1, F = 2, B = 3, L = 4, R = 5;
    static char[] colors = {'w', 'y', 'r', 'o', 'g', 'b'};
    static int T, n;
    static char[][][] cube;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            cube = new char[6][3][3];
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        cube[i][j][k] = colors[i];
                    }
                }
            }

            n = Integer.parseInt(br.readLine());
            String[] str = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                rotate(str[i]);
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    sb.append(cube[U][i][j]);
                }
                sb.append('\n');
            }
        }

        System.out.println(sb);

    }

    private static void rotate(String str) {
        boolean dir = str.charAt(1) == '+' ? true : false;

        switch (str.charAt(0)) {
            case 'U':
                upRotate(dir);
                break;
            case 'D':
                downRotate(dir);
                break;
            case 'L':
                leftRotate(dir);
                break;
            case 'R':
                rightRotate(dir);
                break;
            case 'F':
                frontRotate(dir);
                break;
            case 'B':
                backRotate(dir);
                break;
        }
    }

    private static void backRotate(boolean dir) {
        char[] tmp = {cube[D][0][0], cube[D][0][1], cube[D][0][2]};

        if (dir) {
            for (int i = 0; i < 3; i++) {
                cube[D][0][i] = cube[L][0][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[L][0][i] = cube[U][0][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[U][0][i] = cube[R][0][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[R][0][i] = tmp[i];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                cube[D][0][i] = cube[R][0][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[R][0][i] = cube[U][0][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[U][0][i] = cube[L][0][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[L][0][i] = tmp[i];
            }
        }
        rotateMe("B",dir);
    }

    private static void frontRotate(boolean dir) {
        char[] tmp = {cube[D][2][0], cube[D][2][1], cube[D][2][2]};

        if (dir) {
            for (int i = 0; i < 3; i++) {
                cube[D][2][i] = cube[R][2][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[R][2][i] = cube[U][2][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[U][2][i] = cube[L][2][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[L][2][i] = tmp[i];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                cube[D][2][i] = cube[L][2][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[L][2][i] = cube[U][2][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[U][2][i] = cube[R][2][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[R][2][i] = tmp[i];
            }
        }
        rotateMe("F",dir);
    }

    private static void rightRotate(boolean dir) {
        char[] tmp = {cube[U][0][2], cube[U][1][2], cube[U][2][2]};

        if (dir) {
            for (int i = 0; i < 3; i++) {
                cube[U][i][2] = cube[F][i][2];
            }
            for (int i = 0; i < 3; i++) {
                cube[F][i][2] = cube[D][2 - i][0];
            }
            for (int i = 0; i < 3; i++) {
                cube[D][i][0] = cube[B][2 - i][2];
            }
            for (int i = 0; i < 3; i++) {
                cube[B][i][2] = tmp[i];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                cube[U][i][2] = cube[B][i][2];
            }
            for (int i = 0; i < 3; i++) {
                cube[B][i][2] = cube[D][2 - i][0];
            }
            for (int i = 0; i < 3; i++) {
                cube[D][i][0] = cube[F][2 - i][2];
            }
            for (int i = 0; i < 3; i++) {
                cube[F][i][2] = tmp[i];
            }
        }
        rotateMe("R",dir);
    }

    private static void leftRotate(boolean dir) {
        char[] tmp = {cube[D][0][2], cube[D][1][2], cube[D][2][2]};

        if (dir) {
            for (int i = 0; i < 3; i++) {
                cube[D][i][2] = cube[F][2 - i][0];
            }
            for (int i = 0; i < 3; i++) {
                cube[F][i][0] = cube[U][i][0];
            }
            for (int i = 0; i < 3; i++) {
                cube[U][i][0] = cube[B][i][0];
            }
            for (int i = 0; i < 3; i++) {
                cube[B][i][0] = tmp[2 - i];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                cube[D][i][2] = cube[B][2 - i][0];
            }
            for (int i = 0; i < 3; i++) {
                cube[B][i][0] = cube[U][i][0];
            }
            for (int i = 0; i < 3; i++) {
                cube[U][i][0] = cube[F][i][0];
            }
            for (int i = 0; i < 3; i++) {
                cube[F][i][0] = tmp[2 - i];
            }
        }
        rotateMe("L",dir);
    }

    private static void downRotate(boolean dir) {
        char[] tmp = {cube[F][2][0], cube[F][2][1], cube[F][2][2]};

        if (dir) {
            for (int i = 0; i < 3; i++) {
                cube[F][2][i] = cube[L][i][0];
            }
            for (int i = 0; i < 3; i++) {
                cube[L][i][0] = cube[B][0][2 - i];
            }
            for (int i = 0; i < 3; i++) {
                cube[B][0][i] = cube[R][i][2];
            }
            for (int i = 0; i < 3; i++) {
                cube[R][i][2] = tmp[2 - i];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                cube[F][2][i] = cube[R][2 - i][2];
            }
            for (int i = 0; i < 3; i++) {
                cube[R][i][2] = cube[B][0][i];
            }
            for (int i = 0; i < 3; i++) {
                cube[B][0][i] = cube[L][2 - i][0];
            }
            for (int i = 0; i < 3; i++) {
                cube[L][i][0] = tmp[i];
            }
        }
        rotateMe("D",dir);
    }

    private static void upRotate(boolean dir) {
        // 시계방향
        char[] tmp = {cube[B][2][0], cube[B][2][1], cube[B][2][2]};
        if (dir) {
            for (int i = 0; i < 3; i++) {
                cube[B][2][i] = cube[L][2 - i][2];
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
        rotateMe("U",dir);
    }

    private static void rotateMe(String side, boolean dir) {
        char[][] sideChar;

        if(side.equals("U")) sideChar = cube[U];
        else if(side.equals("D")) sideChar = cube[D];
        else if(side.equals("F")) sideChar = cube[F];
        else if(side.equals("B")) sideChar = cube[B];
        else if(side.equals("L")) sideChar = cube[L];
        else sideChar = cube[R];

        char[][] tmp = new char[3][3];
        if (dir) {
            for (int i = 0; i < 3; i++) {
                tmp[i][2] = sideChar[0][i];
            }
            for (int i = 0; i < 3; i++) {
                tmp[0][2 - i] = sideChar[i][0];
            }
            for (int i = 0; i < 3; i++) {
                tmp[i][0] = sideChar[2][i];
            }
            for (int i = 0; i < 3; i++) {
                tmp[2][i] = sideChar[2 - i][2];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                tmp[i][0] = sideChar[0][i];
            }
            for (int i = 0; i < 3; i++) {
                tmp[2][i] = sideChar[i][0];
            }
            for (int i = 0; i < 3; i++) {
                tmp[i][2] = sideChar[2][2 - i];
            }
            for (int i = 0; i < 3; i++) {
                tmp[0][i] = sideChar[i][2];
            }
        }

        tmp[1][1] = sideChar[1][1];
        if(side.equals("U")) cube[U] = tmp;
        else if(side.equals("D")) cube[D] = tmp;
        else if(side.equals("F")) cube[F] = tmp;
        else if(side.equals("B")) cube[B] = tmp;
        else if(side.equals("L")) cube[L] = tmp;
        else cube[R] = tmp;

    }
}
