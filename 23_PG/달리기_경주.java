import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class 달리기_경주 {

    public String[] solution(String[] players, String[] callings) {
        String[] answer = new String[players.length];

        Map<String, Integer> rank = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            rank.put(players[i], i);
        }

        for (String calling : callings) {
            int changeRank = rank.get(calling);
            changeRank(changeRank, calling, players, rank);
        }

        for (String player : rank.keySet()) {
            answer[rank.get(player)] = player;
        }

        return answer;
    }

    private void changeRank(int changeRank, String changePlayer, String[] players, Map<String, Integer> rank) {
        String prevPlayer = players[changeRank - 1];
        players[changeRank - 1] = changePlayer;
        players[changeRank] = prevPlayer;
        rank.put(prevPlayer, changeRank);
        rank.put(changePlayer, changeRank - 1);
    }


    @Test
    void test() {
        assertArrayEquals(solution(new String[]{"mumu", "soe", "poe", "kai", "mine"}, new String[]{"kai", "kai", "mine", "mine"}), new String[]{"mumu", "kai", "mine", "soe", "poe"});
    }
}
