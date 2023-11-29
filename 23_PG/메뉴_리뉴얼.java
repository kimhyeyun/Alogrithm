import java.util.*;

public class 메뉴_리뉴얼 {

    private List<String> menuList;
    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        Map<String, Integer> countOfCourses = new HashMap<>();
        menuList = new ArrayList<>();

        for (String order : orders) {
            char[] chars = order.toCharArray();
            Arrays.sort(chars);

            for (int i = 0; i < order.length(); i++) {
                for (int j = 0; j < course.length; j++) {
                    setCourses(chars, i, course[j], String.valueOf(chars[i]));
                }
            }
        }

        for (String menu : menuList) {
            countOfCourses.put(menu, countOfCourses.getOrDefault(menu, 0) + 1);
        }

        List<String> keySet = new ArrayList<>(countOfCourses.keySet());
        Collections.sort(keySet, (((o1, o2) -> (countOfCourses.get(o2).compareTo(countOfCourses.get(o1))))));

        for (int c : course) {
            int max = -1;

            for (String key : keySet) {
                if(countOfCourses.get(key) < 2 || key.length() != c) continue;
                if(countOfCourses.get(key) < max) continue;

                max = countOfCourses.get(key);
                answer.add(key);
            }
        }

        Collections.sort(answer);

        return answer.toArray(answer.toArray(new String[answer.size()]));
    }

    private void setCourses(char[] order, int index, int course, String menu) {
        if (menu.length() == course) {
            menuList.add(menu);
            return;
        }

        for (int i = index + 1; i < order.length; i++) {
            setCourses(order, i, course, menu + order[i]);
        }
    }
}
