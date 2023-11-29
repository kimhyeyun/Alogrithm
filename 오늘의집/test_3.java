import java.util.ArrayList;

public class test_3 {
    static boolean[] isVisited;
    static int[][] keyBoards;
    static int answer = Integer.MAX_VALUE;
    public static int solution(String param0){
        keyBoards = new int[][]{
                {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5},
                {1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5},
                {2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {2, 5},
                {3, 0}, {3, 1}, {3, 2}, {3, 3}, {3, 4}, {3, 5},
                {4, 0}, {4, 1}
        };

        isVisited = new boolean[param0.length()];
        findDist(param0,0, 0, ' ', ' ', new ArrayList<Character>());

        return answer;
    }

    private static void findDist(String param0, int idx, int cost, char finger1, char finger2, ArrayList<Character> list ) {
        if (list.size() == param0.length()) {
            answer = Math.min(answer, cost);
            for (char c : list
            ) {
                System.out.print(c);
            }
            System.out.println();
            return;
        }

        for (int i = idx; i < param0.length(); i++) {
            if(isVisited[i]) continue;

            int tmpCost = 0;
            if (finger1 == ' ') {
                tmpCost = 0;
            } else {
                int r = (finger1 - 'A') / 6;
                int c = (finger1 - 'A') % 6;

                int r1 = (param0.charAt(i) - 'A') / 6;
                int c1 = (param0.charAt(i) - 'A') % 6;

                tmpCost = (Math.abs(r1 - r) + Math.abs(c1 - c));
            }
            isVisited[i] = true;
            list.add(param0.charAt(i));
            findDist(param0, i+1,cost + tmpCost, param0.charAt(i), finger2,list);
            list.remove(list.size() - 1);
            isVisited[i] = false;

            if (finger2 == ' ') {
                tmpCost = 0;
            } else {
                int r = (finger2 - 'A') / 6;
                int c = (finger2 - 'A') % 6;

                int r1 = (param0.charAt(i) - 'A') / 6;
                int c1 = (param0.charAt(i) - 'A') % 6;

                tmpCost = (Math.abs(r1 - r) + Math.abs(c1 - c));
            }
            isVisited[i] = true;
            list.add(param0.charAt(i));
            findDist(param0, i+1,cost + tmpCost, finger1, param0.charAt(i), list);
            list.remove(list.size() - 1);
            isVisited[i] = false;
        }

        return;
    }


    public static void main(String[] args) {
        System.out.println(solution("HAPPY"));
    }
}
