public class 방문길이 {

    // U D R L
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static boolean[][][][] visited = new boolean[11][11][11][11];

    public static int solution(String dirs) {
        int answer = 0;
        
        int x = 0;
        int y = 0;

        int nx = 5;
        int ny = 5;
        
        int dir = 0;

        for(int i = 0; i < dirs.length(); i++){
            x = nx;
            y = ny;

            if(dirs.charAt(i) == 'U')
                dir = 0;
            else if(dirs.charAt(i) == 'D')
                dir = 1;
            else if(dirs.charAt(i) == 'R')
                dir = 2;
            else
                dir = 3;

            nx += dx[dir];
            ny += dy[dir];

            if(nx < 0 || ny < 0 || 10 < nx || 10 < ny){
                nx -= dx[dir];
                ny -= dy[dir];
                continue;
            }

            if(!visited[x][y][nx][ny] && ! visited[nx][ny][x][y]){
                visited[x][y][nx][ny] = true;
                visited[nx][ny][x][y] = true;
                answer++;
            }
        }

        return answer;
    }
 
    public static void main(String[] args) {
        System.out.println(solution("ULURRDLLU"));
    }
}
