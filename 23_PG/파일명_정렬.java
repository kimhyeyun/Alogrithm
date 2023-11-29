import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class 파일명_정렬 {

    public String[] solution(String[] files) {

        Arrays.sort(files, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String head1 = o1.split("[0-9]")[0];
                String head2 = o2.split("[0-9]")[0];

                int result = head1.toLowerCase().compareTo(head2.toLowerCase());

                if (result == 0) {
                    result = convert(o1, head1) - convert(o2, head2);
                }
                return result;
            }

            private int convert(String file, String head) {
                file = file.substring(head.length());
                String result = "";

                for (char c : file.toCharArray()) {
                    if (Character.isDigit(c) && result.length() < 5) {
                        result += c;
                    } else break;
                }

                return Integer.parseInt(result);
            }
        });

        return files;
    }

    @Test
    void test() {
        assertArrayEquals(solution(new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"}), new String[]{"img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png"});

    }
}
