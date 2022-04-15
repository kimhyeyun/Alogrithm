import java.util.Stack;

public class 표_편집 {
    public static String solution(int n, int k, String[] cmd){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("O");
        }
        int[] pre = new int[n];
        int[] next = new int[n];
        int cur = k;

        for (int i = 0; i < n; i++) {
            pre[i] = i - 1;
            next[i] = i + 1;

            if(i == n-1) next[i] = -1;
        }

        Stack<int[]> delStack = new Stack<>();
        for (String c : cmd) {
            String[] str = c.split(" ");
            if(str[0].equals("U")){
                int x = Integer.parseInt(str[1]);
                while(x-- > 0) cur = pre[cur];
            } else if (str[0].equals("D")) {
                int x = Integer.parseInt(str[1]);
                while(x-- > 0) cur = next[cur];
            } else if (str[0].equals("C")) {
                delStack.push(new int[]{pre[cur], cur, next[cur]});

                if(pre[cur] != -1) next[pre[cur]] = next[cur];
                if(next[cur] != -1) pre[next[cur]] = pre[cur];

                sb.setCharAt(cur, 'X');

                if(next[cur] != -1) cur = next[cur];
                else cur = pre[cur];

            } else {
                if(delStack.isEmpty()) continue;

                int[] recovery = delStack.pop();

                if(recovery[0] != -1) next[recovery[0]] = recovery[1];
                if(recovery[2] != -1) pre[recovery[2]] = recovery[1];

                sb.setCharAt(recovery[1], 'O');
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
        System.out.println(solution(8, 2, cmd));

    }
}
