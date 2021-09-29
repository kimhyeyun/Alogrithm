import java.util.*;

public class 단어변환 {

    static boolean[] isVisited;
    static int answer;
    public static int solution(String begin, String target, String[] words) {
        answer = 9999999;
        isVisited = new boolean[words.length];

        List<String> tmp = Arrays.asList(words);
        if(!tmp.contains(target))
            return 0;

        DFS(words, begin, target, 0);

        return answer;
    }
    private static void DFS(String[] words, String now, String target, int cnt) {

        if(now.equals(target)){
            answer = Math.min(answer, cnt);
            return;
        }

        for(int i = 0; i < words.length; i++){
            // 다른 문자 수
            int diff = 0;

            if(isVisited[i])
                continue;
            
            for(int j = 0 ; j < words[i].length() ; j++){
                if(now.charAt(j) != words[i].charAt(j))
                    diff++;

                if(diff > 1)
                    break;
            }
            
            // 하나만 차이나면 변경
            if(diff == 1){
                isVisited[i] = true;
                DFS(words, words[i], target, cnt+1);
                isVisited[i] = false;
            }
        }

    }
    public static void main(String[] args) {
        String[] words = {"hot", "dot", "dog", "lot", "log"};
        System.out.println(solution("hit", "cog", words));
    }
}
