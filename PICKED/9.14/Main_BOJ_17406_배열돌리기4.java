import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_BOJ_17406_배열돌리기4 {

    static int N, M, K;
    static int min = Integer.MAX_VALUE;
    static int[][] arr, rcs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        arr = new int[N][M];
        rcs = new int[K][3];

        for(int i = 0 ; i < N ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                arr[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        for(int i = 0; i < K ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            rcs[i][0] = Integer.parseInt(stringTokenizer.nextToken());
            rcs[i][1] = Integer.parseInt(stringTokenizer.nextToken());
            rcs[i][2] = Integer.parseInt(stringTokenizer.nextToken());
        }
        
        makePerm(new boolean[K], new LinkedList<Integer>());

        System.out.println(min);

    }

    private static void makePerm(boolean[] isVisited, LinkedList<Integer> listRotate) {
        if(listRotate.size() == K){
            int[][] arrCopy = copyArr();

            // 뽑힌 순서대로 돌리기
            for(int l : listRotate){
                int r = rcs[l][0];
                int c = rcs[l][1];
                int s = rcs[l][2];

                calPoint(arrCopy, r, c, s);
            }

            min = Math.min(min, calRow(arrCopy));
        }

        for(int i = 0; i < K ; i++){
            if(!isVisited[i]){
                isVisited[i] = true;
                listRotate.add(i);

                makePerm(isVisited, listRotate);

                isVisited[i] = false;
                listRotate.removeLast();
            }
        }
    }

    private static int calRow(int[][] arrCopy) {
        int minSum = Integer.MAX_VALUE;
        for(int i = 0 ; i < N ; i++){
            int sum = 0;
            for(int j = 0 ; j < M ; j++){
                sum += arrCopy[i][j];
            }
            System.out.println(sum);
            minSum = Math.min(minSum, sum);
        }

        return minSum;
    }

    private static void calPoint(int[][] arrCpy, int r, int c, int s) {
        for(int i = 0 ; i < s ; i++){
            int r1 = r - s + i - 1;
            int c1 = c - s + i - 1;
            int r2 = r + s - i - 1;
            int c2 = c + s - i - 1;

            rotateArr(arrCpy, r1, c1, r2, c2);
        }
    }

    private static void rotateArr(int[][] arrCpy, int r1, int c1, int r2, int c2) {
        int tmp = arrCpy[r1][c1];

        // 왼쪽 열
        for(int r = r1+1 ; r <= r2 ; r++){
            arrCpy[r-1][c1] = arrCpy[r][c1];
        }
        
        // 맨 밑 행
        for(int c = c1+1 ; c <= c2 ; c++){
            arrCpy[r2][c-1] = arrCpy[r2][c];
        }

        // 오른쪽 열
        for(int r = r2-1; r >= r1 ; r--){
            arrCpy[r+1][c2] = arrCpy[r][c2];
        }

        // 맨 윗 행
        for(int c = c2-1 ; c > c1 ; c--){
            arrCpy[r1][c+1] = arrCpy[r1][c];
        }

        arrCpy[r1][c1+1] = tmp;
    }

    private static int[][] copyArr() {
        int[][] tmp = new int[N][M];
        for(int i = 0 ; i < N ; i++)
            tmp[i] = Arrays.copyOf(arr[i], M);

        return tmp;
    }
    
}
