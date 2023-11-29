import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_7568_덩치 {
    public static class Person implements Comparable<Person> {
        int index;
        int weight, height;

        public Person(int index, int weight, int height) {
            this.index = index;
            this.weight = weight;
            this.height = height;
        }


        @Override
        public int compareTo(Person o) {
            if (this.weight == o.weight) {
                return o.height - this.height;
            }
            return o.weight - this.weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        List<Person> people = new ArrayList<>();
        Map<Integer, Integer> rank = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            people.add(new Person(i, w, h));
            rank.put(i, 1);
        }

        Collections.sort(people);


        for (int i = 0; i < people.size(); i++) {
            Person now = people.get(i);

            for (int j = 0; j < i; j++) {
                if (now.weight < people.get(j).weight && now.height < people.get(j).height) {
                    rank.put(now.index, rank.getOrDefault(now.index, 0) + 1);
                }
            }
        }

        for (int key : rank.keySet()) {
            System.out.print(rank.get(key) + " ");
        }
    }
}
