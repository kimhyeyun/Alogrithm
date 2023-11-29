import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 코딩과제 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        long M = Long.parseLong(input[0]);
        long N = Long.parseLong(input[1]);



        System.out.println(N * M);
    }
}
