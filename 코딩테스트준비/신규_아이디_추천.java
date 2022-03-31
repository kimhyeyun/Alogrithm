public class 신규_아이디_추천 {
    public static String solution(String new_id) {
        String id = new_id.toLowerCase();

        StringBuilder sb = new StringBuilder();
        char[] idArr = id.toCharArray();
        for (char i : idArr) {
            if(('a' <= i && i <= 'z') || ('0'<= i && i <= '9') || i == '-' || i == '_' || i == '.')
                sb.append(i);
        }

        id = sb.toString();
        while (id.contains("..")) {
            id = id.replace("..", ".");
        }

        if (id.length() > 0) {
            if(id.charAt(0) == '.') id = id.substring(1);
        }
        if (id.length() > 0) {
            if(id.charAt(id.length()-1) == '.') id = id.substring(0, id.length() - 1);
        }

        if(id.length() <= 0) id = "a";
        if(id.length() > 15) id = id.substring(0, 15);
        if(id.charAt(id.length()-1) == '.') id = id.substring(0, id.length() - 1);

        while (id.length() < 3) {
            id += id.charAt(id.length() - 1);
        }

        return id;
    }

    public static void main(String[] args) {
        System.out.println(solution("=.="));
    }
}
