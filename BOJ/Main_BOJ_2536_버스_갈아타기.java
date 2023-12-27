import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_2536_버스_갈아타기 {

    public static class Bus {
        int index;
        int startX, startY;
        int endX, endY;
        int type; // 0: 수직, 1: 수평

        public Bus(int index, int x1, int y1, int x2, int y2, int type) {
            this.index = index;
            this.startX = Math.min(x1, x2);
            this.startY = Math.min(y1, y2);
            this.endX = Math.max(x1, x2);
            this.endY = Math.max(y1, y2);
            this.type = type;
        }

        public boolean isContain(int x, int y) {
            if (this.type == 0) {
                // 수직, this.startX == this.endX
                if (this.startX != x) return false;
                return this.startY <= y && y <= this.endY;
            } else {
                // 수평, this.startY == this.endY
                if (this.startY != y) return false;
                return this.startX <= x && x <= endX;
            }
        }

        public boolean isContain(Bus bus) {
            if (this.type == 0) {
                if (bus.type == 0) {
                    // 수직 - 수직
                    return this.startX == bus.startX && this.startY <= bus.endY && bus.startY <= this.endY;
                } else {
                    // 수직 - 수평
                    return (bus.startX <= this.startX && this.startX <= bus.endX) && (this.startY <= bus.startY && bus.startY <= this.endY);
                }
            } else {
                if (bus.type == 0) {
                    // 수평 - 수직
                    return (this.startX <= bus.startX && bus.startX <= this.endX) && (bus.startY <= this.startY && this.startY <= bus.endY);
                } else {
                    // 수평 - 수평
                    return this.startY == bus.startY && this.endX >= bus.startX && bus.endX >= this.startX;
                }
            }
        }
    }

    static int m, n, k;
    static int sx,sy,ex, ey;
    static Bus[] buses;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());

        buses = new Bus[k + 1];

        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());

            int b = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int type = y1 == y2 ? 1 : 0;

            buses[b] = new Bus(b, x1, y1, x2, y2, type);
        }

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());
        ex = Integer.parseInt(st.nextToken());
        ey = Integer.parseInt(st.nextToken());

        System.out.println(BFS());

    }

    private static int BFS() {
        Queue<Bus> q = new LinkedList<>();
        int[] visited = new int[k + 1];

        for (int i = 1; i <= k; i++) {
            if (buses[i].isContain(sx, sy)) {
                q.add(buses[i]);
                visited[i] = 1;
            }
        }

        while (!q.isEmpty()) {
            Bus cur = q.poll();

            if (cur.isContain(ex, ey)) return visited[cur.index];

            for (int i = 1; i <= k; i++) {
                if (cur.index == i) continue;
                if (!cur.isContain(buses[i]) || visited[i] != 0) continue;

                q.add(buses[i]);
                visited[i] = visited[cur.index] + 1;
            }
        }
        return -1;
    }
}
