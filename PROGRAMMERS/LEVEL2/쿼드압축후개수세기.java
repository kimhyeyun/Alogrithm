public class 쿼드압축후개수세기 {

    static int[] answer;
    public static int[] solution(int[][] arr) {
        answer = new int[2];

        int len = arr.length;

        div(0, 0, len, arr);

        return answer;
    }
    private static void div(int x, int y, int len, int[][] arr) {
        boolean isZero = true;
        boolean isOne = true;

        for(int i = x; i < x+len; i++){
            for(int j = y; j < y+len; j++){
                if(arr[i][j] == 1)
                    isZero = false;
                else
                    isOne = false;
            }
        }

        if(isZero){
            answer[0]++;
            return;
        }

        if(isOne){
            answer[1]++;
            return;
        }

        div(x, y, len/2, arr);
        div(x+len/2, y, len/2, arr);
        div(x, y+len/2, len/2, arr);
        div(x+len/2, y+len/2, len/2, arr);

    }
    public static void main(String[] args) {
        int[][] a = {{1,1,1,1,1,1,1,1},{0,1,1,1,1,1,1,1},{0,0,0,0,1,1,1,1},{0,1,0,0,1,1,1,1},{0,0,0,0,0,0,1,1},{0,0,0,0,0,0,0,1},{0,0,0,0,1,0,0,1},{0,0,0,0,1,1,1,1}};
        int[] ans = solution(a);

        for(int an : ans)
            System.out.println(an);
    }
}
