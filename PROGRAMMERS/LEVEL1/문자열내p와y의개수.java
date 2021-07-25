import javax.xml.transform.SourceLocator;

public class 문자열내p와y의개수{
    public static void main(String[] args) {
        System.out.println(solution("PyY"));
    }

    static boolean solution(String s) {
        boolean answer = false;

        int pcnt = 0;
        int ycnt = 0;

        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == 'p' || s.charAt(i) == 'P'){
                pcnt++;
            }
            if(s.charAt(i) == 'y' || s.charAt(i) == 'Y'){
                ycnt++;
            }
        }

        if(pcnt == ycnt)
            answer = true;


        return answer;
    }
}