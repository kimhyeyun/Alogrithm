import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_2583_영역구하기 {

    static int M, N, K;
    static boolean[][] board, isVisited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static List<Integer> ans = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        M = Integer.parseInt(stringTokenizer.nextToken());
        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        board = new boolean[M][N];
        isVisited = new boolean[M][N];
        
        for(int i = 0 ; i < K ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(stringTokenizer.nextToken());
            int x1 = Integer.parseInt(stringTokenizer.nextToken());
            int y2 = Integer.parseInt(stringTokenizer.nextToken());
            int x2 = Integer.parseInt(stringTokenizer.nextToken());

            for(int x = x1; x < x2; x++){
                for(int y = y1; y < y2; y++){
                    board[x][y] = true;
                }
            }
        }

        for(int i = 0 ; i < M ; i++){
            for(int j = 0 ; j < N ; j++){
                if(!board[i][j] && !isVisited[i][j])
                    ans.add(BFS(i, j));
            }
        }

        Collections.sort(ans);
        System.out.println(ans.size());
        for(int a : ans){
            System.out.print(a + " ");
        }
    }

    private static int BFS(int i, int j) {
        int sz = 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i,j});
        isVisited[i][j] = true;

        while(!queue.isEmpty()){
            int[] q = queue.poll();
            int x = q[0];
            int y = q[1];

            for(int d = 0 ; d < 4; d++){
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(nx < 0 || M <= nx || ny < 0 || N <= ny || isVisited[nx][ny] || board[nx][ny]) continue;

                sz++;
                queue.offer(new int[]{nx, ny});
                isVisited[nx][ny] = true;
            }
        }

        return sz;
    }
}
