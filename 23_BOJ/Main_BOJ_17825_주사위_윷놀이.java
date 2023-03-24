import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_17825_주사위_윷놀이 {
    static class Square {
        int score;
        boolean isFinished;
        boolean isEmpty;
        Square next;
        Square shortCut;

        public Square(int score) {
            this.score = score;
            isFinished = false;
            isEmpty = true;
            next = null;
            shortCut = null;
        }

        public Square addNext(int next) {
            this.next = new Square(next);
            return this.next;
        }

        public static Square findSquare(Square start, int score) {
            Square now = start;

            while (true) {
                if(now == null) return null;
                if(now.score == score) return now;

                now = now.next;
            }
        }

        public Square addShortCut(int shortCut) {
            this.shortCut = new Square(shortCut);
            return this.shortCut;
        }
    }

    static int answer;
    static int[] numberOfDices, orders;
    static Square start;
    static Square[] horses;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        answer = Integer.MIN_VALUE;

        numberOfDices = new int[10];
        orders = new int[10];
        horses = new Square[4];

        for (int i = 0; i < 10; i++) {
            numberOfDices[i] = Integer.parseInt(st.nextToken());
        }

        initBoard();
        makeOrder(0);

        System.out.println(answer);
    }

    private static void makeOrder(int count) {
        if (count == 10) {
            answer = Math.max(playGame(), answer);
            return;
        }

        for (int i = 0; i < 4; i++) {
            orders[count] = i;
            makeOrder(count + 1);
        }
    }

    private static int playGame() {
        Arrays.fill(horses, start);

        int score = 0;

        for (int i = 0; i < 10; i++) {
            Square now = horses[orders[i]];
            now.isEmpty = true;

            for (int j = 0; j < numberOfDices[i]; j++) {
                if (j == 0 && now.shortCut != null) {
                    now = now.shortCut;
                } else {
                    now = now.next;
                }
            }

            horses[orders[i]] = now;
            if (!now.isEmpty && !now.isFinished) {
                score = 0;
                break;
            }

            now.isEmpty = false;
            score += now.score;
        }

        for (int i = 0; i < 4; i++) {
            horses[i].isEmpty = true;
        }

        return score;
    }

    private static void initBoard() {
        start = new Square(0);

        Square tmp = start;

        for (int i = 2; i <= 40; i += 2) {
            tmp = tmp.addNext(i);
        }

        Square end = tmp.addNext(0);
        end.isFinished = true;
        end.next = end;

        Square center = new Square(25);
        tmp = center.addNext(30);
        tmp = tmp.addNext(35);
        tmp.next = Square.findSquare(start, 40);

        tmp = Square.findSquare(start, 10);
        tmp = tmp.addShortCut(13);
        tmp = tmp.addNext(16);
        tmp = tmp.addNext(19);
        tmp.next = center;

        tmp = Square.findSquare(start, 20);
        tmp = tmp.addShortCut(22);
        tmp = tmp.addNext(24);
        tmp.next = center;

        tmp = Square.findSquare(start, 30);
        tmp = tmp.addShortCut(28);
        tmp = tmp.addNext(27);
        tmp = tmp.addNext(26);
        tmp.next = center;

    }
}
