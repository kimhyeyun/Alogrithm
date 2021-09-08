public class 추석트래픽 {
    public static int solution(String[] lines) {
        int answer = 0;

        int[] startTimes = new int[lines.length];
        int[] endTimes = new int[lines.length];

        getTimes(lines, startTimes, endTimes);

        for(int i = 0 ; i < lines.length ; i++){
            int cnt = 0;
            int startOfSection = startTimes[i];
            int endOfSection = startOfSection + 1000 - 1;

            for(int j = 0 ; j < lines.length; j++){
                if(startOfSection <= startTimes[j] && startTimes[j] <= endOfSection)
                    cnt++;
                else if(startOfSection <= endTimes[j] && endTimes[j] <= endOfSection)
                    cnt++;
                else if(startTimes[j] <= startOfSection && endOfSection <= endTimes[j])
                    cnt++;
            }

            answer = Math.max(cnt, answer);
        }

        for(int i = 0 ; i < lines.length ; i++){
            int cnt = 0;
            int startOfSection = endTimes[i];
            int endOfSection = startOfSection + 1000 - 1;

            for(int j = 0; j < lines.length; j++){
                if(startOfSection <= startTimes[j] && startTimes[j] <= endOfSection)
                    cnt++;
                else if(startOfSection <= endTimes[j] && endTimes[j] <= endOfSection)
                    cnt++;
                else if(startTimes[j] <= startOfSection && endOfSection <= endTimes[j])
                    cnt++;
            }

            answer = Math.max(answer, cnt);
        }

        return answer;
    }

    private static void getTimes(String[] lines, int[] startTimes, int[] endTimes) {
        for(int i = 0 ; i < lines.length ; i++){
            String[] log = lines[i].split(" ");
            String[] resTime = log[1].split(":");
            int processingTime = (int)(Double.parseDouble(log[2].substring(0, log[2].length()-1)) * 1000);

            int startTime = 0;
            int endTime = 0;

            endTime += Integer.parseInt(resTime[0])*60*60*1000;
            endTime += Integer.parseInt(resTime[1])*60*1000;
            endTime += (int)(Double.parseDouble(resTime[2]) * 1000);

            startTime = endTime - processingTime + 1;

            startTimes[i] = startTime;
            endTimes[i] = endTime;
        }
    }

    public static void main(String[] args) {
        String[] log = {"2016-09-15 20:59:57.421 0.351s",
        "2016-09-15 20:59:58.233 1.181s",
        "2016-09-15 20:59:58.299 0.8s",
        "2016-09-15 20:59:58.688 1.041s",
        "2016-09-15 20:59:59.591 1.412s",
        "2016-09-15 21:00:00.464 1.466s",
        "2016-09-15 21:00:00.741 1.581s",
        "2016-09-15 21:00:00.748 2.31s",
        "2016-09-15 21:00:00.966 0.381s",
        "2016-09-15 21:00:02.066 2.62s"};

        System.out.println(solution(log));
    }
}
