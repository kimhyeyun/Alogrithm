import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_2931_가스관 {
    static int N, M;
    static char[][] map;
    static boolean[][] isVisited;
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        isVisited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            Arrays.fill(isVisited[i], true);
        }

        int mx = 0, my = 0, zx = 0, zy = 0;

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();

            for (int j = 0; j < M; j++) {
                if(map[i][j] != '.') isVisited[i][j] = false;
                if (map[i][j] == 'M') {
                    mx = i;
                    my = j;
                }
                if (map[i][j] == 'Z') {
                    zx = i;
                    zy = j;
                }
            }
        }

        Block mBlock = searchFirstBlock(mx, my);
        Block zBlock = searchFirstBlock(zx, zy);

        Block mEndBlock = search(mBlock);
        Block zEndBlock = search(zBlock);

        char removedBlock = getBlock(mEndBlock.x, mEndBlock.y, mEndBlock.dir, reverseDirection(zEndBlock.dir));

        System.out.println((mEndBlock.x + 1) + " " + (mEndBlock.y + 1) + " " + removedBlock);

    }

//    이전 방향과 다음 향하는 방향을 토대로 블록을 구하는 함수
//    @return : 블록의 종류 값
    private static char getBlock(int x, int y, int prev, int next) {
        if (prev == next) {
            if (prev == 0 || prev == 1) return '-';
            else return '|';
        } else {
//            십자 블록 예외 처리
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;
                if(!isVisited[nx][ny]) return '+';
//               지워진 블록 상하좌우의 블록 중 접근하지 않은 블록이 있다면
            }
        }
        if((prev == 2 && next == 1) || (prev == 0 && next == 3)) return '1';
        if((prev == 3 && next == 1) || (prev == 0 && next == 2)) return '2';
        if((prev == 1 && next == 2) || (prev == 3 && next == 0)) return '3';
        if((prev == 1 && next == 3) || (prev == 2 && next == 0)) return '4';

        return '*';
    }

    //    방향을 반대로 바꾸는 함수
//    @return : 반대 방향(왼, 오, 위, 아래)
    private static int reverseDirection(int dir) {
        switch (dir) {
            case 0:
                return 1;
            case 1:
                return 0;
            case 2:
                return 3;
            case 3:
                return 2;
            default:
                return -1;
        }
    }

    //    시작 블록부터 가스 탐색
    private static Block search(Block start) {
        int nx = start.x + dx[start.dir];
        int ny = start.y + dy[start.dir];

        if (map[nx][ny] == '.') {
            return new Block(nx, ny, start.dir);
        } else {
            isVisited[nx][ny] = true;
            return search(new Block(nx, ny, getMoveDirection(map[nx][ny], start.dir)));
        }
    }

    //    첫 시작 위치의 다음 블록 찾기
    private static Block searchFirstBlock(int x, int y) {
        isVisited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;
            if (map[nx][ny] != '.' && (map[nx][ny] != 'Z' && map[nx][ny] != 'M')) {
                Block block = new Block(nx, ny, getMoveDirection(map[nx][ny], d));
                isVisited[nx][ny] = true;
                return block;
            }
        }
        return null;
    }

//     블록의 종류에 따라 달라지는 가스의 다음 방향을 구하는 함수
//    @return : 다음 방향 (왼, 오, 위, 아래)
    private static int getMoveDirection(char block, int prevDir) {
        switch (block) {
            case '1' -> {
                if(prevDir == 2) return 1;
                if(prevDir == 0) return 3;
                break;
            }
            case '2' -> {
                if(prevDir == 0) return 2;
                if(prevDir == 3) return 1;
                break;
            }
            case '3' -> {
                if(prevDir == 1) return 2;
                if(prevDir == 3) return 0;
                break;
            }
            case '4' -> {
                if(prevDir == 1) return 3;
                if(prevDir == 2) return 0;
                break;
            }
        }
        return prevDir;
    }


    static class Block {
        int x, y;
        int dir;

        public Block(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public void set(int nx, int ny, int nd) {
            this.x = nx;
            this.y = ny;
            this.dir = nd;
        }
    }
}
