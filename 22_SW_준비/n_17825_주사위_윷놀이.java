import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n_17825_주사위_윷놀이 {
    static class node {
        int score;
        boolean isFinish;
        boolean isEmpty;
        node next;
        node shortCut;

        public node(int score) {
            this.score = score;
            this.isEmpty = true;
        }

        public node setNextNode(int score) {
            node next = new node(score);
            this.next = next;
            return next;
        }

        public static node findNode(node start, int score) {
            node now = start;
            while (true) {
                if(now == null) return null;
                if(now.score == score) return now;

                now = now.next;
            }
        }
    }

    static int answer;
    static int[] dices, orders;
    static node[] horses;
    static node start;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        dices = new int[10];
        orders = new int[10];
        horses = new node[4];
        answer = Integer.MIN_VALUE;

        for (int i = 0; i < 10; i++) {
            dices[i] = Integer.parseInt(st.nextToken());
        }

        init();
        makeOrder(0);

        System.out.println(answer);
    }

    private static void makeOrder(int cnt) {
        if (cnt == 10) {
            answer = Math.max(answer, gameStart());
            if (answer == 190) {
                System.out.println(orders[0]);
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            orders[cnt] = i;
            makeOrder(cnt + 1);
        }
    }

    private static int gameStart() {
        Arrays.fill(horses, start);
        int score = 0;
        for (int i = 0; i < 10; i++) {
            node now = horses[orders[i]];
            now.isEmpty = true;

            for (int j = 0; j < dices[i]; j++) {
                if (j == 0 && now.shortCut != null) {
                    now = now.shortCut;
                } else {
                    now = now.next;
                }
            }

            horses[orders[i]] = now;
            if (!now.isEmpty && !now.isFinish) {
                score = 0;
                break;
            } else {
                now.isEmpty = false;
                score += now.score;
            }
        }

        for (int i = 0; i < 4; i++) {
            horses[i].isEmpty = true;
        }

        return score;
    }

    private static void init() {
        start = new node(0);
        node tmp = start;
        for (int i = 2; i <= 40; i += 2) {
            tmp = tmp.setNextNode(i);
        }

        node end = tmp.setNextNode(0);
        end.isFinish = true;
        end.next = end;

        node center = new node(25);
        tmp = center.setNextNode(30);
        tmp = tmp.setNextNode(35);
        tmp.next = node.findNode(start, 40);

        tmp = node.findNode(start, 10);
        tmp = tmp.shortCut = new node(13);
        tmp = tmp.setNextNode(16);
        tmp = tmp.setNextNode(19);
        tmp.next = center;

        tmp = node.findNode(start, 20);
        tmp = tmp.shortCut = new node(22);
        tmp = tmp.setNextNode(24);
        tmp.next = center;

        tmp = node.findNode(start, 30);
        tmp = tmp.shortCut = new node(28);
        tmp = tmp.setNextNode(27);
        tmp = tmp.setNextNode(26);
        tmp.next = center;
    }
}
