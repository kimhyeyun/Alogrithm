public class 다트게임 {
    public static void main(String[] args) {
        System.out.println(solution("1S*2T*3S"));
    }

    public static int solution(String dartResult) {
        int answer = 0;

        // 각 기회마다 점수 저장
        int[] scores = new int[3];
        // 기회 카운트
        int cnt = 0;
        // 점수
        int score = 0;

        for(int i=0;i<dartResult.length();i++){
            if(dartResult.charAt(i) == 'S'){
                if(i > 1 && 0 <= dartResult.charAt(i-2) - '0' && dartResult.charAt(i-2) - '0' <= 9){
                    score  = 10;
                }
                else
                    score = dartResult.charAt(i-1) - '0';
                score = (int) Math.pow(score, 1);
                scores[cnt++] = score;
            }
            else if(dartResult.charAt(i) == 'D'){
                if(i > 1 && 0 <= dartResult.charAt(i-2) - '0' && dartResult.charAt(i-2) - '0' <= 9){
                    score = 10;
                }
                else
                    score = dartResult.charAt(i-1) - '0';
                score = (int) Math.pow(score, 2);
                scores[cnt++] = score;
            }
            else if(dartResult.charAt(i) == 'T'){
                if(i > 1 && 0 <= dartResult.charAt(i-2) - '0' && dartResult.charAt(i-2) - '0' <= 9){
                    score = 10;
                }
                else
                    score = dartResult.charAt(i-1) - '0';
                score = (int) Math.pow(score, 3);
                scores[cnt++] = score;
            }
            else if(dartResult.charAt(i) == '*'){
                if(cnt != 1){
                    scores[cnt-2] *= 2;
                }
                scores[cnt-1] *= 2;
            }
            else if(dartResult.charAt(i) == '#'){
                scores[cnt-1] *= -1;
            }
            else
                continue;
        }

        for(int i=0;i<3;i++){
            answer += scores[i];
        }
        return answer;
    }
}
