import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 여행경로 {

    static boolean[] isVisited;
    static List<String> ansList;
    public static String[] solution(String[][] tickets) {
        String[] answer = {};

        isVisited = new boolean[tickets.length];
        ansList = new ArrayList<>();

        Arrays.sort(tickets, new Comparator<String[]>(){
            @Override
            public int compare(String[] o1, String[] o2) {
                if(o1[0].toString().equals(o2[0].toString()))
                    return o1[1].toString().compareTo(o2[1].toString());
                return o1[0].toString().compareTo(o2[0].toString());
            }
        });

        DFS("ICN", tickets, 0, "ICN");

        answer = ansList.get(0).split(" ");

        return answer;
    }

    private static void DFS(String now, String[][] tickets, int cnt, String result) {
        
        if(cnt == tickets.length){
            ansList.add(result);
            return;
        }
        for(int i = 0 ; i < tickets.length ; i++){
            if(tickets[i][0].equals(now) && !isVisited[i]){
                isVisited[i] = true;
                DFS(tickets[i][1], tickets, cnt+1, result+" "+tickets[i][1]);
                isVisited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};

        String[] ans = solution(tickets);

        for(String a : ans){
            System.out.println(a);
        }
    }
}
