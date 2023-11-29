import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class sol_5_표_편집 {
    public static String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();

        int[][] merge = new int[50][50];
        String[][] graph = new String[50][50];

        int mergeIdx = 1;

        for (String command : commands) {
            String[] cstr = command.split(" ");

            if (cstr[0].equals("UPDATE")) {
                int len = cstr.length;
                if (len == 4) {
                    int r = Integer.parseInt(cstr[1]) - 1;
                    int c = Integer.parseInt(cstr[2]) - 1;

                    graph[r][c] = cstr[3];

                    if (merge[r][c] != 0) {
                        for (int i = 0; i < 50; i++) {
                            for (int j = 0; j < 50; j++) {
                                if (merge[i][j] == merge[r][c]) {
                                    graph[i][j] = cstr[3];
                                }
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < 50; i++) {
                        for (int j = 0; j < 50; j++) {
                            if(graph[i][j] == null) continue;
                            if (graph[i][j].equals(cstr[1])) graph[i][j] = cstr[2];
                        }
                    }
                }
            } else if (cstr[0].equals("MERGE")) {
                int r1 = Integer.parseInt(cstr[1]) - 1;
                int c1 = Integer.parseInt(cstr[2]) - 1;
                int r2 = Integer.parseInt(cstr[3]) - 1;
                int c2 = Integer.parseInt(cstr[4]) - 1;

               if(merge[r1][c1] == 0) merge[r1][c1] = merge[r2][c2] = mergeIdx++;
               else merge[r2][c2] = merge[r1][c1];

               graph[r2][c2] = graph[r1][c1];

            } else if (cstr[0].equals("UNMERGE")) {
                int r = Integer.parseInt(cstr[1]) - 1;
                int c = Integer.parseInt(cstr[2]) - 1;

                String v = graph[r][c];
                int m = merge[r][c];
                for (int i = 0; i < 50; i++) {
                    for (int j = 0; j < 50; j++) {
                        if (merge[i][j] == m) {
                            merge[i][j] = 0;
                            graph[i][j] = null;
                        }
                    }
                }

                graph[r][c] = v;

            }else{
                int r = Integer.parseInt(cstr[1]) - 1;
                int c = Integer.parseInt(cstr[2]) - 1;

                if(graph[r][c] == null) answer.add("EMPTY");
                else answer.add(graph[r][c]);
            }
        }
        return answer.toArray(new String[answer.size()]);
    }

    public static void main(String[] args) {
//        String[] commands = {"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 otalian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"};
        String[] commands = {"UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d","MERGE 1 1 1 2","MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2 ", "PRINT 1 1"};
        String[] answer = solution(commands);
        for (String a : answer) {
            System.out.println(a);
        }
    }
}
