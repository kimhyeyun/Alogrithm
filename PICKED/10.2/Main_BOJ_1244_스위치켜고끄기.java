import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1244_스위치켜고끄기 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer;

        int N = Integer.parseInt(br.readLine());
        stringTokenizer = new StringTokenizer(br.readLine());
        int[] switches = new int[N+1];

        for(int i = 1; i < N+1 ; i++){
            int x = Integer.parseInt(stringTokenizer.nextToken());
            switches[i] = x;
        }

        int studentNum = Integer.parseInt(br.readLine());
        while(studentNum-- > 0){
            String[] str = br.readLine().split(" ");

            if(str[0].equals("1")){
                // 남학생
                int sNum = Integer.parseInt(str[1]);
                for(int i = sNum; i < N+1 ; i+=sNum){
                    switches[i] = switches[i] == 1 ? 0 : 1;
                }
            }
            else{
                // 여학생
                int sNum = Integer.parseInt(str[1]);
                int left = sNum-1;
                int right = sNum+1;

                while(left > 0 && right < N+1){
                    if(switches[left] == switches[right]){
                        left--;
                        right++;
                    }
                    else
                        break;

                }

                left = left <= 0 ? 1 : left+1;
                right = N < right ? N : right-1;

                for(int i = left ; i < sNum ; i++){
                    switches[i] = switches[i] == 1 ? 0 : 1;
                }

                for(int i = sNum ; i <= right ; i++){
                    switches[i] = switches[i] == 1 ? 0 : 1;
                }
            }
            for(int i = 1; i < N+1 ; i++){
                sb.append(switches[i]).append(" ");
                if(i%20 == 0)
                    sb.append("\n");
            }
    
            System.out.println(sb);
            sb = new StringBuilder();
        }

        // for(int i = 1; i < N+1 ; i++){
        //     sb.append(switches[i]).append(" ");
        //     if(i%20 == 0)
        //         sb.append("\n");
        // }

        // System.out.println(sb);
    }
}
