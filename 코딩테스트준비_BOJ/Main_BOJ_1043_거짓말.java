import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_BOJ_1043_거짓말 {
    static int N, M;
    static boolean[] knownPeople;
    static Set<Integer>[] parties;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        knownPeople = new boolean[N + 1];
        parties = new Set[M + 1];

        for (int i = 1; i <= M; i++) {
            parties[i] = new HashSet<>();
        }

        st = new StringTokenizer(br.readLine());
        int knownCount = Integer.parseInt(st.nextToken());

        for (int i = 0; i < knownCount; i++) {
            knownPeople[Integer.parseInt(st.nextToken())] = true;
        }

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int p = 1; p <= M; p++) {
            String[] inputs = br.readLine().split(" ");
            int partyPeoplCount = Integer.parseInt(inputs[0]);

            if(partyPeoplCount <= 1){
                parties[p].add(Integer.parseInt(inputs[1]));
                continue;
            }

            for (int j = 1; j < partyPeoplCount; j++) {
                int a = Integer.parseInt(inputs[j]);
                int b = Integer.parseInt(inputs[j + 1]);

                if(find(a) != find(b)) union(a, b);

                parties[p].add(a);
                parties[p].add(b);
            }
        }

        boolean[] isVisited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (knownPeople[i] && !isVisited[i]) {
                int root = find(i);
                for (int j = 1; j <= N; j++) {
                    if (find(j) == root) {
                        knownPeople[j] = true;
                        isVisited[j] = true;
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= M; i++) {
            boolean flag = false;

            for (int p : parties[i]) {
                if(knownPeople[p]){
                    flag = true;
                    break;
                }
            }

            if(!flag) answer += 1;
        }

        System.out.println(answer);
    }

    private static void union(int a, int b) {
        int parentB = find(b);

        parent[parentB] = a;
    }

    private static int find(int idx) {
        if(parent[idx] == idx) return idx;

        parent[idx] = find(parent[idx]);
        return parent[idx];
    }


}
