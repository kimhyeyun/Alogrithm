public class 프렌즈4블록 {

    static char[][] block;
    public static int solution(int m, int n, String[] board) {
        int answer = 0;

        block = new char[m][n];

        for(int i = 0 ; i < m; i++){
            block[i] = board[i].toCharArray();
        }

        while(true){
            int cnt = checkBlock(m,n);
            if(cnt == 0)
                break;
            answer += cnt;
            
            dropBlock(m, n);
        }

        return answer;
    }

    private static void dropBlock(int m, int n) {
        for(int col = 0; col < n; col++){
            for(int row = m-1; row >= 0; row--){
                if(block[row][col] == '.'){
                    for(int nRow = row-1; nRow >= 0; nRow--){
                        if(block[nRow][col] != '.'){
                            block[row][col] = block[nRow][col];
                            block[nRow][col] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }

    private static int checkBlock(int m, int n) {
        boolean[][] isVisited = new boolean[m][n];
        int cnt = 0;

        for(int i = 0; i < m-1; i++){
            for(int j = 0; j < n-1 ;j++){
                if(block[i][j] == '.')
                    continue;
                
                checkFour(i, j, isVisited);
            }
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(isVisited[i][j]){
                    cnt++;
                    block[i][j] = '.';
                }
            }
        }

        return cnt;
    }

    private static void checkFour(int i, int j, boolean[][] isVisited) {

        char b = block[i][j];
        
        for(int row = i; row < i+2; row++){
            for(int col = j; col < j+2; col++){
                if(block[row][col] != b)
                    return;
            }
        }

        for(int row = i; row < i+2; row++){
            for(int col = j; col < j+2; col++){
                isVisited[row][col] = true;
            }
        }
    }

    public static void main(String[] args) {
        String board[] = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        System.out.println(solution(6, 6, board));
    }
}
