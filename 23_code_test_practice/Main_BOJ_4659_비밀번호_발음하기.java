import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_4659_비밀번호_발음하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String command = br.readLine();
            if(command.equals("end")) break;

            if(!isContainsVowel(command)) System.out.println("<" + command + ">" + " is not acceptable.");
            else if(isTripleContinuous(command)) System.out.println("<" + command + ">" + " is not acceptable.");
            else if(isContinuousSame(command)) System.out.println("<" + command + ">" + " is not acceptable.");
            else System.out.println("<" + command + ">" + " is acceptable.");
        }
    }

    private static boolean isContinuousSame(String command) {
        for (int i = 1; i < command.length(); i++) {
            if (command.charAt(i - 1) == command.charAt(i)) {
                if(command.charAt(i) == 'e' || command.charAt(i) == 'o') continue;
                else return true;
            }
        }
        return false;
    }

    private static boolean isTripleContinuous(String command) {
        int vowelCount = 0, consonantCount = 0;

        for (int i = 0; i < command.length(); i++) {
            if (isVowel(command.charAt(i))) {
                vowelCount += 1;
                consonantCount = 0;
            } else {
                consonantCount += 1;
                vowelCount = 0;
            }

            if(vowelCount >= 3 || consonantCount >= 3) return true;
        }

        return false;
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    private static boolean isContainsVowel(String command) {
        return command.contains("a") || command.contains("e") || command.contains("i") || command.contains("o") || command.contains("u");
    }
}
