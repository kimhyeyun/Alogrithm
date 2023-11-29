import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class code_tree_산타의_선물_공장 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int q, n, m;
    static Map<Integer, Integer> weightOfBoxes, prev, next, beltNumOfBoxes;
    static int[] head, tail;
    static boolean[] isBroken;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        q = Integer.parseInt(br.readLine());

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == 100) buildFactory();
            else if (command == 200) dropBox();
            else if (command == 300) removeBox();
            else if (command == 400) checkBox();
            else brokenBelt();
        }
        System.out.println(sb);
    }

    private static void brokenBelt() {
        int beltNum = Integer.parseInt(st.nextToken()) - 1;

        if (isBroken[beltNum]) {
            sb.append(-1).append("\n");
            return;
        }

        isBroken[beltNum] = true;
        if (head[beltNum] == -1) {
            sb.append(beltNum + 1).append("\n");
            return;
        }

        int index = beltNum;
        while (true) {
            index = (index + 1) % m;

            if (!isBroken[index]) {
                if (tail[index] == -1) {
                    head[index] = head[beltNum];
                    tail[index] = tail[beltNum];
                } else {
                    pushId(tail[index], head[beltNum]);
                    tail[index] = tail[beltNum];
                }

                int id = head[beltNum];
                while (id != -1) {
                    beltNumOfBoxes.put(id, index);
                    id = next.getOrDefault(id, -1);
                }

                head[beltNum] = tail[beltNum] = -1;
                break;
            }
        }
        sb.append(beltNum + 1).append("\n");
    }

    private static void checkBox() {
        int idOfBox = Integer.parseInt(st.nextToken());
        int beltNum = beltNumOfBoxes.getOrDefault(idOfBox, -1);

        if (beltNum == -1) {
            sb.append(-1).append("\n");
            return;
        }

        if (head[beltNum] != idOfBox) {
            int originTail = tail[beltNum];
            int originHead = head[beltNum];

            int nowTail = prev.get(idOfBox);
            tail[beltNum] = nowTail;
            next.put(nowTail, -1);

            next.put(originTail, originHead);
            prev.put(originHead, originTail);

            head[beltNum] = idOfBox;
        }

        sb.append(beltNum + 1).append("\n");
    }

    private static void removeBox() {
        int idOfBox = Integer.parseInt(st.nextToken());
        int beltNum = beltNumOfBoxes.getOrDefault(idOfBox, -1);

        if (beltNum == -1) {
            sb.append(-1).append("\n");
            return;
        }

        removeId(idOfBox, true);
        sb.append(idOfBox).append("\n");
    }

    private static void dropBox() {
        int maxOfWeight = Integer.parseInt(st.nextToken());
        int sumOfWeight = 0;

        for (int i = 0; i < m; i++) {
            if (isBroken[i]) continue;
            if (head[i] != -1) {
                int id = head[i];
                int weight = weightOfBoxes.get(id);

                if (weight <= maxOfWeight) {
                    sumOfWeight += weight;
                    removeId(id, true);
                } else if (next.get(id) != -1) {
                    removeId(id, false);
                    pushId(tail[i], id);
                }
            }
        }
        sb.append(sumOfWeight).append("\n");
    }

    private static void pushId(int targetId, int id) {
        next.put(targetId, id);
        prev.put(id, targetId);

        int beltNum = beltNumOfBoxes.get(targetId);
        if (tail[beltNum] == targetId) {
            tail[beltNum] = id;
        }
    }

    private static void removeId(int id, boolean removeBelt) {
        int beltNum = beltNumOfBoxes.get(id);

        if (removeBelt) beltNumOfBoxes.put(id, -1);

        if (head[beltNum] == tail[beltNum]) head[beltNum] = tail[beltNum] = -1;
        else if (id == head[beltNum]) {
            int nextId = next.get(id);
            head[beltNum] = nextId;
            prev.put(nextId, -1);
        } else if (id == tail[beltNum]) {
            int prevId = prev.get(id);
            tail[beltNum] = prevId;
            next.put(prevId, -1);
        } else {
            int prevId = prev.get(id);
            int nextId = next.get(id);

            next.put(prevId, nextId);
            prev.put(nextId, prevId);
        }

        next.put(id, -1);
        prev.put(id, -1);
    }

    private static void buildFactory() {
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        weightOfBoxes = new HashMap<>();

        prev = new HashMap<>();
        next = new HashMap<>();
        beltNumOfBoxes = new HashMap<>();

        head = new int[m];
        tail = new int[m];
        isBroken = new boolean[m];

        int count = n / m;
        int[] ids = new int[n];
        int[] weights = new int[n];

        for (int i = 0; i < n; i++) {
            ids[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            weightOfBoxes.put(ids[i], weights[i]);
        }

        for (int i = 0; i < m; i++) {
            head[i] = ids[i * count];
            tail[i] = ids[(i + 1) * count - 1];

            for (int j = i * count; j < (i + 1) * count; j++) {
                beltNumOfBoxes.put(ids[j], i);

                if (j < (i + 1) * count - 1) {
                    next.put(ids[j], ids[j + 1]);
                    prev.put(ids[j + 1], ids[j]);
                }
            }
        }
    }
}
