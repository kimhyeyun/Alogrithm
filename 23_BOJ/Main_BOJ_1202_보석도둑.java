import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1202_보석도둑 {

    static class Jewel {
        int mass;
        int value;

        public Jewel(int mass, int value) {
            this.mass = mass;
            this.value = value;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Jewel> jewels = new ArrayList<>();
        List<Integer> bags = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            jewels.add(new Jewel(m, v));
        }

        for (int i = 0; i < K; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(jewels, Comparator.comparingInt(o -> o.mass));
        Collections.sort(bags);

        PriorityQueue<Jewel> putJewels = new PriorityQueue<>((o1, o2) -> o2.value - o1.value);

        int index = 0;
        long answer = 0;

        for (int i = 0; i < K; i++) {
            int nowBag = bags.get(i);

            while (index < N) {
                if (jewels.get(index).mass <= nowBag) {
                    putJewels.add(jewels.get(index));
                    index += 1;
                } else break;
            }

            if (!putJewels.isEmpty()) {
                answer += putJewels.poll().value;
            }
        }
        System.out.println(answer);
    }
}
