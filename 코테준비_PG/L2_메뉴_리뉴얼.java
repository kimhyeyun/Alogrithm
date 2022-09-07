import java.util.*;

public class L2_메뉴_리뉴얼 {
    static List<String> menuList;
    public static String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        Map<String, Integer> courses = new HashMap<>();
        menuList = new ArrayList<>();

        for (String o : orders) {
            char[] order = o.toCharArray();
            Arrays.sort(order);

            for (int i = 0; i < order.length; i++) {
                for (int j = 0; j < course.length; j++) {
                    DFS(order, i, course[j], String.valueOf(order[i]));
                }
            }
        }

        for (String menu : menuList) {
            courses.put(menu, courses.getOrDefault(menu, 0) + 1);
        }

        List<String> keySet = new ArrayList<>(courses.keySet());
        Collections.sort(keySet, ((o1, o2) -> (courses.get(o2).compareTo(courses.get(o1)))));

        for (int c : course) {
            int max = 0;
            for (String key : keySet) {
                if (courses.get(key) < 2 || key.length() != c) continue;
                if (courses.get(key) < max) continue;

                max = courses.get(key);
                answer.add(key);
            }
        }


        Collections.sort(answer);

        return answer.toArray(new String[answer.size()]);
    }

    private static void DFS(char[] order, int idx, int course, String menu) {
        if (menu.length() == course) {
            menuList.add(menu);
            return;
        }
        for (int i = idx + 1; i < order.length; i++) {
            DFS(order, i, course, menu + order[i]);
        }
    }

    public static void main(String[] args) {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
//        String[] orders = {"XYZ", "XWY", "WXA"};
        int[] course = {2, 3, 4};


        String[] result = solution(orders, course);

        for(String r : result) System.out.println(r);
    }
}
