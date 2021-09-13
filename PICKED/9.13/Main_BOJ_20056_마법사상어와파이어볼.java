import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_20056_마법사상어와파이어볼 {
    static class fireBall{
        int r;
        int c;
        int m;
        int s;
        int d;

        fireBall(int r, int c, int m, int s, int d){
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
    static List<fireBall>[][] map;
    static List<fireBall> list = new ArrayList<>();
    static int dx[] = {-1,-1,0,1,1,1,0,-1};
    static int dy[] = {0,1,1,1,0,-1,-1,-1};
    
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
            list.add(new fireBall(r-1, c-1, m, s, d));
        }

        while(K-- > 0){
            // 이동
            for(fireBall fBall : list){
                fBall.r = (fBall.r + (fBall.s % N) * dx[fBall.d] + N) % N;
                fBall.c = (fBall.c + (fBall.s % N) * dy[fBall.d] + N) % N;

                map[fBall.r][fBall.c].add(fBall);

            }

            // 파이어볼이 두개 이상
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < N; j++){
                    if(map[i][j].size() == 1)
                        map[i][j].clear();
                    if(map[i][j].size() == 0)
                        continue;
                    
                    boolean even = map[i][j].get(0).d % 2 == 0 ? true : false;
                    boolean odd = map[i][j].get(0).d % 2 == 1 ? true : false;

                    int mSum = 0;
                    int sSum = 0;

                    for(fireBall f : map[i][j]){
                        mSum += f.m;
                        sSum += f.s;
                        even = even && f.d % 2 == 0 ? true : false;
                        odd = odd && f.d % 2 == 1 ? true : false;
                        list.remove(f);
                    }

                    int nm = Math.floorDiv(mSum, 5);
                    int ns = Math.floorDiv(sSum, map[i][j].size());
                    map[i][j].clear();

                    if(nm == 0)
                        continue;

                    if(even || odd){
                        for(int k = 0 ; k < 7 ; k += 2)
                            list.add(new fireBall(i,j,nm, ns, k));
                    }
                    else{
                        for(int k = 1; k < 8 ; k+=2)
                            list.add(new fireBall(i, j, nm, ns, k));
                    }
                }
            }
        }

        int ans = 0;
        for(fireBall f : list)
            ans += f.m;

        System.out.println(ans);
    }
}
