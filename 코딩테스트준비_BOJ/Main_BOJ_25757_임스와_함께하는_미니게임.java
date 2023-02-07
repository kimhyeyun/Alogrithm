import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_25757_임스와_함께하는_미니게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        String game = stringTokenizer.nextToken();

        List<String> proposers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            proposers.add(br.readLine());
        }

        int size = (int) proposers.stream().distinct().count();

        if (game.equals("Y")) {
            System.out.println(size / 1);
        } else if (game.equals("F")) {
            System.out.println(size / 2);
        } else {
            System.out.println(size / 3);
        }


    }
}
