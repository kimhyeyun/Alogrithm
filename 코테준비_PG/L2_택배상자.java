import java.util.Stack;

public class L2_택배상자 {
    public static int solution(int[] order) {
        int answer = 0;

        int N = order.length;
        Stack<Integer> mainBelt = new Stack<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = N; i > 0; i--) mainBelt.add(i);

        for (int idx = 0; idx < order.length; idx++) {
            boolean isEnd = false;
            if (!mainBelt.isEmpty() && mainBelt.peek() == order[idx]) {
                answer += 1;
                mainBelt.pop();
            } else if (!stack.isEmpty() && stack.peek() == order[idx]) {
                answer += 1;
                stack.pop();
            }else{
                while(true){
                    if(mainBelt.isEmpty()) break;
                    if(mainBelt.peek() == order[idx]) break;

                    stack.add(mainBelt.pop());
                }
                if(mainBelt.isEmpty()){
                    isEnd = true;
                    break;
                }
                if(mainBelt.peek() == order[idx]){
                    answer += 1;
                    mainBelt.pop();
                }
            }
            if(isEnd) break;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] order = {5,4,3,2,1};
        System.out.println(solution(order));

    }
}
