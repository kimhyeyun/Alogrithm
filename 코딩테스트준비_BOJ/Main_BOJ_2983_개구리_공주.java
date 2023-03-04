import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_2983_개구리_공주 {
    static class Plant implements Comparable<Plant>{
        long x, y;

        public Plant(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Plant o) {
            return (int) (this.x - o.x);
        }
    }
    static long N, K;
    static char[] jumpDirs;
    static int[][] map;
    static int[] dx = {1, 1, -1, -1};
    static int[] dy = {1, -1, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());

        jumpDirs = br.readLine().toCharArray();

        st = new StringTokenizer(br.readLine());
        long[] firstFrogPoint = new long[]{Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())};
        long filter = (firstFrogPoint[0] + firstFrogPoint[1]) % 2;

        Map<Long, TreeSet<Plant>> sumMap = new HashMap<>();
        Map<Long, TreeSet<Plant>> diffMap = new HashMap<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            if ((x + y) % 2 == filter) {
                Plant plant = new Plant(x, y);
                TreeSet<Plant> sumList = sumMap.get(x + y);

                if(sumList == null) sumList = new TreeSet<>();

                sumList.add(plant);
                sumMap.put(x + y, sumList);

                TreeSet<Plant> diffList = diffMap.get(x - y);
                if(diffList == null) diffList = new TreeSet<>();

                diffList.add(plant);
                diffMap.put(x - y, diffList);
            }
        }

        long loopX = firstFrogPoint[0];
        long loopY = firstFrogPoint[1];

        for (char dir : jumpDirs) {
            long key = (dir == 'A' || dir == 'D') ? loopX - loopY : loopX + loopY;

            TreeSet<Plant> vList = (dir == 'A' || dir == 'D') ? diffMap.get(key) : sumMap.get(key);

            if(vList == null || vList.size() == 0) continue;

            boolean isXAdd = (dir == 'A' || dir == 'B') ? true : false;
            Plant exPoint = new Plant(loopX, loopY);
            Plant hitPoint = (isXAdd) ? vList.higher(exPoint) : vList.lower(exPoint);

            if(hitPoint == null) continue;

            loopX = hitPoint.x;
            loopY = hitPoint.y;
            vList.remove(hitPoint);

            if(vList.size() == 0) vList = null;

            if (dir == 'A' || dir == 'D') {
                diffMap.put(key, vList);

                TreeSet<Plant> dlist = sumMap.get(hitPoint.x + hitPoint.y);
                dlist.remove(hitPoint);

                if (dlist == null || dlist.size() == 0) vList = null;

                sumMap.put(hitPoint.x + hitPoint.y, dlist);
            } else {
                sumMap.put(key, vList);

                TreeSet<Plant> dlist = diffMap.get(hitPoint.x - hitPoint.y);
                dlist.remove(hitPoint);

                if(dlist == null || dlist.size() == 0) dlist = null;

                diffMap.put(hitPoint.x - hitPoint.y, dlist);
            }
        }
        System.out.println(loopX + " " + loopY);

    }
}
