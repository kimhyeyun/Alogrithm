import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 오픈채팅방 {
    public String[] solution(String[] record) {
        Map<String, String> nicknames = new HashMap<>();

        for (String r : record) {
            String[] s = r.split(" ");

            if(s[0].equals("Enter")) nicknames.put(s[1], s[2]);
            else if(s[0].equals("Change")) nicknames.replace(s[1], s[2]);
        }

        List<String> answer = new ArrayList<>();
        for (String r : record) {
            String[] s = r.split(" ");

            if(s[0].equals("Enter")) answer.add(nicknames.get(s[1]) + "님이 들어왔습니다.");
            else if(s[0].equals("Leave")) answer.add(nicknames.get(s[1]) + "님이 나갔습니다.");
        }

        return answer.toArray(new String[answer.size()]);
    }

    @Test
    void test() {
        Assertions.assertArrayEquals(solution(new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"}), new String[]{"Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."});
    }
}