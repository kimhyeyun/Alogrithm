public class L1_행렬의_덧셈 {
    public static int[][] solution(int[][] arr1, int[][] arr2) {
        int rowLen = arr1.length, colLen = arr1[0].length;
        int[][] answer = new int[rowLen][colLen];

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                answer[i][j] = arr1[i][j] + arr2[i][j];
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] arr1 = {{1, 2}, {2, 3}};
        int[][] arr2 = {{3, 4}, {5, 6}};
        int[][] answer = solution(arr1, arr2);
        for (int[] ans : answer) {
            for (int a : ans) {
                System.out.print(a);
            }
            System.out.println();
        }
    }
}
