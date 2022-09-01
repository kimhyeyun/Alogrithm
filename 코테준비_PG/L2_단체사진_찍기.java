import java.util.HashMap;
import java.util.Map;

public class L2_단체사진_찍기 {

    static Map<Character, Integer> friends;
    static boolean[] isVisited;
    static int[] position;
    static int answer;
    public static int solution(int n, String[] data) {
        friends = new HashMap<>();
        isVisited = new boolean[8];
        position = new int[8];
        answer = 0;
        char[] kakaos = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};

        for (int i = 0; i < 8; i++) friends.put(kakaos[i], i);

        DFS(0, data);

        return answer;
    }

    private static void DFS(int idx, String[] data) {
        if (idx == 8) {
            if(isPossible(data)) answer += 1;
            return;
        }

        for (int i = 0; i < 8; i++) {
            if(isVisited[i]) continue;

            isVisited[i] = true;
            position[idx] = i;
            DFS(idx + 1, data);
            isVisited[i] = false;
        }
    }

    private static boolean isPossible(String[] data) {
        for (String d : data) {
            int x = position[friends.get(d.charAt(0))];
            int y = position[friends.get(d.charAt(2))];
            char compare = d.charAt(3);
            int diff = (int) d.charAt(4) - '0';
            int numOfBetween = Math.abs(y - x) - 1;

            switch (compare) {
                case '=':
                    if(diff != numOfBetween) return false;
                    break;
                case '<':
                    if(numOfBetween >= diff) return false;
                    break;
                case '>':
                    if(numOfBetween <= diff) return false;
                    break;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] data = {"N~F=0", "R~T>2"};
//        String[] data = {"M~C<2", "C~M>1"};
        System.out.println(solution(2, data));

    }
}
