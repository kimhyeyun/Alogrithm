public class 행렬의곱셈 {
    public static int[][] solution(int[][] arr1, int[][] arr2) {
        int rowLen = arr1.length;
        int colLen = arr2[0].length;

        int[][] answer = new int[rowLen][colLen];

        for(int i = 0 ; i < rowLen; i++){
            for(int j = 0; j < colLen; j++){
                for(int k = 0; k < arr1[0].length; k++){
                    answer[i][j] += (arr1[i][k] * arr2[k][j]);
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] arr1 = {{1,4}, {3,2}, {4,1}};
        int[][] arr2 = {{3,3}, {3,3}};

        int[][] ans = solution(arr1, arr2);

        for(int[] arr : ans){
            for(int a : arr){
                System.out.print(a +" ");
            }
            System.out.println();
        }   
     }
}
