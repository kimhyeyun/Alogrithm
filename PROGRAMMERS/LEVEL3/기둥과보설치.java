import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class 기둥과보설치 {

    static boolean[][] pillars;
    static boolean[][] beams;
    static LinkedList<result> ansList;
    static int N;

    static final int PILLAR = 0;
    static final int BEAM = 1;
    static final int CONSTRUCT = 1;
    static final int DECONSTRUCT = 0;

    public static class result implements Comparable<result>{
        int x;
        int y;
        int what;

        result(int x, int y, int what){
            this.x = x;
            this.y = y;
            this.what = what;
        }

        @Override
        public int compareTo(result o) {
            // TODO Auto-generated method stub
            int result = this.x - o.x;
            if(result == 0)
                result = this.y - o.y;
            if(result == 0)
                result = this.what - o.what;

            return result;
        }
    }

    public static int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};

        N = n+1;
        pillars = new boolean[n+1][n+1];
        beams = new boolean[n+1][n+1];
        ansList = new LinkedList<>();

        for(int[] f : build_frame){
            int x = f[0];
            int y = f[1];
            int what = f[2];
            int how = f[3];

            if(how == CONSTRUCT){
                if(what == PILLAR){
                    if(canPillarConstruct(x, y)){
                        pillars[x][y] = true;
                        ansList.add(new result(x, y, what));
                    }
                }
                else{
                    if(canBeamConstruct(x, y)){
                        beams[x][y] = true;
                        ansList.add(new result(x, y, what));
                    }
                }
            }
            else{
                DoDeconstruct(x, y, what);
                if(!allCheck()){
                    ansList.add(new result(x, y, what));
                    if(what == PILLAR)
                        pillars[x][y] = true;
                    else
                        beams[x][y] = true;
                }
            }
        }

        Collections.sort(ansList);
        answer = new int[ansList.size()][3];

        for(int i = 0 ; i < ansList.size() ; i++){
            answer[i][0] = ansList.get(i).x;
            answer[i][1] = ansList.get(i).y;
            answer[i][2] = ansList.get(i).what;

        }
        return answer;
    }


    private static boolean canBeamConstruct(int x, int y) {
        if(y == 0)
            return true;
        else if(0 <= x-1 && x+1 < N && beams[x-1][y] && beams[x+1][y])
            return true;
        else if(pillars[x][y-1] || pillars[x+1][y-1])
            return true;

        return false;
    }


    private static boolean allCheck() {

        Iterator<result> iterator = ansList.iterator();
        while(iterator.hasNext()){
            result r = iterator.next();

            if(r.what == PILLAR){
                if(!canPillarConstruct(r.x, r.y))
                    return false;
            }
            else{
                if(!canBeamConstruct(r.x, r.y))
                    return false;
            }
        }

        return true;
    }


    private static void DoDeconstruct(int x, int y, int what) {
        for(int i = 0 ; i < ansList.size() ; i++){
            if(x == ansList.get(i).x && y == ansList.get(i).y && what == ansList.get(i).what){
                ansList.remove(i);
                if(what == PILLAR)
                    pillars[x][y] = false;
                else
                    beams[x][y] = false;
                break;
            }
        }
    }


    private static boolean canPillarConstruct(int x, int y) {
        if(y == 0)
            return true;
        else if((0 <= x-1 && beams[x-1][y]) || beams[x][y])
            return true;
        else if(0 <= y-1 && pillars[x][y-1])
            return true;

        return false;
    }

    public static void main(String[] args) {
        int[][] build_frame = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
        int[][] answer = solution(5, build_frame);

        for(int[] ans : answer){
            System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
        }
    }
}
