import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1713_후보_추천하기 {
    static class student implements Comparable<student> {
        int idx, order, cnt;

        public student(int idx, int order, int cnt) {
            this.idx = idx;
            this.order = order;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(student o) {
            if (this.cnt == o.cnt)
                return this.order - o.order;
            return this.cnt - o.cnt;
        }
    }

    static int N, totalOfReco;
    static List<student> students;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());
        totalOfReco = Integer.parseInt(br.readLine());

        students = new ArrayList<>();

        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < totalOfReco; i++) {
            boolean flag = false;
            int idx = Integer.parseInt(stringTokenizer.nextToken());
            for (student s : students) {
                if(s.idx == idx) {
                    s.cnt += 1;
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                if (students.size() == N) {
                    Collections.sort(students);
                    students.remove(0);
                }
                students.add(new student(idx, i, 1));
            }
        }

        List<Integer> answer = new ArrayList<>();
        for (student s : students) {
            answer.add(s.idx);
        }

        Collections.sort(answer);
        for (int a : answer) {
            System.out.print(a + " ");
        }
        System.out.println();
    }
}

