import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_8879_올림픽 {

    public static class Nation implements Comparable<Nation> {
        int index;
        int gold, silver, bronze;

        public Nation(int index, int gold, int silver, int bronze) {
            this.index = index;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        @Override
        public int compareTo(Nation o) {
            if (this.gold == o.gold) {
                if (this.silver == o.silver) {
                    return o.bronze - this.bronze;
                }
                return o.silver - this.silver;
            }
            return o.gold - this.gold;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Nation> nations = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            nations.add(new Nation(index, g, s, b));
        }

        Collections.sort(nations);

        if (nations.get(0).index == K) {
            System.out.println(1);
        } else {
            int rank = 1;
            int count = 1;

            for (int i = 1; i < nations.size(); i++) {
                Nation prev = nations.get(i - 1);
                Nation cur = nations.get(i);

                if (prev.gold == cur.gold && prev.silver == cur.silver && prev.bronze == cur.bronze) {
                    count += 1;
                } else {
                    rank += count;
                    count = 1;
                }

                if (cur.index == K) {
                    System.out.println(rank);
                    break;
                }
            }

        }
    }
}
