import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main_BOJ_20920_영단어_암기는_괴로워 {
    /*
    정렬 방법에서 시간 초과가 아니라, 출력 과정에서 시간 초과가 났음
    * */
    public static void solution_1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            if (word.length() < M) continue;

            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<String> words = map.keySet().stream().collect(Collectors.toList());

        words.sort((o1, o2) -> {
            int cnt1 = map.get(o1);
            int cnt2 = map.get(o2);

            if (cnt1 == cnt2) {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }
                return o2.length() - o1.length();
            }
            return cnt2 - cnt1;
        });

        for (String word : words) {
            sb.append(word).append("\n");
        }
        System.out.println(sb);
    }

    /* 두 가지 방법 */
    static class Word implements Comparable<Word>{
        String w;
        int count;
        int length;

        public Word(String w, int count, int length) {
            this.w = w;
            this.count = count;
            this.length = length;
        }

        @Override
        public int compareTo(Word o) {
            int result = o.count - this.count;
            if (result == 0) {
                result = o.length - this.length;
            }
            if (result == 0) {
                result = this.w.compareTo(o.w);
            }
            return result;
        }
    }
    public static void solution_2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        List<Word> words = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            if(word.length() < M) continue;

            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for (String key : map.keySet()) {
            words.add(new Word(key, map.get(key), key.length()));
        }

        Collections.sort(words);

        for (Word word : words) {
            sb.append(word.w).append("\n");
        }
        System.out.println(sb);
    }
}
