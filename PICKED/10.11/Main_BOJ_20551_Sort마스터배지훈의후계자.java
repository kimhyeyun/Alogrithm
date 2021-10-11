import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_BOJ_20551_Sort마스터배지훈의후계자 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());  

        // 시간초과
        /* LinkedList<Integer> arrA = new LinkedList<>();
        for(int i = 0 ; i < N ; i++){
            arrA.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(arrA);

        while(M-- > 0){
            int x = Integer.parseInt(br.readLine());
            sb.append(arrA.indexOf(x)).append("\n");
        }

        System.out.println(sb); */

        int[] arrA = new int[N];
        for(int i = 0 ; i < N ; i++){
            arrA[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arrA);

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0 ; i < N ; i++){
            if(!map.containsKey(arrA[i])){
                map.put(arrA[i], i);
            }
        }

        while(M-- > 0){
            int x = Integer.parseInt(br.readLine());
            if(map.containsKey(x))
                sb.append(map.get(x)).append("\n");
            else
                sb.append(-1).append("\n");
        }

        System.out.println(sb);
        
    }
}
