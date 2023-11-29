public class sol_1 {
    public static int solution(String[] logs) {
        int answer = 0;

        for (String log : logs) {
            String[] s = log.split(" | :");
            if (s.length != 12 || log.length() > 100) {
                answer += 1;
                continue;
            }
            if(!s[0].equals("team_name")) answer += 1;
            else if(!s[3].equals("application_name")) answer += 1;
            else if(!s[6].equals("error_level")) answer += 1;
            else if(!s[9].equals("message")) answer += 1;
        }
        return answer;
    }
    public static void main(String[] args) {
        String[] logs = {"team_name : MyTeam application_name : YourApp error_level : info messag : IndexOutOfRange", "no such file or directory", "team_name : recommend application_name : recommend error_level : info message : RecommendSucces11", "team_name : recommend application_name : recommend error_level : info message : Success!", "   team_name : db application_name : dbtest error_level : info message : test", "team_name     : db application_name : dbtest error_level : info message : test", "team_name : TeamTest application_name : TestApplication error_level : info message : ThereIsNoError"};
        System.out.println(solution(logs));
    }
}
