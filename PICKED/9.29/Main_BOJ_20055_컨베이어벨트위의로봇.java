import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_20055_컨베이어벨트위의로봇 {
    static int N, K;
    static int[] belt;
    static boolean[] robot;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        belt = new int[2*N];
        robot = new boolean[N];
        // 로봇은 N+1칸에서 내리기때문에 N개!

        stringTokenizer = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < 2*N ;i++){
            belt[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        
        int turn = 0;
        while(true){
            turn++;

            beltMove();
            robotMove();
            
            if(isEnd())
                break;          
        }

        System.out.println(turn);
    }

    private static boolean isEnd() {
        int cnt = 0;
        for(int i = 0 ; i < 2*N ; i++){
            if(belt[i] == 0)
                cnt++;
            
            if(cnt >= K)
                return true;
        }
        return false;
    }
    private static void robotMove() {
        // 로봇 내리기
        if(robot[N-1])
            robot[N-1] = false;

        // 로봇 이동
        for(int i = N-2 ; i >= 0 ; i--){
            if(!robot[i+1] && robot[i] && belt[i+1] > 0){
                robot[i] = false;
                robot[i+1] = true;
                belt[i+1]--;
            }
        }

        // 올라가는 위치에 로봇이 없고, 내구도가 1이상이면 올리기
        if(!robot[0] && belt[0] >= 1){
            robot[0] = true;
            belt[0]--;
        }
    }

    private static void beltMove() {
        // 컨베이너 벨트 이동
        int tmp = belt[2*N-1];

        for(int i = 2*N-1 ; i > 0 ; i--){
            belt[i] = belt[i-1];
        }
        belt[0] = tmp;

        // 로봇이동
        for(int i = N-1 ; i > 0; i--){
            robot[i] = robot[i-1];
        }
        // N에서 로봇이 내리기 때문에 이동 후에는 0에 로봇이 없음
        robot[0] = false;
    }
}
