import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class L3_리틀_프렌즈_사천성 {
    List<Character> tiles;
    Map<Character, int[][]> tilesIndex;
    char[][] board;
    public String solution(int m, int n, String[] board) {
        String answer = "";

        tiles = new ArrayList<>();
        tilesIndex = new HashMap<>();
        this.board = new char[m][n];

        for (int i = 0; i < m; i++) {
            this.board[i] = board[i].toCharArray();
            for (int j = 0; j < n; j++) {
                char c = board[i].charAt(j);
                if ('A' <= c && c <= 'Z') {
                    if (tiles.contains(c)) {
                        tilesIndex.get(c)[1][0] = i;
                        tilesIndex.get(c)[1][1] = j;
                    } else {
                        tiles.add(c);
                        tilesIndex.put(c, new int[2][2]);
                        tilesIndex.get(c)[0][0] = i;
                        tilesIndex.get(c)[0][1] = j;
                    }
                }
            }
        }

        Collections.sort(tiles);
        int idx = 0;

        while (tiles.size() > 0) {
            if (canRemove(tiles.get(idx))) {
                char popped = tiles.remove(idx);
                answer += popped;
                deleteChar(popped);
                idx = 0;
            } else {
                idx += 1;
                if (idx == tiles.size()) {
                    return "IMPOSSIBLE";
                }
            }
        }
        return answer;
    }

    private void deleteChar(char a) {
        board[tilesIndex.get(a)[0][0]][tilesIndex.get(a)[0][1]] = '.';
        board[tilesIndex.get(a)[1][0]][tilesIndex.get(a)[1][1]] = '.';
    }

    private boolean canRemove(char c) {
        int x1 = tilesIndex.get(c)[0][0];
        int y1 = tilesIndex.get(c)[0][1];
        int x2 = tilesIndex.get(c)[1][0];
        int y2 = tilesIndex.get(c)[1][1];

        if (y1 < y2) {
            if (linearColumnCheck(y1, y2, x1, c) && linearRowCheck(x1, x2, y2, c)) {
                return true;
            }
            if (linearRowCheck(x1, x2, y1, c) && linearColumnCheck(y1, y2, x2, c)) {
                return true;
            }
        } else {
            if (linearRowCheck(x1, x2, y2, c) && linearColumnCheck(y2, y1, x1, c)) {
                return true;
            }
            if (linearColumnCheck(y2, y1, x2, c) && linearRowCheck(x1, x2, y1, c)) {
                return true;
            }
        }
        return false;
    }

    private boolean linearRowCheck(int x1, int x2, int y, char c) {
        for (int i = x1; i <= x2; i++) {
            if (board[i][y] != '.' && board[i][y] != c) {
                return false;
            }
        }
        return true;
    }

    private boolean linearColumnCheck(int y1, int y2, int x, char c) {
        for (int i = y1; i <= y2; i++) {
            if (board[x][i] != '.' && board[x][i] != c) {
                return false;
            }
        }
        return true;
    }

    @Test
    void test() {
        assertEquals("ABCD", solution(3, 3, new String[]{"DBA", "C*A", "CDB"}));
        assertEquals("RYAN", solution(2, 4, new String[]{"NRYN", "ARYA"}));
        assertEquals("MUZI", solution(4, 4, new String[]{".ZI.", "M.**", "MZU.", ".IU."}));
        assertEquals("IMPOSSIBLE", solution(2, 2, new String[]{"AB", "BA"}));
        assertEquals("AZ", solution(2, 2, new String[]{"ZA", "ZA"}));
        assertEquals("A", solution(1, 2, new String[]{"AA"}));
        assertEquals("IMPOSSIBLE", solution(3, 3, new String[]{"A.B", "B.A", "C.C"}));
        assertEquals("ZAB", solution(3, 3, new String[]{"AZA", "BZB", "***"}));
        assertEquals( "CBA", solution(1, 7, new String[]{"ABC.CBA"}));

    }
}
