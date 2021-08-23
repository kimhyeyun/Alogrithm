public class 가장큰정사각형찾기 {
    public static int solution(int [][]board)
    {
        int[][] boardCpy = new int[board.length+1][board[0].length+1];

        for(int i = 0; i < board.length; i++){
            for(int j = 0 ; j < board[0].length; j++){
                boardCpy[i+1][j+1] = board[i][j];
            }
        }

        int answer = 0;

        for(int i = 1; i < boardCpy.length; i++){
            for(int j = 1; j < boardCpy[0].length; j++){
                if(boardCpy[i][j] != 0){
                    boardCpy[i][j] = Math.min(boardCpy[i-1][j], Math.min(boardCpy[i][j-1], boardCpy[i-1][j-1])) + 1;
                    answer = Math.max(boardCpy[i][j], answer);
                }
            }
        }

        return answer*answer;
    }

    public static void main(String[] args) {
        int[][] b = {{0,1,1,1},{1,1,1,1},{1,1,1,1},{0,0,1,0}};

        System.out.println(solution(b));
    }
}
