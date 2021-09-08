import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_16202_kruskal {

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

    static List<edge> edgeList;
    static int[] parentNode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());

        // pqEdge = new PriorityQueue<>();
        edgeList = new LinkedList<>();
        parentNode = new int[n+1];

        for(int i = 1 ; i <= m ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());

            edgeList.add(new edge(x, y, i));
        }

        Collections.sort(edgeList);

        while(k-- > 0){
            for(int i = 0 ; i < n+1 ; i++){
                parentNode[i] = i;
            }
            int weight = 0;
            int cnt = 1;

            for(edge e : edgeList){
                if(findParent(e.from) != findParent(e.to)){
                    weight += e.weight;
                    union(e);
                    // 가능한 이유가 모두 연결이된 스패닝 트리라면 한 번 이상 카운트 되기때문 만약 끊어진다면
                    cnt++;
                }
            }

            if(cnt < n){
                sb.append(0 + " ");
            }
            else{
                sb.append(weight + " ");
                edgeList.remove(0);
            }
           
        }
        System.out.println(sb);
    }

    private static void union(edge e) {
        int v1 = findParent(e.from);
        int v2 = findParent(e.to);

        if(v1 != v2)
            parentNode[v2] = v1;

    }

    private static int findParent(int vertex) {
        if(parentNode[vertex] == vertex){
            return vertex;
        }
        return parentNode[vertex] = findParent(parentNode[vertex]);
    }


}
