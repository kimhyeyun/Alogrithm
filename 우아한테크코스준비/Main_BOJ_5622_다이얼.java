import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_5622_다이얼 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] phoneStr = {"", "", "ABC", "DEF", "GHI", "JKL", "MNO" , "PQRS", "TUV", "WXYZ"};

        String str = br.readLine();

        int ans = 0;
        for(char c : str.toCharArray()){
            for(int i = 2 ; i < 10 ; i++){
                if(phoneStr[i].indexOf(c) != -1){
                    ans += i+1;
                    break;
                }
            }
        }

        System.out.println(ans);
    }
}
