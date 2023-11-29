import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class code_tree_산타의_선물_공장_2 {
   public static int n, m, q;
   public static int[] prev, next;
   public static int[] head, tail;
    public static int[] numGift;

    public static BufferedReader br;
    public static StringBuilder sb;
    public static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        q = Integer.parseInt(br.readLine());

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == 100) buildFactory();
            else if(command == 200) moveAll();
            else if(command == 300) changeHead();
            else if (command == 400) divide();
            else if (command == 500) giftInfo();
            else beltInfo();
        }
        System.out.println(sb);
    }

    private static void beltInfo() {
        int num = Integer.parseInt(st.nextToken()) - 1;

        int a = head[num] == 0 ? -1 : head[num];
        int b = tail[num] == 0 ? -1 : tail[num];
        int c = numGift[num];

        sb.append(a + 2 * b + 3 * c).append("\n");
    }

    private static void giftInfo() {
        int num = Integer.parseInt(st.nextToken());

        int a = prev[num] == 0 ? -1 : prev[num];
        int b = next[num] == 0 ? -1 : next[num];

        sb.append(a + 2 * b).append("\n");
    }

    private static void divide() {
        int src = Integer.parseInt(st.nextToken()) - 1;
        int dest = Integer.parseInt(st.nextToken()) - 1;

        int count = numGift[src];
        List<Integer> boxIds = new ArrayList<>();
        for (int i = 0; i < count / 2; i++) {
            boxIds.add(removeHead(src));
        }

        for (int i = boxIds.size() - 1; i >= 0; i--) {
            pushHead(dest, boxIds.get(i));
        }

        sb.append(numGift[dest]).append("\n");
    }

    private static void changeHead() {
        int src = Integer.parseInt(st.nextToken()) - 1;
        int dest = Integer.parseInt(st.nextToken()) - 1;

        int srcHead = removeHead(src);
        int destHead = removeHead(dest);

        pushHead(dest, srcHead);
        pushHead(src, destHead);

        sb.append(numGift[dest]).append("\n");
    }

    private static void pushHead(int num, int hId) {
        if (hId == 0) return;

        if (numGift[num] == 0) {
            head[num] = tail[num] = hId;
            numGift[num] += 1;
        } else {
            int originHead = head[num];
            next[hId] = originHead;
            prev[originHead] = hId;
            head[num] = hId;
            numGift[num] += 1;
        }
    }

    private static int removeHead(int num) {
        if (numGift[num] == 0) return 0;
        if (numGift[num] == 1) {
            int id = head[num];
            head[num] = tail[num] = 0;
            numGift[num] = 0;
            return id;
        }

        int hId = head[num];
        int nextId = next[hId];
        next[hId] = prev[nextId] = 0;
        numGift[num] -= 1;
        head[num] = nextId;

        return hId;
    }

    private static void moveAll() {
        int src = Integer.parseInt(st.nextToken()) - 1;
        int dest = Integer.parseInt(st.nextToken()) - 1;

        if (numGift[src] == 0) {
            sb.append(numGift[dest]).append("\n");
            return;
        }

        if (numGift[dest] == 0) {
            head[dest] = head[src];
            tail[dest] = tail[src];
        } else {
            int originHead = head[dest];
            head[dest] = head[src];

            int srcTail = tail[src];
            next[srcTail] = originHead;
            prev[originHead] = srcTail;
        }

        head[src] = tail[src] = 0;

        numGift[dest] += numGift[src];
        numGift[src] = 0;

        sb.append(numGift[dest]).append("\n");
    }

    private static void buildFactory() {
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        prev = new int[m + 1];
        next = new int[m + 1];

        head = new int[n + 1];
        tail = new int[n + 1];
        numGift = new int[n + 1];

        List<Integer>[] boxes = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            boxes[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            boxes[Integer.parseInt(st.nextToken()) - 1].add(i);
        }

        for (int i = 0; i < n; i++) {
            if(boxes[i].size() == 0) continue;

            head[i] = boxes[i].get(0);
            tail[i] = boxes[i].get(boxes[i].size() - 1);

            numGift[i] = boxes[i].size();

            for (int j = 0; j < boxes[i].size() - 1; j++) {
                next[boxes[i].get(j)] = boxes[i].get(j + 1);
                prev[boxes[i].get(j + 1)] = boxes[i].get(j);
            }
        }
    }
}
