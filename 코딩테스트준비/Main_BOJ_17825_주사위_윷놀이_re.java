import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_17825_주사위_윷놀이_re {
    public static class Node{
        int score;
        boolean isFinish;
        boolean isEmpty;
        Node next;
        Node fastPath;

        public Node(int score) {
            this.score = score;
            this.isEmpty = true;
        }

        public Node addNext(int score) {
            Node next = new Node(score);
            this.next = next;
            return next;
        }

        public static Node findNode(Node start, int score) {
            Node tmp = start;
            while (true) {
                if(tmp == null) return null;
                if(tmp.score == score) return tmp;

                tmp = tmp.next;
            }
        }
    }

    static int[] dice, order;
    static Node[] horses;
    static Node start;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        dice = new int[11];
        order = new int[11];
        horses = new Node[5];
        ans = Integer.MIN_VALUE;

        for (int i = 1; i < 11; i++) {
            dice[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        init();
        makeOrder(1);

        System.out.println(ans);
    }

    private static void makeOrder(int depth) {
        if (depth == 11) {
            ans = Math.max(ans, gameStart());
            return;
        }

        for (int i = 1; i <= 4; i++) {
            order[depth] = i;
            makeOrder(depth + 1);
        }
    }

    private static int gameStart() {
        Arrays.fill(horses, start);

        int score = 0;
        for (int i = 1; i < 11; i++) {
            Node cur = horses[order[i]];
            cur.isEmpty = true;

            for (int j = 1; j <= dice[i]; j++) {
                if (j == 1 && cur.fastPath != null) {
                    cur = cur.fastPath;
                } else {
                    cur = cur.next;
                }
            }

            horses[order[i]] = cur;

            if (!cur.isEmpty && !cur.isFinish) {
                score = 0;
                break;
            } else {
                cur.isEmpty = false;
                score += cur.score;
            }
        }

        for (int i = 1; i <= 4; i++) {
            horses[i].isEmpty = true;
        }

        return score;
    }

    private static void init() {
        start = new Node(0);

        Node tmp = start;
        for (int i = 2; i <= 40; i += 2) {
            tmp = tmp.addNext(i);
        }

        Node end = tmp.addNext(0);
        end.isFinish = true;
        end.next = end;

        Node center = new Node(25);

        tmp = center.addNext(30);
        tmp = tmp.addNext(35);

        tmp.next = Node.findNode(start, 40);

        tmp = Node.findNode(start, 10);
        tmp = tmp.fastPath = new Node(13);
        tmp = tmp.addNext(16);
        tmp = tmp.addNext(19);
        tmp.next = center;

        tmp = Node.findNode(start, 20);
        tmp = tmp.fastPath = new Node(22);
        tmp = tmp.addNext(24);
        tmp.next = center;

        tmp = Node.findNode(start, 30);
        tmp = tmp.fastPath = new Node(28);
        tmp = tmp.addNext(27);
        tmp = tmp.addNext(26);
        tmp.next = center;

    }
}
