public class Programmers_양궁대회 {
    static int[] lion, ans;
    static int max = -1;
    public static int[] solution(int n, int[] info) {
        lion = new int[11];
        ans = new int[11];
        DFS(info, 0, n);

        if(max == -1) return new int[]{-1};

        return ans;
    }

    private static void DFS(int[] info, int cnt, int n) {
        if (cnt == n) {
            int apachPoint = 0, linePoint = 0;
            for (int i = 0; i < 11; i++) {
                if(info[i] == 0 && lion[i] == 0 ) continue;
                if (info[i] < lion[i]) linePoint += 10 - i;
                else apachPoint += 10 - i;

            }

            if (linePoint > apachPoint) {
                if (max <= linePoint - apachPoint) {
                    max = linePoint - apachPoint;
                    System.arraycopy(lion, 0, ans, 0, 11);
                }
            }

            return;
        }

        for (int i = 0; i < 11 && lion[i] <= info[i]; i++) {
            lion[i] += 1;
            DFS(info, cnt + 1, n);
            lion[i] -= 1;
        }
    }

    public static void main(String[] args) {
        int[] info = {0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3};
        int[] ans = solution(10, info);
        for (int a : ans) {
            System.out.println(a);
        }
        System.out.println(max);
    }
}
