import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class 표_병합 {

    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();

        int[] parent = new int[2501];
        String[] graph = new String[2501];

        for (int i = 1; i <= 2500; i++) {
            parent[i] = i;
            graph[i] = "";
        }

        for (String command : commands) {
            String[] s = command.split(" ");

            if (s[0].equals("UPDATE")) {
                if (s.length == 4) {
                    int x = Integer.parseInt(s[1]);
                    int y = Integer.parseInt(s[2]);

                    int num = (50 * (x - 1) + y);
                    graph[find(num, parent)] = s[3];
                } else {
                    for (int i = 1; i <= 2500; i++) {
                        if(graph[i].equals(s[1])) graph[i] = s[2];
                    }
                }
            } else if (s[0].equals("MERGE")) {
                int x1 = Integer.parseInt(s[1]);
                int y1 = Integer.parseInt(s[2]);
                int x2 = Integer.parseInt(s[3]);
                int y2 = Integer.parseInt(s[4]);

                int n1 = (50 * (x1 - 1) + y1);
                int n2 = (50 * (x2 - 1) + y2);

                int root1 = find(n1, parent);
                int root2 = find(n2, parent);

                if(root1 == root2) continue;

                String rootString = graph[root1].isBlank() ? graph[root2] : graph[root1];

                graph[root1] = "";
                graph[root2] = "";

                union(root1, root2, parent);

                graph[root1] = rootString;
            } else if (s[0].equals("UNMERGE")) {
                int x = Integer.parseInt(s[1]);
                int y = Integer.parseInt(s[2]);

                int num = (50 * (x - 1)) + y;
                int root = find(num, parent);

                String rootString = graph[root];
                graph[root] = "";
                graph[num] = rootString;

                List<Integer> deleteList = new ArrayList<>();
                for (int i = 1; i <= 2500; i++) {
                    if(find(i, parent) == root) deleteList.add(i);
                }

                for (int d : deleteList) {
                    parent[d] = d;
                }
            } else if (s[0].equals("PRINT")) {
                int x = Integer.parseInt(s[1]);
                int y = Integer.parseInt(s[2]);

                int num = (50 * (x - 1)) + y;
                int root = find(num, parent);

                if(graph[root].isBlank()) answer.add("EMPTY");
                else answer.add(graph[root]);
            }
        }

        return answer.toArray(new String[answer.size()]);
    }

    private void union(int a, int b, int[] parent) {
        a = find(a, parent);
        b = find(b, parent);

        if(a != b) parent[b] = a;
    }

    private int find(int num, int[] parent) {
        if(parent[num] == num) return num;
        else return parent[num] = find(parent[num], parent);
    }

    @Test
    void test() {
        Assertions.assertArrayEquals(solution(new String[]{"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"}), new String[]{"EMPTY", "group"});
    }
}
