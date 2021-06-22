public class 신규아이디추천 {
    public static void main(String[] args) {
        System.out.println(solution("abcdefghijklmn.p"));
    }

    public static String solution(String new_id) {
        String answer = "";

        // 1. 소문자로 변경
        new_id = new_id.toLowerCase();

        // 2. 소문자, 숫자, -, _, . 제외하고 제거
        /* StringBuffer str = new StringBuffer(new_id);

        for (int i = 0; i < str.length(); i++) {
            if (97 <= str.charAt(i) && str.charAt(i) <= 122) {
                continue;
            } else if (49 <= str.charAt(i) && str.charAt(i) <= 57) {
                continue;
            } else {
                if (str.charAt(i) != '-' && str.charAt(i) != '_' & str.charAt(i) != '.') {
                    str.deleteCharAt(i);
                    i--;
                }
            }
        } 

        // 3. 연속된 . 찾기
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.') {
                if (i + 1 != str.length() && str.charAt(i + 1) == '.') {
                    str.deleteCharAt(i);
                    i--;
                }
            }
        }
        

        if (str.length() != 0) {
            // 4. 첫 글자나 마지막 글자가 .이면 삭제
            if (str.charAt(0) == '.') {
                str.deleteCharAt(0);
            }
            if (str.length() != 0 && str.charAt(str.length() - 1) == '.') {
                str.deleteCharAt(str.length() - 1);
            }
        }

        
        // 5. 빈문자인지
        if (str.length() == 0) {
            for (int i = 0; i < 3; i++) {
                str.append("a");
            }
        }

        // 6. 길이가 16 이상이면
        if (str.length() >= 16) {
            str.delete(15, str.length());
            if(str.charAt(str.length()-1) == '.')
                str.deleteCharAt(str.length()-1);
        }

        // 7. 길이가 2이하라면
        if (str.length() <= 2) {
            while (str.length() != 3) {
                str.append(str.charAt(str.length() - 1));
            }
        }

        answer = str.toString();
        */ // 처음 코드

        // 2단계
        char[] step_arr = new_id.toCharArray();
        StringBuilder str = new StringBuilder();

        for(char c : step_arr){
            if(('a' <= c && c <= 'z')|| ('0' <= c && c <= '9') || c == '-' || c == '_' || c == '.' ){
                str.append(c);
            }
        }

        // 3단계
        String id = str.toString();
        while(id.contains("..")){
            id = id.replace("..", ".");
        }

        // 4단계
        if(id.length() > 0){
            if(id.charAt(0) == '.')
                id = id.substring(1, id.length());
        }
        if(id.length() > 0){
            if(id.charAt(id.length()-1) == '.')
                id = id.substring(0, id.length()-1);
        }

        // 5단계
        if(id.equals("")){
            id = "a";
        }

        // 6단계
        if(id.length() >= 16){
            id = id.substring(0, 15);

            if(id.charAt(id.length()-1) == '.')
                id = id.substring(0, id.length()-1);
        }

        // 7단계
        str = new StringBuilder(id);
        if(str.length() <= 2){
            char last = str.charAt(str.length()-1);

            while(str.length() < 3){
                str.append(last);
            }
        }

        answer = String.valueOf(str);
        return answer;
    }
}
