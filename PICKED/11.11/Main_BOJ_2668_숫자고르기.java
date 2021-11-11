import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_BOJ_2668_숫자고르기 {
    static int N;
    static int[] nums;
    static boolean[] isVisited;
    static List<Integer> ans;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nums = new int[N+1];
        isVisited = new boolean[N+1];
        ans = new ArrayList<>();

        for(int i = 1; i < N+1 ; i++){
            nums[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 1; i < N+1 ; i++){
            isVisited[i] = true;
            DFS(i, i);
            isVisited[i] = false;
        }

        Collections.sort(ans);
        System.out.println(ans.size());
        for(int a : ans)
            System.out.println(a);
    }

    private static void DFS(int n, int cycleEnd) {
        if(nums[n] == cycleEnd) ans.add(n);

        if(!isVisited[nums[n]]){
            isVisited[nums[n]] = true;
            DFS(nums[n], cycleEnd);
            isVisited[nums[n]] = false;
        }
    }
    
}
