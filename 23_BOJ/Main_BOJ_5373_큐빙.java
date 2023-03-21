import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_5373_큐빙 {
    static char[][][] cube;
    static final int U = 0, D = 1, F = 2, B = 3, L = 4, R = 5;
    static final char[] colors = {'w', 'y', 'r', 'o', 'g', 'b'};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            initCube();
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            while (n-- > 0) {
                char[] str = st.nextToken().toCharArray();
                int dir = str[1] == '+' ? 1 : 0;

                if (str[0] == 'U') {
                    rotateUp(dir);
                } else if (str[0] == 'D') {
                    rotateDown(dir);
                } else if (str[0] == 'F') {
                    rotateFront(dir);
                } else if (str[0] == 'B') {
                    rotateBack(dir);
                } else if (str[0] == 'L') {
                    rotateLeft(dir);
                } else {
                    rotateRight(dir);
                }
            }

            printUpSide();
        }
        System.out.println(sb);
    }

    private static void print() {
        for (int i = 0; i < 6; i++) {
            System.out.println(i);
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print(cube[i][j][k]);
                }
                System.out.println();
            }
            System.out.println("-------------");
        }
    }

    private static void printUpSide() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(cube[U][i][j]);
            }
            sb.append("\n");
        }
    }

    private static void rotateRight(int dir) {
        char[] tmp = {cube[U][0][2], cube[U][1][2], cube[U][2][2]};

        if (dir == 1) {
            for (int i = 0; i < 3; i++) {
                cube[U][i][2] = cube[F][i][2];
                cube[F][i][2] = cube[D][i][2];
                cube[D][i][2] = cube[B][i][2];
                cube[B][i][2] = tmp[i];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                cube[U][i][2] = cube[B][i][2];
                cube[B][i][2] = cube[D][i][2];
                cube[D][i][2] = cube[F][i][2];
                cube[F][i][2] = tmp[i];
            }
        }
        rotate(R, dir);
    }

    private static void rotateLeft(int dir) {
        char[] tmp = {cube[D][0][0], cube[D][1][0], cube[D][2][0]};

        if (dir == 1) {
            for (int i = 0; i < 3; i++) {
                cube[D][i][0] = cube[F][i][0];
                cube[F][i][0] = cube[U][i][0];
                cube[U][i][0] = cube[B][i][0];
                cube[B][i][0] = tmp[i];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                cube[D][i][0] = cube[B][i][0];
                cube[B][i][0] = cube[U][i][0];
                cube[U][i][0] = cube[F][i][0];
                cube[F][i][0] = tmp[i];
            }
        }
//        print();
        rotate(L, dir);
    }

    private static void rotateDown(int dir) {
        char[] tmp = {cube[L][0][0], cube[L][1][0], cube[L][2][0]};

        if (dir == 1) {
            for (int i = 0; i < 3; i++) {
                cube[L][i][0] = cube[B][0][2 - i];
                cube[B][0][2 - i] = cube[R][2 - i][2];
                cube[R][2 - i][2] = cube[F][2][i];
                cube[F][2][i] = tmp[i];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                cube[L][i][0] = cube[F][2][i];
                cube[F][2][i] = cube[R][2 - i][2];
                cube[R][2 - i][2] = cube[B][0][2 - i];
                cube[B][0][2 - i] = tmp[i];
            }
        }
        rotate(D, dir);
    }

    private static void rotateFront(int dir) {
        char[] tmp = {cube[L][2][0], cube[L][2][1], cube[L][2][2]};

        if (dir == 1) {
            for (int i = 0; i < 3; i++) {
                cube[L][2][i] = cube[D][0][2 - i];
                cube[D][0][2 - i] = cube[R][2][i];
                cube[R][2][i] = cube[U][2][i];
                cube[U][2][i] = tmp[i];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                cube[L][2][i] = cube[U][2][i];
                cube[U][2][i] = cube[R][2][i];
                cube[R][2][i] = cube[D][0][2 - i];
                cube[D][0][2 - i] = tmp[i];
            }
        }
//        print();
        rotate(F, dir);
    }

    private static void rotateBack(int dir) {
        char[] tmp = {cube[L][0][0], cube[L][0][1], cube[L][0][2]};

        if (dir == 1) {
            for (int i = 0; i < 3; i++) {
                cube[L][0][i] = cube[U][0][i];
                cube[U][0][i] = cube[R][0][i];
                cube[R][0][i] = cube[D][2][2 - i];
                cube[D][2][2 - i] = tmp[i];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                cube[L][0][i] = cube[D][2][2 - i];
                cube[D][2][2 - i] = cube[R][0][i];
                cube[R][0][i] = cube[U][0][i];
                cube[U][0][i] = tmp[i];
            }
        }
//        print();
        rotate(B, dir);
    }

    private static void rotateUp(int dir) {
        char[] tmp = {cube[L][0][2], cube[L][1][2], cube[L][2][2]};

        if (dir == 1) {
            for (int i = 0; i < 3; i++) {
                cube[L][i][2] = cube[F][0][i];
                cube[F][0][i] = cube[R][2 - i][0];
                cube[R][2 - i][0] = cube[B][2][2 - i];
                cube[B][2][2 - i] = tmp[i];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                cube[L][i][2] = cube[B][2][2 - i];
                cube[B][2][2 - i] = cube[R][2 - i][0];
                cube[R][2 - i][0] = cube[F][0][i];
                cube[F][0][i] = tmp[i];
            }
        }
//        print();
        rotate(U, dir);
    }

    private static void rotate(int side, int dir) {
        char[][] tmp = new char[3][3];

        if (dir == 1) {
            for (int i = 0; i < 3; i++) {
                tmp[0][i] = cube[side][2 - i][0];
            }
            for (int i = 0; i < 3; i++) {
                tmp[2 - i][0] = cube[side][2][2 - i];
            }
            for (int i = 0; i < 3; i++) {
                tmp[2][2 - i] = cube[side][i][2];
            }
            for (int i = 0; i < 3; i++) {
                tmp[i][2] = cube[side][0][i];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                tmp[0][i] = cube[side][i][2];
            }
            for (int i = 0; i < 3; i++) {
                tmp[i][2] = cube[side][2][2 - i];
            }
            for (int i = 0; i < 3; i++) {
                tmp[2][2 - i] = cube[side][2 - i][0];
            }
            for (int i = 0; i < 3; i++) {
                tmp[2 - i][0] = cube[side][0][i];
            }
        }

        tmp[1][1] = cube[side][1][1];
        cube[side] = tmp;

//        print();
    }

    private static void initCube() {
        cube = new char[6][3][3];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    cube[i][j][k] = colors[i];
                }
            }
        }
    }
}
