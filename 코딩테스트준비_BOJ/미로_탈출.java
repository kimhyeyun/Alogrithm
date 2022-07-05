import java.util.*;

public class 미로_탈출 {

    static class Node implements Comparable<Node>{
        int to, weight, status;

        public Node(int to, int weight, int status) {
            this.to = to;
            this.weight = weight;
            this.status = status;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    static int INF = Integer.MAX_VALUE;
    static int[][] dist;
    static List<Node>[] list, rList;
    static Map<Integer, Integer> trapList;

    /*
    * 못하겠다;;;
    * */
    public static int solution(int n, int start, int end, int[][] roads, int[] traps) {
        list = new List[n + 1];
        rList = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<>();
            rList[i] = new ArrayList<>();
        }

        trapList = new HashMap<>();
        for (int i = 0; i < traps.length; i++) {
            trapList.put(traps[i], 1 << (i + 1));
        }

        for (int[] road : roads) {
            list[road[0]].add(new Node(road[1], road[2], 0));
            rList[road[1]].add(new Node(road[0], road[2], 0));
        }

        int len = 1 << 11;
        dist = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            Arrays.fill(dist[i], INF);
        }

        dijkstra(start, end);

        return 0;
    }

    private static void dijkstra(int start, int end) {
        Queue<Node> q = new PriorityQueue<>();
        dist[start][0] = 0;
        q.add(new Node(start, 0, 0));

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int to = cur.to;
            int weight = cur.weight;
            int status = cur.status;

            if(to == end) return;

            boolean flag1 = false;
            if (trapList.containsKey(to)) {
                if((status & trapList.get(to)) == trapList.get(to)) flag1 = true;
            }

            boolean flag2 = false;
            for (Node next : list[to]) {
                int nStatus = status;
                if (trapList.containsKey(next.to)) {
                    flag2 = ((status & trapList.get(next.to)) != 0);
                    nStatus = trapSwitch(flag2, status, trapList.get(next.to));
                }
            }
        }
    }

    private static int trapSwitch(boolean flag, int now, int node) {
        if(flag) return now ^ node;
        else return now | node;
    }


}
