import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1717_집합의표현 {

    static int n, m;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        parent = new int[n+1];
        for(int i = 0 ; i <= n ; i++)
            parent[i] = i;

        for(int i = 0 ; i < m; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            int input = Integer.parseInt(stringTokenizer.nextToken());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());

            if(input == 0){
                unionSet(a,b);
            }
            else{
                if(find(a) == find(b))
                    sb.append("YES").append("\n");
                else
                    sb.append("NO").append("\n");
            }
        }

        System.out.print(sb);
    }

    private static void unionSet(int a, int b) {
        parent[find(b)] = find(a);
    }

    private static int find(int a) {
        if(a != parent[a])
            parent[a] = find(parent[a]);

        return parent[a];
    }
}
