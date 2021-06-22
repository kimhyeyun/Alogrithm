import java.util.Arrays;

public class 완주하지못한선수{
    public static void main(String[] args) {
        String p[] = {"mislav", "stanko", "mislav", "ana"};
        String c[] = {"stanko", "ana", "mislav"};

        System.out.println(solution(p, c));
    }

    public static String solution(String[] participant, String[] completion) {
        String answer = "";
        
        Arrays.sort(participant);
        Arrays.sort(completion);

        boolean flag = true;

        for(int i=0;i<completion.length;i++){
            if(!participant[i].equals(completion[i])){
                answer = participant[i];
                flag = false;
                break;
            }
        }

        if(flag){
            answer = participant[participant.length-1];
        }

        return answer;
    }

}