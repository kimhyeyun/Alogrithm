import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1316_그룹단어체커 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        boolean[] isOk;
        int ans = N;
        while(N-- > 0){
            String str = br.readLine();
            isOk = new boolean[26];

            isOk[str.charAt(0) - 'a'] = true;
            for(int i = 1 ; i < str.length() ; i++){
                if(str.charAt(i) != str.charAt(i-1)){
                    if(isOk[str.charAt(i) - 'a']){
                        ans--;
                        break;
                    }

                    isOk[str.charAt(i) - 'a'] = true;
                }
            }
        }
        System.out.println(ans);
    }
}
