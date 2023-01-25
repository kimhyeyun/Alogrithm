import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_200006_랭킹전_대기열 {
    static class Player implements Comparable<Player> {
        int level;
        String nickname;

        public Player(int level, String nickname) {
            this.level = level;
            this.nickname = nickname;
        }

        @Override
        public int compareTo(Player o) {
            return this.nickname.compareTo(o.nickname);
        }
    }
    static class Room {
        List<Player> players;
        int initLevel;

        public Room(int initLevel) {
            this.players = new LinkedList<>();
            this.initLevel = initLevel;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Room> rooms = new LinkedList<>();

        int p = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            String n = st.nextToken();
            boolean isOk = false;

            for (Room room : rooms) {
                if (room.players.size() < m && (room.initLevel - 10 <= l && l <= room.initLevel + 10)) {
                    room.players.add(new Player(l, n));
                    isOk = true;
                    break;
                }
            }

            if (!isOk) {
                Room r = new Room(l);
                r.players.add(new Player(l, n));
                rooms.add(r);
            }
        }

        for (Room room : rooms) {
            if (room.players.size() == m) {
                stringBuilder.append("Started!").append("\n");
            } else {
                stringBuilder.append("Waiting!").append("\n");
            }

            List<Player> player = room.players;

            Collections.sort(player);
            for (Player tmp : player) {
                stringBuilder.append(tmp.level + " " + tmp.nickname).append("\n");
            }
        }
        System.out.println(stringBuilder);
    }
}
