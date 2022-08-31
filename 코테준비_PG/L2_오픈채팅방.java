import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L2_오픈채팅방 {
    public static String[] solution(String[] record) {
        Map<String, String> userNickName = new HashMap<>();

        for (String r : record) {
            String[] str = r.split(" ");
            if(str[0].equals("Enter")) userNickName.put(str[1], str[2]);
            else if(str[0].equals("Change")) userNickName.replace(str[1], str[2]);
        }

        List<String> list = new ArrayList<>();
        for (String r : record) {
            String[] str = r.split(" ");
            if(str[0].equals("Enter")) list.add(userNickName.get(str[1]) + "님이 들어왔습니다.");
            else if(str[0].equals("Leave")) list.add(userNickName.get(str[1]) + "님이 나갔습니다.");
        }

        return list.toArray(new String[list.size()]);
    }
}
