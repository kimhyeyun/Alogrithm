import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_9655_돌_게임 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 1 -> SK
        // 2 -> CY
        // 3 -> SK
        // 4 -> CY
        // 5 -> (1,3,1) (3,1,1) (1,1,3) SK
        // 6 -> (1,1,1,3) (1,1,3,1) (1,3,1,1) (3,1,1,1) (3,3) CY

        if (N % 2 == 0) {
            System.out.println("CY");
        } else {
            System.out.println("SK");
        }
    }
}
