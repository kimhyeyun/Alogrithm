import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class tmax_23 {
    public String solution(String penter, String pexit, String pescape, String data) {
        int len = penter.length();

        String answer = penter;
        for (int i = 0; i < data.length(); i += len) {
            String str = data.substring(i, i + len);
            System.out.println(str);

            if (str.equals(penter) || str.equals(pexit) || str.equals(pescape)) {
                answer += pescape;
            }

            answer += str;
        }

        answer += pexit;

        return answer;
    }

    static class Alphabet implements Comparable<Alphabet>{
        int x, y;
        char c;

        public Alphabet(int x, int y, char c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        @Override
        public int compareTo(Alphabet o) {
            if(this.x == o.x) return this.y - o.y;
            return this.x - o.x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Alphabet)) return false;
            Alphabet alphabet = (Alphabet) o;
            return x == alphabet.x && y == alphabet.y && c == alphabet.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, c);
        }
    }
    int[] dx = {-1, 0, 0, 1};
    int[] dy = {0, -1, 1, 0};
    int answer = Integer.MAX_VALUE;

    public int solution(String[] maps, String word) {
        List<Alphabet> list = new ArrayList<>();
        if(maps[0].charAt(0) != '-') list.add(new Alphabet(0, 0, maps[0].charAt(0)));

        boolean[][] isVisited = new boolean[maps.length][maps[0].length()];
        isVisited[0][0] = true;

        DFS(maps, word, 0, 0, list, isVisited, 0);

//        int answer = BFS(maps, word);

        if (answer == Integer.MAX_VALUE) return -1;
        else return answer;
    }

    private void DFS(String[] maps, String word, int x, int y, List<Alphabet> list, boolean[][] isVisited, int count) {
        if (list.size() == word.length()) {
            Collections.sort(list);

            StringBuilder sb = new StringBuilder();
            for(Alphabet c : list) sb.append(c.c);

            String str = sb.toString();
            if(str.equals(word)) answer = Math.min(answer, count);

            return;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || ny < 0 || maps.length <= nx || maps[0].length() <= ny || isVisited[nx][ny]) continue;

            if (maps[nx].charAt(ny) != '-') {
                Alphabet a = new Alphabet(nx, ny, maps[nx].charAt(ny));
                list.add(a);
                isVisited[nx][ny] = true;
                DFS(maps, word, nx, ny, list, isVisited, count + 1);
                isVisited[nx][ny] = false;
                list.remove(a);

            } else {
                isVisited[nx][ny] = true;
                DFS(maps, word, nx, ny, list, isVisited, count + 1);
                isVisited[nx][ny] = false;
            }

        }
    }

    class Job implements Comparable<Job>{
        int index;
        int importance;
        Queue<Integer> list = new LinkedList<>();

        public Job(int index, int importance, int l) {
            this.index = index;
            this.importance = importance;
            this.list.add(l);
        }

        public void addImportance(int importance) {
            this.importance += importance;
        }

        public void addQueue(int time) {
            this.list.add(time);
        }

        @Override
        public int compareTo(Job o) {
            if(this.importance == o.importance) return this.index - o.index;
            return o.importance - this.importance;
        }
    }

    public int[] solution(int[][] jobs) {
        List<Integer> answer = new ArrayList<>();
        Job[] ready = new Job[101];

        int nowIndex = jobs[0][2];

        answer.add(nowIndex);
        int index = 1;
        int time = jobs[0][0] + jobs[0][1];

        while(index < jobs.length) {
            List<Job> tmp = new ArrayList<>();
            for (int i = index; i < jobs.length; i++) {
                if (jobs[i][0] <= time) {
                    if (ready[jobs[i][2]] == null) ready[jobs[i][2]] = new Job(jobs[i][2], jobs[i][3], jobs[i][1]);
                    else {
                        ready[jobs[i][2]].addImportance(jobs[i][3]);
                        ready[jobs[i][2]].addQueue(jobs[i][1]);
                    }

                    if(i == jobs.length - 1) break;
                } else {
                    index = i;
                    break;
                }
            }

            for (int i = 0; i < 101; i++) {
                if(ready[i] != null) tmp.add(ready[i]);
            }

            if (ready[nowIndex] == null) {
                Collections.sort(tmp);
                nowIndex = tmp.get(0).index;
                answer.add(nowIndex);

            }
            while (!ready[nowIndex].list.isEmpty()) {
                time += ready[nowIndex].list.poll();
            }

            ready[nowIndex] = null;
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }


    @Test
    void test() {
//        assertEquals(solution("1100", "0010", "1001", "1101100100101111001111000000"), "110011011001100110010010111100111001110000000010");
//        System.out.println(solution(new String[]{"--a-", "--d-", "b-c-", "---c"}, "abc"));
//        System.out.println(solution(new String[]{"--z", "kye", "-xu"}, "zyx"));
//        System.out.println(solution(new String[]{"a"}, "a"));

        int[] result = solution(new int[][]{{1, 5, 2, 3}, {2, 2, 3, 2}, {3, 1, 3, 3}, {5, 2, 1, 5}, {7, 1, 1, 1}, {9, 1, 1, 1}, {10, 2, 2, 9}});
        for (int r : result) {
            System.out.println(r);
        }
    }
}
