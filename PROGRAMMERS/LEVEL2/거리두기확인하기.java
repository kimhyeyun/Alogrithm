
import java.util.LinkedList;
import java.util.Queue;

public class 거리두기확인하기 {
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static class node{
        int x;
        int y;

        public node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        String[][] p = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},{"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},{"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},{"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},{"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};

        int[] a = solution(p);

        for(int i : a){
            System.out.println(i);
        }
    }

    /* 
         P : 사람
         O : 빈 테이블
         X : 파티션
         places : 5X5
         return : 거리두기 준수 여부 ( 1: 지킴, 0 : 안지킴)
         맨해튼 거리 :  |x1-x2| + |y1-y2|
     */
    public static int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for(int i = 0;i < places.length;i++){
            answer[i] = isCorrect(places[i]);
        }

        return answer;
    }

    private static int isCorrect(String[] room) {
        for(int i = 0;i < room.length;i++){
            for(int j = 0;j < room[i].length();j++){
                if(room[i].charAt(j) == 'P'){
                    if(!bfs(room, i, j))
                        return 0;
                }
            }
        }
        return 1;
    }

    private static boolean bfs(String[] room, int x, int y) {
        Queue<node> q = new LinkedList<>();
        boolean[][] visited = new boolean[room.length][room.length];
        q.add(new node(x,y));
        visited[x][y] = true;

        while(!q.isEmpty()){
            node cur = q.poll();

            for(int i = 0; i < 4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int dis = Math.abs(x-nx) + Math.abs(y-ny);

                if(nx < 0 || ny < 0 || nx >= room.length || ny >= room.length)
                    continue;
                if(visited[nx][ny] || dis > 2)
                    continue;

                visited[nx][ny] = true;
                if(room[nx].charAt(ny) == 'X')
                    continue;
                else if(room[nx].charAt(ny) == 'P')
                    return false;
                else
                    q.add(new node(nx,ny));
            }
        }
        return true;
    }
}
