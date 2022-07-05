import java.util.*;

public class 메뉴_리뉴얼 {
    static List<String> combinations = new ArrayList<>();
    public static String[] solution(String[] orders, int[] course) {
        for (String order : orders) {
            char[] orderArr = order.toCharArray();
            Arrays.sort(orderArr);

            for (int i = 0; i < orderArr.length; i++) {
                for (int j = 0; j < course.length; j++) {
                    DFS(orderArr, i, course[j], String.valueOf(orderArr[i]));
                }
            }
        }

        HashMap<String, Integer> orderCnt = new HashMap<>();
        for (String combination : combinations) {
            orderCnt.put(combination, orderCnt.getOrDefault(combination, 0) + 1);
        }

        List<String> keySet = new ArrayList<>(orderCnt.keySet());
        Collections.sort(keySet, ((o1, o2) -> (orderCnt.get(o2).compareTo(orderCnt.get(o1)))));

        List<String> ansList = new ArrayList<>();
        for (int c : course) {
            int max = 0;

            for (String key : keySet) {
                if (orderCnt.get(key) >= 2 && key.length() == c) {
                    if (orderCnt.get(key) >= max) {
                        max = orderCnt.get(key);
                        ansList.add(key);
                    }
                }
            }
        }

        Collections.sort(ansList);

        return ansList.toArray(new String[ansList.size()]);
    }

    private static void DFS(char[] orderArr, int idx, int course, String menu) {
        if (menu.length() == course) {
            combinations.add(menu);
            return;
        }

        for (int i = idx + 1; i < orderArr.length; i++) {
            DFS(orderArr, i, course, menu + orderArr[i]);
        }
    }

    public static void main(String[] args) {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};

        String[] ans = solution(orders, course);
        for (String a : ans) {
            System.out.println(a);
        }
    }
}
