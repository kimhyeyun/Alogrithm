import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class L2_피로도 {
    static int answer;
    public static int solution(int k, int[][] dungeons) {
        answer = 0;
        DFS(k, new boolean[dungeons.length], new LinkedList<Integer>(), dungeons);

        return answer;
    }

    private static void DFS(int k, boolean[] isPicked, LinkedList<Integer> order, int[][] dungeons) {
        if (order.size() == dungeons.length) {
            int energy = k;
            for (int i = 0; i < order.size(); i++) {
                if (energy < dungeons[order.get(i)][0]) {
                    answer = Math.max(answer, i);
                    return;
                } else {
                    energy -= dungeons[order.get(i)][1];
                }
            }
            answer = Math.max(answer, dungeons.length);
        }

        for (int i = 0; i < dungeons.length; i++) {
            if(isPicked[i]) continue;

            isPicked[i] = true;
            order.add(i);
            DFS(k, isPicked, order, dungeons);
            isPicked[i] = false;
            order.removeLast();
        }
    }

    public static void main(String[] args) {
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
        System.out.println(solution(80, dungeons));

    }
}
