import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_20056_마법사상어와파이어볼 {
    static class fireBall{
        int r;  // row
        int c;  // col
        int m;  // mass
        int s;  // speed
        int d;  // direction

        fireBall(int r, int c, int m, int s, int d){
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
    static List<fireBall>[][] map;
    static List<fireBall> fireBallList = new ArrayList<>();
    static int dr[] = {-1,-1,0,1,1,1,0,-1};
    static int dc[] = {0,1,1,1,0,-1,-1,-1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());

        map = new List[N][N];
        for(int i = 0 ; i < N ;i++){
            for(int j = 0 ; j < N; j++){
                map[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0; i < M ;i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int s = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());

            fireBallList.add(new fireBall(r-1, c-1, m, s, d));
        }

        while(K-- > 0){
            // 이동
            for(fireBall fBall : fireBallList){
                // s 가 최대 1,000으로 N을 초과할 수 있기 때문에 % N
                int nr = fBall.r + dr[fBall.d] *(fBall.s % N);
                int nc = fBall.c + dc[fBall.d] *(fBall.s % N);

                if(nr >= N)
                    nr -= N;
                else if(nr < 0)
                    nr += N;
                if(nc >= N)
                    nc -= N;
                else if(nc < 0)
                    nc += N;

                fBall.r = nr;
                fBall.c = nc;

                map[fBall.r][fBall.c].add(fBall);

            }

            // 파이어볼이 두개 이상인곳 확인
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < N; j++){
                    // 2개 미만이면 지우고 continue;
                    if(map[i][j].size() < 2){
                        map[i][j].clear();
                        continue;
                    }
                    
                    // 2개 이상이면
                    // 1. 방향이 모두 짝수(홀수)인지 아닌지 확인
                    boolean even = map[i][j].get(0).d % 2 == 0 ? true : false;
                    boolean odd = map[i][j].get(0).d % 2 == 1 ? true : false;

                    int mSum = 0;   // 질량 합 
                    int sSum = 0;   // 속도 합

                    for(fireBall f : map[i][j]){
                        mSum += f.m;
                        sSum += f.s;
                        // 방향이 짝수인지
                        even = even && f.d % 2 == 0 ? true : false;
                        // 방향이 홀수인지
                        odd = odd && f.d % 2 == 1 ? true : false;

                        fireBallList.remove(f);
                    }

                    int nMass = Math.floorDiv(mSum, 5);
                    int nSpeed = Math.floorDiv(sSum, map[i][j].size());
                    map[i][j].clear();

                    if(nMass == 0)
                        continue;

                    // 방향이 모두 홀수(짝수) 이면
                    if(even || odd){
                        // 방향이 0, 2, 4, 6
                        for(int k = 0 ; k < 7 ; k += 2)
                            fireBallList.add(new fireBall(i,j,nMass, nSpeed, k));
                    }
                    // 아니면
                    else{
                        // 방향이 1, 3, 5, 7
                        for(int k = 1; k < 8 ; k+=2)
                            fireBallList.add(new fireBall(i, j, nMass, nSpeed, k));
                    }
                }
            }
        }

        int ans = 0;
        for(fireBall f : fireBallList)
            ans += f.m;

        System.out.println(ans);
    }
}
