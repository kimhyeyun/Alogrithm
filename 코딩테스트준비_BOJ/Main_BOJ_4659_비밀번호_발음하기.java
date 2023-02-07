import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_4659_비밀번호_발음하기 {
    static final String NOT = " is not acceptable.";
    static final String GOOD = " is acceptable.";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String password = br.readLine();

            if (password.equals("end")) {
                return;
            }

            if (!isContainsVowel(password)) {
                System.out.println("<" + password + ">" + NOT);
            } else if (isThreeContinuous(password)) {
                System.out.println("<" + password + ">" + NOT);
            } else if (isContinuousSame(password)) {
                System.out.println("<" + password + ">" + NOT);
            } else {
                System.out.println("<" + password + ">" + GOOD);
            }
        }

    }

    private static boolean isContinuousSame(String password) {
        for (int i = 1; i < password.length(); i++) {
            if (password.charAt(i) == password.charAt(i - 1)) {
                if (password.charAt(i) == 'e' || password.charAt(i) == 'o') {
                    continue;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isThreeContinuous(String password) {
        int vCount = 0;
        int cCount = 0;

        for (int i = 0; i < password.length(); i++) {
            if (isVowel(password.charAt(i))) {
                vCount += 1;
                cCount = 0;
            } else {
                cCount += 1;
                vCount = 0;
            }

            if (cCount >= 3 || vCount >= 3) {
                return true;
            }
        }
        return false;
    }

    private static boolean isVowel(char c) {
        if (c == 'a' || c == 'o' || c == 'i' || c == 'e' || c == 'u') {
            return true;
        }
        return false;
    }

    private static boolean isContainsVowel(String password) {
        if (password.contains("a") || password.contains("e") || password.contains("i") || password.contains("o") || password.contains("u")) {
            return true;
        }
        return false;
    }
}
