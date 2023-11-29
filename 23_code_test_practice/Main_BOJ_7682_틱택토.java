import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_7682_틱택토 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int oCount, xCount;
        int oRawBingo, oColBingo;
        int xRawBingo, xColBingo;

        while (true) {
            String str = br.readLine();
            if(str.equals("end")) break;

            oCount = xCount = 0;
            oRawBingo = oColBingo = 0;
            xRawBingo = xColBingo = 0;

            char[][] board = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = str.charAt(i * 3 + j);
                    if(board[i][j] == 'O') oCount += 1;
                    else if(board[i][j] == 'X') xCount += 1;
                }
            }

            if (oCount > xCount || Math.abs(xCount - oCount) > 1) {
                sb.append("invalid").append("\n");
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

                    if(rowO == 3) oRawBingo += 1;
                    if(rowX == 3) xRawBingo += 1;
                    if(colX == 3) xColBingo += 1;
                    if(colO == 3) oColBingo += 1;
                }
            }

            int oCrossBingo = 0, xCrossBingo = 0;
            int crossX = 0, crossO = 0;
            for (int i = 0; i < 3; i++) {
                if (board[i][i] == 'X') crossX += 1;
                else if (board[i][i] == 'O') crossO += 1;

                if (crossX == 3) xCrossBingo += 1;
                if (crossO == 3) oCrossBingo += 1;
            }

            crossO = crossX = 0;
            for (int i = 2; i >= 0; i--) {
                if (board[2 - i][i] == 'X') crossX += 1;
                else if (board[2 - i][i] == 'O') crossO += 1;

                if(crossX == 3) xCrossBingo += 1;
                if(crossO == 3) oCrossBingo += 1;
            }

            if (xRawBingo > 1 || xColBingo > 1 || oRawBingo > 1 || oColBingo > 1) {
                sb.append("invalid").append("\n");
                continue;
            }

            if (xCount == oCount) {
                if (xRawBingo > 0 || xColBingo > 0 || xCrossBingo > 0) {
                    sb.append("invalid").append("\n");
                    continue;
                }
            } else if (xCount > oCount) {
                if (oRawBingo > 0 || oColBingo > 0 || oCrossBingo > 0) {
                    sb.append("invalid").append("\n");
                    continue;
                }
            }

            if (xCount + oCount == 9) {
                sb.append("valid").append("\n");
                continue;
            }

            if (xRawBingo + xColBingo + oRawBingo + oColBingo + xCrossBingo + oCrossBingo == 0) {
                sb.append("invalid").append("\n");
                continue;
            }

            sb.append("valid").append("\n");
        }
        System.out.println(sb);
    }
}
