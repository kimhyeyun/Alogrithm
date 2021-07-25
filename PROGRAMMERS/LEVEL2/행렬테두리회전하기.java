public class 행렬테두리회전하기 {

    public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        int[][] matrix = new int[rows][columns];

        int a = 1;
        for(int i = 0;i < rows; i++){
            for(int j = 0;j < columns; j++){
                matrix[i][j] = a++;
            }
        }

        
        for(int i = 0;i < queries.length;i++){
            int start_x = queries[i][0]-1;
            int start_y = queries[i][1]-1;
            int end_x = queries[i][2]-1;
            int end_y = queries[i][3]-1;

            int tmp = matrix[start_x][start_y];
            int min = tmp;

            for(int j = start_x+1; j <= end_x;j++){
                matrix[j-1][start_y] = matrix[j][start_y];
                min = matrix[j][start_y] < min ? matrix[j][start_y] : min;
            }

            for(int j = start_y+1; j <= end_y; j++){
                matrix[end_x][j-1] = matrix[end_x][j];
                min = matrix[end_x][j] < min ? matrix[end_x][j]: min;
            }

            for(int j = end_x-1; j >= start_x; j--){
                matrix[j+1][end_y] = matrix[j][end_y];
                min = matrix[j][end_y] < min ? matrix[j][end_y] : min;
            }

            for(int j = end_y-1; j > start_y; j--){
                matrix[start_x][j+1] = matrix[start_x][j];
                min = matrix[start_x][j] < min ? matrix[start_x][j] : min;
            }

            matrix[start_x][start_y+1] = tmp;

            answer[i] = min;
        }

        return answer;
    }


    public static void main(String[] args) {
        int[][] q = {{1,1,2,2},{1,2,2,3},{2,1,3,2},{2,2,3,3}};
        int ans[] = solution(3,3, q);

        for(int a : ans){
            System.out.println(a);
        }

    }
}
