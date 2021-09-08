import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_16202_MST게임 {

    static class edge implements Comparable<edge> {
        int from;
        int to;
        int weight;

        edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(edge o) {
            // TODO Auto-generated method stub
            return this.weight - o.weight;
        }
    }

    static List<edge>[] graph;  // graph
    static List<edge> mst;      // MST vertex 저장 list
    static boolean[] isVisted;  

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());

        int weight = 0;
        graph = new ArrayList[n + 1];
        isVisted = new boolean[n + 1];

        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());

            graph[x].add(new edge(x, y, i));
            graph[y].add(new edge(y, x, i));
        }

        while (k-- > 0) {
            // prim
            mst = new ArrayList<>();
            PriorityQueue<edge> pq = new PriorityQueue<>();
            Queue<Integer> queue = new LinkedList<>();
            isVisted = new boolean[n+1];
            weight = 0;

            queue.add(1);
            mst.add(new edge(0,1,0));
            isVisted[1] = true;

            while (!queue.isEmpty()) {
                int now = queue.poll();

                for (edge e : graph[now]) {
                    if (!isVisted[e.to])
                        pq.add(e);
                }

                while (!pq.isEmpty()) {
                    edge e = pq.poll();

                    if (!isVisted[e.to]) {
                        queue.add(e.to);
                        isVisted[e.to] = true;
                        mst.add(e);
                        weight += e.weight;
                        break;
                    }
                }
            }

            if (mst.size() < n){
                sb.append(0 + " ");
            }
            else{
                sb.append(weight + " ");

                Collections.sort(mst);
                edge e = mst.get(1);
                if(e != null){
                    graph[e.from].remove(0);
                    // edge e2 = new edge(e.to, e.from, e.weight);
                    graph[e.to].remove(0);
                }  
            }        
        }
        System.out.println(sb);
    }
}
