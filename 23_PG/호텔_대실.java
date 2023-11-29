import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 호텔_대실 {

    public int solution(String[][] book_time) {
        PriorityQueue<int[]> books = new PriorityQueue<>((o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });

        for (int i = 0; i < book_time.length; i++) {
            String[] start = book_time[i][0].split(":");
            String[] end = book_time[i][1].split(":");

            int s = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
            int e = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]) + 10;

            books.add(new int[]{s, e});
        }

        PriorityQueue<int[]> rooms = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        rooms.add(books.poll());

        while (!books.isEmpty()) {
            int[] book = books.poll();
            int[] room = rooms.peek();

            if(room[1] <= book[0]) rooms.poll();

            rooms.add(book);
        }

        return rooms.size();
    }

    @Test
    void test() {
//        assertEquals(solution(new String[][]{{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}}), 3);
//        assertEquals(solution(new String[][]{{"05:57", "06:02"}, {"04:00", "06:59"}, {"03:56", "07:57"}, {"06:12", "08:55"}, {"07:09", "07:11"}}), 3);
        assertEquals(solution(new String[][]{{"08:00", "08:30"}, {"08:00", "13:00"}, {"12:30", "13:30"}}), 2);
    }

}
