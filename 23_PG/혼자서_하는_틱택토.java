import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 혼자서_하는_틱택토 {
    public int solution(String[] board) {

        int OCount = 0, XCount = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                if(board[i].charAt(j) == 'O') OCount += 1;
                else if(board[i].charAt(j) =='X') XCount += 1;
            }
        }

        if(XCount > OCount) return 0;
        if(OCount - XCount > 1) return 0;
        if(XCount == 0 && OCount == 0) return 1;

        boolean isCompletedOfO = false, isCompletedOfX = false;

        for (int i = 0; i < 3; i++) {
            if(board[i].charAt(0) != '.' && board[i].charAt(1) != '.' && board[i].charAt(2) != '.') {
                if (board[i].charAt(0) == board[i].charAt(1) && board[i].charAt(0) == board[i].charAt(2)) {
                    if (board[i].charAt(0) == 'O') isCompletedOfO = true;
                    else isCompletedOfX = true;
                }
            }

            if(board[0].charAt(i) != '.' && board[1].charAt(i) != '.' && board[2].charAt(i) != '.') {
                if (board[0].charAt(i) == board[1].charAt(i) && board[0].charAt(i) == board[2].charAt(i)) {
                    if (board[0].charAt(i) == 'O') isCompletedOfO = true;
                    else isCompletedOfX = true;
                }
            }
        }

        if(board[0].charAt(0) != '.' && board[1].charAt(1) != '.' && board[2].charAt(2) != '.'){
            if (board[0].charAt(0) == board[1].charAt(1) && board[0].charAt(0) == board[2].charAt(2)) {
                if(board[0].charAt(0) == 'O') isCompletedOfO = true;
                else isCompletedOfX = true;
            }
        }

        if(board[0].charAt(2) != '.' && board[1].charAt(1) != '.' && board[2].charAt(0) != '.'){
            if (board[0].charAt(2) == board[1].charAt(1) && board[0].charAt(2) == board[2].charAt(0)) {
                if(board[0].charAt(2) == 'O') isCompletedOfO = true;
                else isCompletedOfX = true;
            }
        }

        if(isCompletedOfO && isCompletedOfX) return 0;
        if(isCompletedOfO && OCount == XCount) return 0;
        if(isCompletedOfX && OCount > XCount) return 0;
        return 1;
    }

    @Test
    void test() {
//        assertEquals(solution(new String[]{"O.X", ".O.", "..X"}), 1);
//        assertEquals(solution(new String[]{"OOO", "...", "XXX"}), 0);
//        assertEquals(solution(new String[]{"...", ".X.", "..."}), 0);
//        assertEquals(solution(new String[]{"...", "...", "..."}), 1);
//        assertEquals(solution(new String[]{"XOX", "OOO", "XOX"}), 1);
//
//        String[][] str = {
//                {"OXO", "XOX", "OXO"},
//                {"OOX", "XXO", "OOX"},
//                {"XXX", ".OO", "O.."},
//                {"OX.", ".O.", ".XO"},
//                {"...", "...", ".O."},
//                {"X.X", "X.O", "O.O"},
//                {"XO.", "OXO", "XOX"},
//                {"OOO", "XOX", "XXO"},
//                {"OOO", "XOX", "X.X"},
//                {"XXX", "OO.", "OO."},
//                {".X.", "...", "..."},
//                {".X.", "X..", ".O."},
//                {"XOX", "OXO", "XOX"},
//                {"XXX", "XOO", "OOO"},
//                {"OOX", "OXO", "XOO"},
//                {"OOX", "OXO", "XOX"},
//                {".OX", "OXO", "XO."},
//                {"OOO", "XX.", "X.."}
//        };
//
//
//        for (int i = 0; i < str.length; i++) {
//            System.out.println(solution(str[i]) == 1 ? true : false);
//        }

        assertEquals(solution(new String[]{"...", ".X.", "..."}), 0);

    }
}
