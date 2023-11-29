import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 방금그곡 {

    String[] sharps = {"C#", "D#", "F#", "G#", "A#"};
    String[] lowers = {"c", "d", "f", "g", "a"};

    public String solution(String m, String[] musicinfos) {
        for (int i = 0; i < sharps.length; i++) {
            m = m.replaceAll(sharps[i], lowers[i]);
        }

        String[][] infos = refine(musicinfos);

        Arrays.sort(infos, (o1, o2) -> Integer.parseInt(o2[0]) - Integer.parseInt(o1[0]));

        for (int i = 0; i < infos.length; i++) {
            if(infos[i][2].contains(m)) return infos[i][1];
        }
        return "(None)";
    }

    private String[][] refine(String[] musicinfos) {
        String[][] infos = new String[musicinfos.length][3];

        for (int i = 0;i<musicinfos.length;i++) {
            String[] split = musicinfos[i].split(",");

            String[] start = split[0].split(":");
            String[] end = split[1].split(":");
            String title = split[2];
            String code = split[3];

            String melody = "";

            for (int j = 0; j < sharps.length; j++) {
                code = code.replaceAll(sharps[j], lowers[j]);
            }

            int runningTime = (Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1])) - (Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]));
            int index = 0;

            for (int j = 0; j < runningTime; j++) {
                melody += code.charAt(index);
                index = (index + 1) % code.length();
            }

            infos[i][0] = String.valueOf(runningTime);
            infos[i][1] = title;
            infos[i][2] = melody;

        }

        return infos;
    }

    @Test
    void test() {
        assertEquals(solution("ABCDEFG", new String[]{"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"}), "HELLO");
        assertEquals(solution("CC#BCC#BCC#BCC#B", new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}), "FOO");
    }

}
