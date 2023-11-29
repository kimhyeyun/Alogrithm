import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_20006_랭킹전_대기열 {

    public static class Player implements Comparable<Player> {
        String nickname;
        int level;

        public Player(String nickname, int level) {
            this.nickname = nickname;
            this.level = level;
        }

        @Override
        public int compareTo(Player o) {
            return this.nickname.compareTo(o.nickname);
        }
    }

    public static class Room {
        List<Player> players;
        int initLevel;

        public Room(int initLevel) {
            this.players = new LinkedList<>();
            this.initLevel = initLevel;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int p = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Room> rooms = new LinkedList<>();

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());

            int l = Integer.parseInt(st.nextToken());
            String n = st.nextToken();

            boolean isAttended = false;
            for (Room room : rooms) {
                if(room.initLevel - 10 > l || room.initLevel + 10 < l) continue;
                if(room.players.size() == m) continue;

                room.players.add(new Player(n, l));
                isAttended = true;
                break;
            }

            if (!isAttended) {
                rooms.add(new Room(l));
                rooms.get(rooms.size() - 1).players.add(new Player(n, l));
            }
        }

        for (Room room : rooms) {
            if (room.players.size() == m) System.out.println("Started!");
            else System.out.println("Waiting!");

            List<Player> players = room.players;
            Collections.sort(players);

            for (Player player : players) {
                System.out.println(player.level + " " + player.nickname);
            }
        }
    }
}
