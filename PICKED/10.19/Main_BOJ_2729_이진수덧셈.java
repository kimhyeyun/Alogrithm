import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2729_이진수덧셈 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;
        StringTokenizer stringTokenizer;

        int testCase = Integer.parseInt(br.readLine());

        while(testCase-- > 0){
            sb = new StringBuilder();
            stringTokenizer = new StringTokenizer(br.readLine());
            String a = stringTokenizer.nextToken();
            String b = stringTokenizer.nextToken();

            

            int len = 0;
            int diff = 0;
            int carry = 0;
            int remain = 0;
            if(a.length() > b.length()){
                len = a.length();
                diff = a.length() - b.length();
                while(diff-- > 0){
                    b = '0'+ b;
                }
            }
            else if(b.length() > a.length()){
                len = b.length();
                diff = b.length() - a.length();
                while(diff-- > 0){
                    a = '0' + a;
                }
            }
            else
                len = a.length();

            for(int i = len-1; i >= 0 ; i--){
                int sum = (a.charAt(i) - 48) + (b.charAt(i) - 48) + carry;
                carry = sum / 2;
                remain = sum % 2;
                sb.append(remain);
                if(i == 0 && carry == 1)
                    sb.append(1);
                
            }

            char[] str = sb.reverse().toString().toCharArray();
            int idx = 0;
            for(int i = 0 ; i < str.length; i++){
                if(str[i] == '0'){
                    idx++;
                }
                else break;
            }

            if(idx == str.length)
                System.out.println(0);
            else{
                for(int i = idx; i < str.length ; i++)
                    System.out.print(str[i]);
                System.out.println();
            }

        }
    }
}
