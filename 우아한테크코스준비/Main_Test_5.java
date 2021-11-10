public class Main_Test_5 {
    public static int[][] solution(int rows, int columns) {
        int[][] answer = new int[rows][columns];

        int num = 1;
        int zeroCnt = rows*columns-1;
        int r = 0, c = 0;
        answer[r][c] = 1;

        while(true){
            if(num%2 == 0) // 짝수
                r = r+1 == rows ? 0 : r+1;
            else // 홀수
                c = c+1 == columns ? 0 : c+1;

            num+=1;
            if(answer[r][c] != 0 && answer[r][c]%2 == num%2 || zeroCnt == 0)
                break;
            
            if(answer[r][c] == 0)
                zeroCnt--;
            answer[r][c] = num;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] answer = solution(3, 4);

        for(int[] ans : answer){
            for(int a : ans)
                System.out.print(a + " ");
            System.out.println();
        }
    }
}
