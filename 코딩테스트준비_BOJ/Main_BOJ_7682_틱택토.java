import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_7682_틱택토 {
    static char[][] board;
    static int xRowBingo, xColBingo;
    static int oRowBingo, oColBingo;
    static int xCrossBingo, oCrossBingo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String str = br.readLine();
            if(str.equals("end")) break;

            xRowBingo = xColBingo = 0;
            oRowBingo = oColBingo = 0;
            xCrossBingo = oCrossBingo = 0;

            int xCount = 0, oCount = 0;

            board = new char[3][3];
            for (int i = 0; i < 9; i++) {
                board[i / 3][i % 3] = str.charAt(i);

                if(str.charAt(i) == 'X') xCount += 1;
                else if (str.charAt(i) == 'O')oCount += 1;
            }

            if (oCount > xCount || Math.abs(xCount - oCount) > 1) {
                System.out.println("invalid");
                continue;
            }

            for (int i = 0; i < 3; i++) {
                int rowX = 0, colX = 0;
                int rowO = 0, colO = 0;

                for (int j = 0; j < 3; j++) {
                    if(board[i][j] == 'X') rowX += 1;
                    else if(board[i][j] == 'O') rowO += 1;

                    if(board[j][i] == 'X') colX += 1;
                    else if(board[j][i] == 'O') colO += 1;

                    if(rowO == 3) oRowBingo += 1;
                    else if(rowX == 3) xRowBingo += 1;
                    else if(colX == 3) xColBingo += 1;
                    else if(colO == 3) oColBingo += 1;
                }
            }

            int crossX = 0, crossO = 0;
            for (int i = 0; i < 3; i++) {
                if(board[i][i] == 'X') crossX += 1;
                else if(board[i][i] == 'O') crossO += 1;

                if(crossO == 3) oCrossBingo += 1;
                if(crossX == 3) xCrossBingo += 1;
            }

            crossO = crossX = 0;
            int tmp = 0;
            for (int i = 2; i >= 0; i--) {
                if(board[i][tmp] == 'X') crossX += 1;
                else if(board[i][tmp] == 'O') crossO += 1;

                tmp += 1;

                if(crossO == 3) oCrossBingo += 1;
                if(crossX == 3) xCrossBingo += 1;
            }

            if (xRowBingo > 1 || xColBingo > 1 || oRowBingo > 1 || oColBingo > 1) {
                System.out.println("invalid");
                continue;
            }

            if (xCount == oCount) {
                if (xRowBingo > 0 || xColBingo > 0 || xCrossBingo > 0) {
                    System.out.println("invalid");
                    continue;
                }
            } else if (xCount > oCount) {
                if (oRowBingo > 0 || oColBingo > 0 || oCrossBingo > 0) {
                    System.out.println("invalid");
                    continue;
                }
            }

            if (xCount + oCount == 9) {
                System.out.println("valid");
                continue;
            }

            if (xRowBingo + xColBingo + oRowBingo + oColBingo + xCrossBingo + oCrossBingo == 0) {
                System.out.println("invalid");
                continue;
            }

            System.out.println("valid");
        }
    }
}
