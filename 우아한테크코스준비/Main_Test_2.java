public class Main_Test_2 {
    public String solution(String[] log) {
        String answer = "";
        int start = 0;
        int stop = 0;
        int total = 0;

        for(int i = 0 ; i < log.length ; i++){
            if(i%2 == 0)
                // 시작 시간
                start = stringToTime(log[i]);
            else{
                // 종료 시간
                stop = stringToTime(log[i]);
                int tmp = stop - start;

                tmp = tmp < 5 ? 0 : tmp;
                tmp = tmp > 105 ? 105 : tmp;

                total += tmp;
            }
        }

        int hour = total/60;
        int min = total %60;

        String h = hour > 9 ? String.valueOf(hour) : "0" + hour;
        String m = min > 9 ? String.valueOf(min) : "0" + min;

        answer = h + ":" + m;
        return answer;
    }

    private int stringToTime(String str) {
        String[] time = str.split(":");
        int t = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);

        return t;
    }
}
