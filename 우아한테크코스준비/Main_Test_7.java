public class Main_Test_7 {
    public static String[] solution(String[] grid, boolean clockwise) {
        StringBuilder sb = new StringBuilder();
        String[] answer = new String[grid.length];

        int n = grid.length;
        /*
         * char[][] grids = new char[n][n]; for(int i = 0 ; i < grid.length ; i++){
         * for(int j = 0; j < grid[i].length() ; j++){ grids[i][j] = grid[i].charAt(j);
         * } }
         */

        if (clockwise) {
            for (int a = 0; a < n; a++) {
                sb = new StringBuilder();
                int cnt = 1;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < 2; j++) {
                        if (i < a)
                            break;
                        if (a == i) {
                            sb.append(grid[i].charAt(0));
                            break;
                        } else {
                            sb.append(grid[i].charAt(cnt++));
                        }
                    }
                }
                answer[n - a - 1] = sb.reverse().toString();
            }
        }

        else{
            int cnt = 0;
            for (int a = 0; a < n; a++) {
                sb = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    while(true) {
                        if (i < a)
                            break;
                        if(i == a){
                            sb.append(grid[i].charAt(cnt));
                            break;
                        }
                        else{
                            sb.append(grid[i].charAt(cnt+1));
                            sb.append(grid[i].charAt(cnt));
                            break;
                        }
                    }
                }
                cnt += 2;
                answer[n - a - 1] = sb.toString();
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        // String[] grid = { "1", "234", "56789" };
        String[] grid = {"A","MAN","DRINK","WATER11"};
        String[] result = solution(grid, false);

        for (String r : result)
            System.out.println(r);
    }
}
