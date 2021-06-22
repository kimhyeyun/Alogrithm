import java.util.Stack;

public class 크레인인형뽑기 {
    public static void main(String[] args) {
        int[][] b = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
        int[] m = {1,5,3,5,1,2,1,4};

        System.out.println(solution(b, m));
    }

    public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stk = new Stack<>();

        for(int i=0;i<moves.length;i++){
            int c = moves[i]-1;
            for(int r = 0;r<board[c].length;r++){
                if(board[r][c] != 0){
                    if(!stk.empty()){
                        int top = stk.peek();
                        if(top == board[r][c]){
                            answer+=2;
                            stk.pop();
                        }
                        else{
                            stk.push(board[r][c]);
                        }
                        board[r][c] = 0;
                    }
                    else{
                        stk.push(board[r][c]);
                        board[r][c] = 0;
                    }
                    break;
                }
                
            }
        }

        return answer;
    }
}
