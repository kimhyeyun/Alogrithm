import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class 불량사용자 {
    static boolean[] isVisited;
    static Set<LinkedList<String>> set;
    static LinkedList<String> list;
    static int answer;

    public static int solution(String[] user_id, String[] banned_id) {
        answer = 0;
        isVisited = new boolean[user_id.length];
        set = new HashSet<>();
        list = new LinkedList<>();

        DFS(0, user_id, banned_id);

        return set.size();
    }

    private static void DFS(int cnt, String[] user_id, String[] banned_id) {

        if(cnt == banned_id.length){
            // 정렬을 해주기 때문에 return 된 후, 마지막 삭제시 다른 것이 삭제되기때문
            LinkedList<String> tmp = new LinkedList<>();
            for(String l : list)
                tmp.add(l);
            
            Collections.sort(tmp);

            if(!set.contains(tmp)){
                set.add(tmp);
                answer++;
            }
            return;
        }

        for(int i = 0 ; i < user_id.length ; i++){
            if(!isVisited[i] && check(user_id[i], banned_id[cnt])){
                isVisited[i] = true;
                list.addLast(user_id[i]);
                DFS(cnt+1, user_id, banned_id);
                list.removeLast();
                isVisited[i] = false;
            }
        }
    }

    // 글자수가 같고 * 를 제외한 char이 일치하면 true
    private static boolean check(String originId, String bannedId) {
        if(originId.length() != bannedId.length())
            return false;

        for(int i = 0 ; i < originId.length() ; i++){
            if(bannedId.charAt(i) != '*' && bannedId.charAt(i) != originId.charAt(i))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "*rodo", "******", "******"};

        System.out.println(solution(user_id, banned_id));
    }
    
}
