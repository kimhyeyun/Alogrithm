import java.util.Arrays;

public class task_2 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,3,4,2,5}));
    }
    public static int solution(int[] A) {
        int[] tmp = new int[A.length];
        System.arraycopy(A, 0, tmp, 0, A.length);

        Arrays.sort(tmp);

        int N = tmp[tmp.length - 1];

        boolean[] isShined = new boolean[N + 1];
        boolean[] isTurnOn = new boolean[N + 1];

        int ans = 0;
        for (int a : A) {
            isTurnOn[a] = true;
            boolean flag = false;

            for (int i = a - 1; i > 0; i--) {
                if(!isShined[i]) break;
                flag = true;
            }
            if(flag || a == 1) isShined[a] = true;

            if (isShined[a]) {
                for (int i = a + 1; i < N + 1; i++) {
                    if(isTurnOn[i]) isShined[i] = true;
                    else break;
                }
            }

            boolean flag2 = true;
            for (int i = 0; i < N + 1; i++) {
                if (isShined[i] != isTurnOn[i]) {
                    flag2 = false;
                    break;
                }
            }

            if(flag2) ans += 1;
        }

        return ans;
    }
}
