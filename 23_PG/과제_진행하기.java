import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class 과제_진행하기 {

    class Subject {
        String name;
        int startTime;
        int durationTime;

        public Subject(String name, int startTime, int durationTime) {
            this.name = name;
            this.startTime = startTime;
            this.durationTime = durationTime;
        }

        public Subject(String name, int durationTime) {
            this.name = name;
            this.durationTime = durationTime;
        }
    }

    public String[] solution(String[][] plans) {
        PriorityQueue<Subject> pq = new PriorityQueue<>(((o1, o2) -> (o1.startTime - o2.startTime)));
        Stack<Subject> pause = new Stack<>();

        for (int i = 0; i < plans.length; i++) {
            String[] split = plans[i][1].split(":");
            int s = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
            int d = Integer.parseInt(plans[i][2]);

            pq.add(new Subject(plans[i][0], s, d));
        }

        List<String> answer = new ArrayList<>();

        while (!pq.isEmpty()) {
            Subject now = pq.poll();

            int nowTime = now.startTime;

            if (!pq.isEmpty()) {
                Subject next = pq.peek();

                if (nowTime + now.durationTime < next.startTime) {
                    answer.add(now.name);
                    nowTime += now.durationTime;

                    while (!pause.isEmpty()) {
                        Subject p = pause.pop();

                        if (nowTime + p.durationTime <= next.startTime) {
                            nowTime += p.durationTime;
                            answer.add(p.name);
                            continue;
                        } else {
                            int t = p.durationTime - (next.startTime - nowTime);
                            pause.push(new Subject(p.name, t));
                            break;
                        }
                    }
                } else if (nowTime + now.durationTime == next.startTime) {
                    answer.add(now.name);
                    continue;
                } else {
                    int t = next.startTime - nowTime;
                    pause.push(new Subject(now.name, now.durationTime - t));
                }
            } else {
                if (pause.isEmpty()) {
                    nowTime += now.durationTime;
                    answer.add(now.name);
                } else {
                    answer.add(now.name);

                    while (!pause.isEmpty()) {
                        Subject p = pause.pop();
                        answer.add(p.name);
                    }
                }
            }
        }

        return answer.toArray(new String[answer.size()]);
    }

    @Test
    void test() {
//        Assertions.assertArrayEquals(solution(new String[][]{{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}}), new String[]{"korean", "english", "math"});
        Assertions.assertArrayEquals(solution(new String[][]{{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}}),
                new String[]{"science", "history", "computer", "music"});
    }
}
