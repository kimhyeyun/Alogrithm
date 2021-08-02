public class 조이스틱 {

    public static int solution(String name){

        int answer = 0;

        int min_move = name.length() - 1;

        for(int i = 0; i < name.length();i++){
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);

            int next_idx = i + 1;

            while(next_idx < name.length() && name.charAt(next_idx) == 'A'){
                next_idx++;
            }

            min_move = Math.min(min_move, (i * 2) + name.length() - next_idx);
            // i*2 는 다시 앞으로 돌아가는 횟수, name.length() - next_idx 는 맨 오른쪽에서 위치 
        }

        answer += min_move;

        return answer;
    }
    public static void main(String[] args) {
        System.out.println(solution("JEROEN"));
    }
}
