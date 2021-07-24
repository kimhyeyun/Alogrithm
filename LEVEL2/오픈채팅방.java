import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class 오픈채팅방 {
    public static void main(String[] args) {
        String[] r = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        
        String[] a = solution(r);

        for(String s : a){
            System.out.println(s);
        }
    }

    public static String[] solution(String[] record) {
        String[] answer = new String[record.length];
        HashMap<String, String> nickName = new HashMap<>();

        for(int i = 0;i < record.length;i++){
            String[] str = record[i].split(" ");

            if(str[0].equals("Enter")){
                nickName.put(str[1], str[2]);
            }

            if(str[0].equals("Change")){
                nickName.replace(str[1], str[2]);
            }
        }

        List<String> list = new LinkedList<>();
        for(int i = 0;i < record.length;i++){
            String[] str = record[i].split(" ");

            if(str[0].equals("Enter")){
                list.add(nickName.get(str[1])+"님이 들어왔습니다.");
            }
            if(str[0].equals("Leave")){
                list.add(nickName.get(str[1])+"님이 나갔습니다.");
            }
        }

        answer = new String[list.size()];
        int i = 0;
        for(String s : list){
            answer[i++] = s;
        }

        return answer;
    }
}
