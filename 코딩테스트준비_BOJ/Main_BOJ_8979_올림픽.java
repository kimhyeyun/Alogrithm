import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_8979_올림픽 {
    static class Country implements Comparable<Country> {
        int idx;
        int gold, silver, bronze;

        public Country(int idx, int gold, int silver, int bronze) {
            this.idx = idx;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        @Override
        public int compareTo(Country o) {
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
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());

        List<Country> countries = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());

            int idx = Integer.parseInt(stringTokenizer.nextToken());
            int g = Integer.parseInt(stringTokenizer.nextToken());
            int s = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());

            countries.add(new Country(idx, g, s, b));
        }

        Collections.sort(countries);

        if (countries.get(0).idx == K) {
            System.out.println(1);
            return;
        }

        int rank = 1;
        int cnt = 1;

        for (int i = 1; i < countries.size(); i++) {
            Country prev = countries.get(i - 1);
            Country now = countries.get(i);

            if (now.gold == prev.gold && now.silver == prev.silver && now.bronze == prev.bronze) {
                cnt += 1;
            } else {
                rank += cnt;
                cnt = 1;
            }

            if (now.idx == K) {
                System.out.println(rank);
                return;
            }
        }
    }
}
