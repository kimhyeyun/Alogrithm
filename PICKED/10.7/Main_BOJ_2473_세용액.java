import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main_BOJ_2473_세용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer;

        int N = Integer.parseInt(br.readLine());
        stringTokenizer = new StringTokenizer(br.readLine());

        long[] valueOfSolution = new long[N];
        for(int i = 0 ; i < N ; i++){
            valueOfSolution[i] = Long.parseLong(stringTokenizer.nextToken());
        }

        Arrays.sort(valueOfSolution);
        long minOfValue = Long.MAX_VALUE;
        long[] valueOfAnswer = new long[3];
        for(int i = 0 ; i < N-2 ; i++){
            int first = i;
            int second = i+1;
            int third = N-1;

            while(second < third){
                long valueOfSum = valueOfSolution[first] + valueOfSolution[second] + valueOfSolution[third];
                long absOfvalueOfSum = Math.abs(valueOfSum);

                if(absOfvalueOfSum < minOfValue){
                    minOfValue = absOfvalueOfSum;
                    valueOfAnswer = new long[]{ valueOfSolution[first], valueOfSolution[second], valueOfSolution[third] };
                }

                if(valueOfSum < 0){
                    second++;
                }
                else if(valueOfSum > 0){
                    third--;
                }
                else{
                    for(long v : valueOfAnswer)
                        sb.append(v + " ");

                    System.out.println(sb);
                    return;
                }

            }
        }
        for(long v : valueOfAnswer)
        sb.append(v + " ");

        System.out.println(sb);
        
    }
}
