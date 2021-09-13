import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_BOJ_2026_소풍 {
    static int N, K, F;
    static int[][] friends;     // 친구 관계 저장 1: 친구, 0 : 아님
    static int[] friendsNum;    // i의 친구 수
    static boolean[] isVisited; 
    static StringBuilder sb;
    static boolean flag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        K = Integer.parseInt(stringTokenizer.nextToken());
        N = Integer.parseInt(stringTokenizer.nextToken());
        F = Integer.parseInt(stringTokenizer.nextToken());

        friends = new int[N+1][N+1];
        friendsNum = new int[N+1];
        isVisited = new boolean[N+1];

        for(int i = 0 ; i < F; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());

            friends[x][y] = 1;
            friends[y][x] = 1;

            friendsNum[x]++;
            friendsNum[y]++;
        }

        for(int i = 1; i <= N ;i++){
            // 친구의 수가 본인 포함 K명 이상이여야함
            // K명 미만이라면 불가능하기때문!
            if(friendsNum[i] < K-1)
                continue;
            if(flag)
                break;

            isVisited[i] = true;
            DFS(i, 1);
            isVisited[i] = false;
            
        }

        if(flag)
            System.out.print(sb);
        else
            System.out.println(-1);
    }
    private static void DFS(int idx, int cnt) {
        if(flag)
            return;

        if(cnt == K){
            for(int i = 1; i <= N ; i++){
                if(isVisited[i])
                    sb.append(i).append("\n");
            }
            flag = true;
            return;
        }

        for(int i = idx+1 ; i <= N ;i++){
            if(friends[idx][i] == 1 && isFriend(i)){
                isVisited[i] = true;
                DFS(i, cnt+1);
                isVisited[i] = false;
            }
        }
    }
    private static boolean isFriend(int idx) {
        for(int i = 1; i <= N; i++){
            if(isVisited[i] && friends[idx][i] != 1)
                return false;
        }

        return true;
    }
}
