import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1535_안녕 {
    static int N;
    static int[] energies;
    static int[] joy;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());
        energies = new int[N+1];
        joy = new int[N+1];

        stringTokenizer = new StringTokenizer(br.readLine());
        for(int i = 1 ; i < N+1 ; i++){
            energies[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        stringTokenizer = new StringTokenizer(br.readLine());
        for(int i = 1 ; i < N+1 ; i++){
            joy[i] = Integer.parseInt(stringTokenizer.nextToken());
        }


        System.out.println(solve(0, 100));

     
    }

    private static int solve(int idx, int energy) {
        if(idx == N)
            return 0;

        int hi = 0;
        int noHi = 0;
    
        if(energy - energies[idx+1] > 0){
            // idx 번째 사람에게 인사
            hi = solve(idx+1, energy-energies[idx+1]) + joy[idx+1];
        }

        // 인사안함
        noHi = solve(idx+1, energy);

        return Math.max(hi, noHi);
        
    }
}
