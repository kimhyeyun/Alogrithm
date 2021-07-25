import java.util.Arrays;

public class 서울에서김서방찾기 {
    public static void main(String[] args) {
        String[] s = {"Jane", "Kim"};
        System.out.println(solution(s));
    }

    // 내 코드 : 배열을 돌면서 "Kim" 과 일치하는 위치 찾기
    public static String solution(String[] seoul) {
        String answer = "";

        for(int i=0;i<seoul.length;i++){
            if(seoul[i].equals("Kim")){
                answer = "김서방은 "+i+"에 있다";
                break;
            }
        }

        return answer;
    }

    // 다른 사람 풀이 : 속도는 유사하나 간단한 코드
    public String FindKim(String[] seoul){
        int idx = Arrays.asList(seoul).indexOf("Kim");
        return "김서방은 "+idx+" 에 있다";
    }
}
