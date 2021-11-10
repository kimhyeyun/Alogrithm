import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
/* 
    정규표현식 사용
*/
public class Main_BOJ_2671_잠수함식별{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String noise = "NOISE";
        String submarine = "SUBMARINE";
        // (100~1~|01)~
        String regexp = "^(100+1+|01)+";
        String str = br.readLine();

        boolean flag = Pattern.matches(regexp, str);
        if(flag)
            System.out.println(submarine);
        else
            System.out.println(noise);


    }
}