import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOj_20665_독서실_거리두기 {
    static class Time{
        int hour;
        int minute;

        public Time(int hour, int minute) {
            this.hour = hour;
            this.minute = minute;
        }
    }

    static class TimePair implements Comparable<TimePair>{
        Time start;
        Time end;

        public TimePair(Time start, Time end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(TimePair o) {
            if(this.start.hour == o.start.hour){
                if(this.start.minute == o.start.minute){
                    if(this.end.hour == o.end.hour){
                        return this.end.minute - o.end.minute;
                    }
                    return this.end.hour - o.end.hour;
                }
                return this.start.minute - o.start.minute;
            }
            return this.start.hour - o.start.hour;
        }
    }

    static int N, T, P;
    static List<TimePair> timeList;
    static boolean[][][] isSeated;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        T = Integer.parseInt(stringTokenizer.nextToken());
        P = Integer.parseInt(stringTokenizer.nextToken());

        isSeated = new boolean[24][60][N + 1];
        timeList = new ArrayList<>();

        for (int i = 0; i < T; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());

            Time startTime = new Time(start / 100, start % 100);
            Time endTime = new Time(end / 100, end % 100);

            timeList.add(new TimePair(startTime, endTime));
        }

        Collections.sort(timeList);

        solve();
    }

    private static void solve() {
        for (TimePair time : timeList) {
            int startHour = time.start.hour;
            int startMin = time.start.minute;
            int endHour = time.end.hour;
            int endMin = time.end.minute;

            int seat = findSeat(startHour, startMin);

            for (int hour = startHour; hour <= endHour; hour++) {
                if (startHour == endHour) {
                    for (int min = startMin; min < endMin; min++) {
                        isSeated[hour][min][seat] = true;
                    }
                    break;
                }

                if (hour == startHour) {
                    for (int min = startMin; min < 60; min++) {
                        isSeated[hour][min][seat] = true;
                    }
                } else if (hour == endHour) {
                    for (int min = 0; min < endMin; min++) {
                        isSeated[hour][min][seat] = true;
                    }
                } else {
                    for (int min = 0; min < 60; min++) {
                        isSeated[hour][min][seat] = true;
                    }
                }
            }
        }

        int ans = 0;
        for (int hour = 9; hour < 21; hour++) {
            for (int min = 0; min < 60; min++) {
                if(!isSeated[hour][min][P]) ans += 1;
            }
        }
        System.out.println(ans);
    }

    private static int findSeat(int hour, int min) {
        int maxDis = 0;
        int idx = -1;

        for (int i = 1; i < N + 1; i++) {
            if (!isSeated[hour][min][i]) {
                int dist = findMinSeatDis(hour, min, i);
                if (dist > maxDis) {
                    maxDis = dist;
                    idx = i;
                }
            }
        }
        return idx;
    }

    private static int findMinSeatDis(int hour, int min, int seatIdx) {
        int minDist = 101;

        for (int next = 1; next < N + 1; next++) {
            if(next == seatIdx) continue;
            if (isSeated[hour][min][next]) {
                int d = Math.abs(seatIdx - next);
                if(d < minDist) minDist = d;
            }
        }
        return minDist;
    }
}
