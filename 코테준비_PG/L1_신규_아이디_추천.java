public class L1_신규_아이디_추천 {
    public static String solution(String new_id) {
        new_id = new_id.toLowerCase();

        String id = "";
        for (char c : new_id.toCharArray()) {
            if('a' <= c && c <= 'z') id += c;
            else if('0' <= c && c <= '9') id += c;
            else if(c == '-' || c == '_' || c == '.') id += c;
        }

        while (id.contains("..")) {
            id = id.replace("..", ".");
        }

        if (id.length() > 0 && id.charAt(0) == '.') id = id.substring(1, id.length());
        if (id.length() > 0 && id.charAt(id.length() - 1) == '.') id = id.substring(0, id.length() - 1);

        if(id.length() == 0) id = "a";
        if(id.length() > 15) id = id.substring(0, 15);

        if(id.charAt(id.length()-1) == '.') id = id.substring(0, id.length()-1);

        if (id.length() < 3) {
            while(id.length() < 3) id += id.charAt(id.length() - 1);
        }

        return id;
    }

    public static void main(String[] args) {
        String new_id = "abcdefghijklmn.p";

        System.out.println(solution(new_id));
    }
}
