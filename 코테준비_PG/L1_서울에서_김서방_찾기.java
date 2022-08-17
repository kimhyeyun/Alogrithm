import java.util.Arrays;

public class L1_서울에서_김서방_찾기 {
    public String solution(String[] seoul) {
        int idx = Arrays.asList(seoul).indexOf("Kim");

        return "김서방은 " + idx + "에 있다";
    }
}
