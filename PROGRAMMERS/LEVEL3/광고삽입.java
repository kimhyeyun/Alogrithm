public class 광고삽입 {
    public static String solution(String play_time, String adv_time, String[] logs) {

        int playTime = strToSecond(play_time);
        int advTime = strToSecond(adv_time);

        long[] play = new long[playTime+1];
        for(String log : logs){
            String[] l = log.split("-");
            int startTime = strToSecond(l[0]);
            int endTime = strToSecond(l[1]);

            for(int i = startTime ; i < endTime ; i++){
                play[i]++;
            }
        }

        // 0초 ~ advTime
        int startTime = 0;
        int endTime = advTime;
        long sum = 0;
        for(int i = startTime; i < endTime ; i++){
            sum += play[i];
        }

        long max = sum;
        int answerTime = startTime;
        while(endTime <= playTime){
            sum -= play[startTime];
            sum += play[endTime];

            if(max < sum){
                max = sum;
                answerTime = startTime + 1;
            }

            startTime++;
            endTime++;
        }

        return secondToStr(answerTime);
    }

    private static String secondToStr(int answerTime) {
        int hour = answerTime / 3600;
        answerTime %= 3600;
        int min = answerTime / 60;
        int sec = answerTime % 60;

        String h = hour > 9 ? String.valueOf(hour) : "0" + hour;
        String m = min > 9 ? String.valueOf(min) : "0" + min;
        String s = sec > 9 ? String.valueOf(sec) : "0" + sec;

        return h + ":" + m + ":" + s;

    }

    private static int strToSecond(String str) {
        String[] time = str.split(":");

        int second = Integer.parseInt(time[2]);
        second += Integer.parseInt(time[0])*60*60;
        second += Integer.parseInt(time[1])*60;

        return second;
    }   

    public static void main(String[] args) {
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
        System.out.println(solution("02:03:55", "00:14:15", logs));
    }
}
