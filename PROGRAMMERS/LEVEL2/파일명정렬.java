import java.util.Arrays;
import java.util.Comparator;

public class 파일명정렬 {
    public static String[] solution(String[] files) {

        // 이 경우는 내림차순
        Arrays.sort(files, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                String head1 = o1.split("[0-9]")[0];
                String head2 = o2.split("[0-9]")[0];

                int result = head2.toLowerCase().compareTo(head1.toLowerCase());

                if(result == 0){
                    // head가 같은 경우 
                    result = convertNum(o2, head2) - convertNum(o1, head1);
                }

                return result;
            }
        });

        // 이 경우는 오름차순
        Arrays.sort(files, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                String head1 = o1.split("[0-9]")[0];
                String head2 = o2.split("[0-9]")[0];

                int result = head1.toLowerCase().compareTo(head2.toLowerCase());

                if(result == 0){
                    // head가 같은 경우 
                    result = convertNum(o1, head1) - convertNum(o2, head2);
                }

                return result;
            }
        });

        return files;
    }
    protected static int convertNum(String file, String head) {
        file = file.substring(head.length());
        String result = "";
        
        for(char c : file.toCharArray()){
            if(Character.isDigit(c) && result.length() < 5){
                result += c;
            }
            else
                break;
        }

        return Integer.parseInt(result);
    }
    public static void main(String[] args) {
        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        String[] answer = solution(files);

        for(String a : answer){
            System.out.println(a);
        }
    }
}
