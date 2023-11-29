import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 카드_뭉치 {
    public String solution(String[] cards1, String[] cards2, String[] goal) {

        Queue<String> deck1 = new LinkedList<>();
        Queue<String> deck2 = new LinkedList<>();

        deck1.addAll(Arrays.asList(cards1));

        deck2.addAll(Arrays.asList(cards2));

        for (int i = 0; i < goal.length; i++) {
            if(!deck1.isEmpty() && deck1.peek().equals(goal[i])){
                deck1.poll();
                continue;
            }

            if (!deck2.isEmpty() && deck2.peek().equals(goal[i])) {
                deck2.poll();
                continue;
            }

            if(deck1.isEmpty() && deck2.isEmpty()) return "NO";
            else if ((!deck1.isEmpty() && !deck1.peek().equals(goal[i])) || (!deck2.isEmpty() && !deck2.peek().equals(goal[i]))) {
                return "NO";
            }
        }

        return "YES";
    }
}
