import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_12782_비트우정지수 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer;

        int testCase = Integer.parseInt(br.readLine());

        while(testCase-- > 0){
            stringTokenizer = new StringTokenizer(br.readLine());

            char[] bit1 = stringTokenizer.nextToken().toCharArray();
            char[] bit2 = stringTokenizer.nextToken().toCharArray();

            int cntOne = 0;
            int cntZero = 0;
            for(int i = 0; i < bit1.length; i++){
                if(bit1[i] != bit2[i]){
                    if(bit1[i] == '1')
                        cntOne++;
                    else
                        cntZero++;
                }
            }

            if(cntZero < cntOne){
                sb.append(cntZero + (cntOne - cntZero)).append("\n");
            }
            else if(cntOne < cntZero){
                sb.append(cntOne + (cntZero - cntOne)).append("\n");
            }
            else{
                sb.append(cntOne).append("\n");
            }
        }

        System.out.print(sb);
    }
    
}
