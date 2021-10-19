import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class 단속카메라 {
    public static int solution(int[][] routes) {
        int answer = 0;

        ArrayList<int[]> carList = new ArrayList<>();
        for(int[] r : routes){
            carList.add(r);
        }

        Collections.sort(carList, (a,b) -> a[1] - b[1]);

        while(!carList.isEmpty()){
            int nowCarExit = carList.get(0)[1];

            for(int i =  0 ; i < carList.size() ; i++){
                if(carList.get(i)[0] <= nowCarExit){
                    carList.remove(i);
                    i--;
                }
            }

            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] routes = {{-20, -15}, {-14,-5}, {-18,-13}, {-5,-3}};
        System.out.println(solution(routes));
    }
}
