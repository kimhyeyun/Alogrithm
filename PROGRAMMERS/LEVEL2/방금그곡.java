import java.util.Arrays;
import java.util.Comparator;

public class 방금그곡 {
    static String[] sharp = {"C#", "D#", "F#", "G#", "A#"};
    static String[] lower = {"c", "d", "f", "g", "a"};

    public static String solution(String m, String[] musicinfos) {
        String melody = refine(m);
        String[][] infos = refine(musicinfos);
        
        Arrays.sort(infos, new Comparator<String[]>(){

            @Override
            public int compare(String[] music1, String[] music2) {
                // TODO Auto-generated method stub

                int runningTimeOfMusic1 = Integer.parseInt(music1[0]);
                int runningTimeOfMusic2 = Integer.parseInt(music2[0]);

                return -(runningTimeOfMusic1 - runningTimeOfMusic2);
            }
            
        });

        for(int i = 0; i < infos.length; i++){
            if(infos[i][2].contains(melody)){
                return infos[i][1];
            }
        }

        return "(None)";
    }

    private static String[][] refine(String[] musicinfos) {
        String infos[][] = new String[musicinfos.length][3];

        for(int i = 0; i < musicinfos.length; i++){
            String[] info = musicinfos[i].split(",");

            String[] start = info[0].split(":");
            String[] end = info[1].split(":");
            String title = info[2];
            String musicCode = info[3];

            String music = "";
            
            for(int j = 0; j < sharp.length; j++){
                musicCode = musicCode.replaceAll(sharp[j], lower[j]);
            }

            int musicLength = musicCode.length();
            int runningTime = getRunningTime(start, end);

            int codeIdx = 0;

            for(int j = 0; j < runningTime; j++){
                music += musicCode.charAt(codeIdx);
                codeIdx = (codeIdx + 1) % musicLength;
            }

            infos[i][0] = runningTime + "";
            infos[i][1] = title;
            infos[i][2] = music;
        }

        return infos;
    }

    private static int getRunningTime(String[] start, String[] end) {
        int runningTime = 0;

        int startHour = Integer.parseInt(start[0]);
        int startMinute = Integer.parseInt(start[1]);
        int endHour = Integer.parseInt(end[0]);
        int endMinute = Integer.parseInt(end[1]);

        runningTime = (endHour - startHour) * 60 + (endMinute - startMinute);

        return runningTime;

    }

    private static String refine(String m) {
        String result = m;

        for(int i = 0; i < sharp.length; i++){
            result =  result.replaceAll(sharp[i], lower[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        String[] m = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};

        System.out.println(solution("ABCDEFG", m));
    }

    
}
