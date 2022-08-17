public class L1_비밀지도 {
    public static String[] solution(int n, int[] arr1, int[] arr2) {
        int[][] map1 = new int[n][n];
        int[][] map2 = new int[n][n];

        for (int i = 0; i < arr1.length; i++) {
            String s1 = Integer.toString(arr1[i], 2);
            String s2 = Integer.toString(arr2[i], 2);
            while (s1.length() < n) s1 = "0" + s1;
            while (s2.length() < n) s2 = "0" + s2;
            for (int j = 0; j < n; j++) {
                map1[i][j] = (int) s1.charAt(j) - '0';
                map2[i][j] = (int) s2.charAt(j) - '0';
            }
        }

        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            String ans = "";
            for (int j = 0; j < n; j++) {
                if(map1[i][j] == 0 && map2[i][j] == 0) ans += " ";
                else ans += "#";
            }
            answer[i] = ans;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] arr1 = {9, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};

        String[] ans = solution(5, arr1, arr2);
        for (String a : ans) {
            System.out.println(a);
        }
    }
}
