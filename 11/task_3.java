import java.util.*;

public class task_3 {
    public static void main(String[] args) {
        System.out.println(solution("photo.jpg, Warsaw, 2013-09-05 14:08:15\n" +
                "john.png, London, 2015-06-20 15:13:22\n" +
                "myFriends.png, Warsaw, 2013-09-05 14:07:13\n" +
                "Eiffel.jpg, Paris, 2015-07-23 08:03:02\n" +
                "pisatower.jpg, Paris, 2015-07-22 23:59:59\n" +
                "BOB.jpg, London, 2015-08-05 00:02:03\n" +
                "notredame.png, Paris, 2015-09-01 12:00:00\n" +
                "me.jpg, Warsaw, 2013-09-06 15:40:22\n" +
                "a.png, Warsaw, 2016-02-13 13:33:50\n" +
                "b.jpg, Warsaw, 2016-01-02 15:12:22\n" +
                "c.jpg, Warsaw, 2016-01-02 14:34:30\n" +
                "d.jpg, Warsaw, 2016-01-02 15:15:01\n" +
                "e.png, Warsaw, 2016-01-02 09:49:09\n" +
                "f.png, Warsaw, 2016-01-02 10:55:32\n" +
                "g.jpg, Warsaw, 2016-02-29 22:13:11"));
    }
    public static String solution(String S) {
        StringBuilder sb = new StringBuilder();
        String[] s = S.split("\n");

        String[] ans = new String[s.length];

        Map<String, List<Integer>> cityMap = new HashMap<>();
        String[][] sArr = new String[s.length][3];
        for (int i = 0; i < s.length; i++) {
            sArr[i] = s[i].split("[.]|, ");

            if (cityMap.containsKey(sArr[i][2])) {
                List<Integer> list = cityMap.get(sArr[i][2]);
                list.add(i);
                cityMap.put(sArr[i][2], list);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                cityMap.put(sArr[i][2], list);
            }
        }

        for (String key : cityMap.keySet()) {
            List<Integer> list = cityMap.get(key);

            Map<String, Integer> timeList = new HashMap<>();
            for (int l : list) {
                timeList.put(sArr[l][3], l);
            }

            List<String> keySet = new ArrayList<>(timeList.keySet());
            Collections.sort(keySet);

            int cnt = 1;
            if (keySet.size() > 9) {
                for (String k : keySet) {
                    if(cnt > 9) ans[timeList.get(k)] = key + cnt + "." + sArr[timeList.get(k)][1];
                    else ans[timeList.get(k)] = key + "0" + cnt + "." + sArr[timeList.get(k)][1];
                    cnt += 1;
                }
            } else {
                for (String k : keySet) {
                    ans[timeList.get(k)] = key + cnt + "." + sArr[timeList.get(k)][1];
                    cnt += 1;
                }
            }
        }


        for (String an : ans) {
            sb.append(an + "\n");
        }

        return sb.toString();
    }
}
