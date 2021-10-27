import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_20665_독서실거리두기 {

    static class time{
        int hour;
        int min;

        time(int hour, int min){
            this.hour = hour;
            this.min = min;
        }
    }

    static class timePair implements Comparable<timePair>{
        time start;
        time end;

        timePair(time start, time end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(timePair o) {
            int result = this.start.hour - o.start.hour;
            if(result == 0)
                result = this.start.min - o.start.min;
            if(result == 0)
                result = this.end.hour - o.end.hour;
            if(result == 0)
                result = this.end.min - o.end.min;

            return result;
        }
    }

    static int N, T, P;
    static List<timePair> timeList = new ArrayList<>();
    static boolean[][][] isSeated;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());  // 좌석 개수
        T = Integer.parseInt(stringTokenizer.nextToken());  // 예약자 수
        P = Integer.parseInt(stringTokenizer.nextToken());  // 좋아하는 좌석 번호

        isSeated = new boolean[24][60][N+1];

        for(int i = 0 ; i < T; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());

            time startTime = new time(start / 100, start % 100);
            time endTime = new time(end / 100, end % 100);

            timeList.add(new timePair(startTime, endTime));
        }

        Collections.sort(timeList);

        solve();
    }

    private static void solve() {
        for(timePair time : timeList){
            int startHour = time.start.hour;
            int startMin = time.start.min;
            int endHour = time.end.hour;
            int endMin = time.end.min;
            int seat = findSeat(startHour, startMin);

            for(int hour = startHour ; hour <= endHour ; hour++){
                if(startHour == endHour){
                    for(int min = startMin ; min < endMin ; min++){
                        isSeated[hour][min][seat] = true;
                    }
                    break;
                }

                if(hour == startHour){
                    for(int min = startMin ; min < 60 ; min++){
                        isSeated[hour][min][seat] = true;
                    }
                }
                else if(hour == endHour){
                    for(int min = 0 ; min < endMin ; min++){
                        isSeated[hour][min][seat] = true;
                    }
                }
                else{
                    for(int min = 0 ; min < 60 ; min++){
                        isSeated[hour][min][seat] = true;
                    }
                }
            }
        }

        int ans = 0;
        for(int hour = 9; hour < 21 ; hour++){
            for(int min = 0 ; min < 60 ; min++){
                if(!isSeated[hour][min][P]) 
                    ans++;
            }
        }
        
        System.out.println(ans);
    }

    private static int findSeat(int hour, int min) {
        int maxDist = 0;
        int idx = -1;

        for(int i = 1; i <= N; i++){
            if(!isSeated[hour][min][i]){
                int dist = findMinSeatDist(hour, min, i);
                if(dist > maxDist){
                    maxDist = dist;
                    idx = i;
                }
            }
        }
        return idx;
    }

    // 가장 가까이에 앉아있는 좌석거리 찾기
    private static int findMinSeatDist(int hour, int min, int i) {
        int minDist = 101;
        for(int next = 1; next <= N ; next++){
            if(next == i)   continue;
            if(isSeated[hour][min][next]){
                int d = Math.abs(i - next);
                if(d < minDist)
                    minDist = d;
            }
        }
        return minDist;
    }
}
