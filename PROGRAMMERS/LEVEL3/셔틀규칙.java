import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 셔틀규칙 {

    static PriorityQueue<Integer> q = new PriorityQueue<>();
    static int answer = 0;

    public static String solution(int n, int t, int m, String[] timetable) {
        
        for(int i = 0  ; i < timetable.length; i++){
            String[] s = timetable[i].split(":");
            int time = Integer.parseInt(s[0])*60 + Integer.parseInt(s[1]);
            q.add(time);
        }



        int busTime = 9*60;
        int people = 0;
        for(int i = 0 ; i < n ; i++){
            // 탑승한 인원
            people = 0;
            while(!q.isEmpty()){
                int boardingTime = q.poll();

                if(boardingTime <= busTime && people < m){
                    people++;
                }
                else{
                    q.add(boardingTime);
                    break;
                }

                answer = boardingTime - 1;
            }

            // 다음 버스 시간
            busTime += t;
        }

        if(people < m){
            answer = busTime-t;
        }

        String hour = String.format("%02d", answer/60);
        String minute = String.format("%02d", answer%60);

        return hour+":"+minute;
    }

    public static void main(String[] args) {
        String[] timetable = {"23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"};
        System.out.println(solution(10, 60, 45, timetable));
    }
}
