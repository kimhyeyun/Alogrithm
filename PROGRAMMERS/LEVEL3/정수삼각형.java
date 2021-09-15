public class 정수삼각형 {

    public static int solution(int[][] triangle) {
        int lastIdx = triangle.length - 1;

        for(int i = lastIdx - 1; i >= 0 ; i--){
            for(int j = 0 ; j < triangle[i].length ; j++){
                triangle[i][j] += Math.max(triangle[i+1][j], triangle[i+1][j+1]);
            }
        }

        return triangle[0][0];
    }

    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4} , {4, 5, 2, 6, 5}};

        System.out.println(solution(triangle));
    }
}
