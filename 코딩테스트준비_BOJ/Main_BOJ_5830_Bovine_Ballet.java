import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BOJ_5830_Bovine_Ballet {
    static class Foot {
        int x, y;

        public Foot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static int maxX, maxY, minX, minY;
    static char dir;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static Foot[] feet;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        feet = new Foot[4];
        feet[0] = new Foot(1, 1);
        feet[1] = new Foot(0, 1);
        feet[2] = new Foot(1, 0);
        feet[3] = new Foot(0, 0);

        Map<String, Integer> feetNum = new HashMap<>();
        feetNum.put("FR", 0);
        feetNum.put("FL", 1);
        feetNum.put("RR", 2);
        feetNum.put("RL", 3);

        maxX = maxY = 1;
        minX = minY = 0;

        dir = 'N';
        boolean isPossible = true;

        for (int i = 0; i < N; i++) {
            String command = br.readLine();
            String inFoot = command.substring(0, 2);
            String inst = command.substring(2, 3);
            int footNum = feetNum.get(inFoot);

            if(inst.equals("P")){
                if(dir == 'N') dir = 'E';
                else if(dir == 'E') dir = 'S';
                else if(dir == 'S') dir = 'W';
                else dir = 'N';

                for (int j = 0; j < 4; j++) {
                    if(j == footNum) continue;

                    int yDis = feet[j].y - feet[footNum].y;
                    int xDis = feet[j].x - feet[footNum].x;

                    feet[j].x = feet[footNum].x + yDis;
                    feet[j].y = feet[footNum].y - xDis;
                }
            }else {
                if (dir == 'N') {
                    if (inst.equals("F")) feet[footNum].y += 1;
                    if (inst.equals("B")) feet[footNum].y -= 1;
                    if (inst.equals("R")) feet[footNum].x += 1;
                    if (inst.equals("L")) feet[footNum].x -= 1;
                } else if (dir == 'S') {
                    if (inst.equals("F")) feet[footNum].y -= 1;
                    if (inst.equals("B")) feet[footNum].y += 1;
                    if (inst.equals("R")) feet[footNum].x -= 1;
                    if (inst.equals("L")) feet[footNum].x += 1;
                } else if (dir == 'E') {
                    if (inst.equals("F")) feet[footNum].x += 1;
                    if (inst.equals("B")) feet[footNum].x -= 1;
                    if (inst.equals("R")) feet[footNum].y -= 1;
                    if (inst.equals("L")) feet[footNum].y += 1;
                } else {
                    if (inst.equals("F")) feet[footNum].x -= 1;
                    if (inst.equals("B")) feet[footNum].x += 1;
                    if (inst.equals("R")) feet[footNum].y += 1;
                    if (inst.equals("L")) feet[footNum].y -= 1;
                }
            }

            for (int j = 0; j < 4; j++) {
                for (int k = j + 1; k < 4; k++) {
                    if(feet[j].x == feet[k].x && feet[j].y == feet[k].y) isPossible = false;
                }
            }

            for (int j = 0; j < 4; j++) {
                maxX = Math.max(feet[j].x, maxX);
                maxY = Math.max(feet[j].y, maxY);
                minX = Math.min(feet[j].x, minX);
                minY = Math.min(feet[j].y, minY);
            }
        }

        if(!isPossible) System.out.println(-1);
        else System.out.println((maxX - minX + 1) * (maxY - minY + 1));
    }

}
