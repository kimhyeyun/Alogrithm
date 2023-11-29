public class task3{
    static int ans = Integer.MIN_VALUE;
    static int[] num;
    static boolean[] isVisited;
    public int solution(int N, int[] A, int[] B) {
        num = new int[N + 1];
        isVisited = new boolean[N + 1];

        setVal(N, 1, A, B);

        return ans;
    }

    private void setVal(int N, int idx, int[] A, int[] B) {
        if (idx == N + 1) {
            ans = Math.max(ans,calSum(A, B));
            return;
        }

        for (int i = 1; i <= N; i++) {
            if(isVisited[i]) continue;

            isVisited[i] = true;
            num[idx] = i;
            setVal(N, idx + 1, A, B);
            isVisited[i] = false;
        }
    }

    private int calSum(int[] a, int[] b) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += num[a[i]] + num[b[i]];
        }

        return sum;
    }
}
