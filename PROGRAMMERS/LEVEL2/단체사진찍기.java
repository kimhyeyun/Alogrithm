import java.util.HashMap;

public class 단체사진찍기{
    private static int cnt;
    static int[] position;
    static boolean[] visited;
    static HashMap<Character, Integer> kakao;
    static boolean ok;

    public static void main(String[] args) {
        String[] d = {"N~F=0", "R~T>2"};
        System.out.println(solution(2, d));
    }

    public static int solution(int n, String[] data) {
        int answer = 0;
        position = new int[8];
        visited = new boolean[8];
        cnt = 0;
        kakao = new HashMap<>();
        
        // Position의 index 번호로 매핑
        kakao.put('A', 0);
        kakao.put('C', 1);
        kakao.put('F', 2);
        kakao.put('J', 3);
        kakao.put('M', 4);
        kakao.put('N', 5);
        kakao.put('R', 6);
        kakao.put('T', 7);

        // 1.DFS
        dfs(0, data);

        answer = cnt;

        return answer;
    }

    private static void dfs(int idx, String[] data) {
        if(idx == 8){
            if(isPossible(data)){
                cnt++;
            }
            return;
        }

        // DFS
        for(int i = 0;i < 8;i++){
            // 방문 ㄴㄴ
            if(!visited[i]){
                visited[i] = true;
                position[idx] = i;
                dfs(idx+1, data);
                visited[i] = false;
            }
        }
    }

    private static boolean isPossible(String[] data) {

        for(int i = 0;i < data.length;i++){
            int X = kakao.get(data[i].charAt(0));
            int Y = kakao.get(data[i].charAt(2));
            char type = data[i].charAt(3);
            int diff = data[i].charAt(4) - '0';
            int Xpos = position[X];
            int Ypos = position[Y];

            if(type == '='){
                if(Math.abs(Xpos - Ypos)-1 != diff){
                    return false;
                }
            }
            else if(type == '>'){
                if(Math.abs(Xpos - Ypos)-1 <= diff){
                    return false;
                }
            }
            else if(type == '<'){
                if(Math.abs(Xpos - Ypos)-1 >= diff){
                    return false;
                }
            }
        }
        return true;
    }
}