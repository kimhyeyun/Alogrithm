public class Main_Test_6 {
    public static String solution(double time, String[][] plans) {
        String answer = "";
        int FriTime = 18;
        int MonTime = 13;
        // 금퇴 -> 6pm, 월출근 -> 1pm

        int departTime = 0;
        int arriveTime = 0;
        for(String[] plan : plans){
            if(plan[1].contains("AM")){
                // 출발 시간이 AM
                departTime = Integer.parseInt(plan[1].substring(0, plan[1].indexOf('A')));
            }
            else{
                departTime = Integer.parseInt(plan[1].substring(0, plan[1].indexOf('P'))) + 12;
            }
            
            if(plan[2].contains("AM")){
                // 도착 시간이 AM
                arriveTime = Integer.parseInt(plan[2].substring(0, plan[2].indexOf('A')));
            }
            else{
                arriveTime = Integer.parseInt(plan[2].substring(0, plan[2].indexOf('P'))) + 12;
            }

            double tmp = 0;
            tmp += departTime < FriTime ? FriTime - departTime : 0;
            tmp += arriveTime > MonTime ? arriveTime - MonTime : 0;

            if(time < tmp)
                break;
            else{ 
                answer = plan[0];
                time -= tmp;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[][] plans = {{"홍콩", "11PM", "9AM"}, {"엘에이", "11PM", "1PM"}};
        System.out.println(solution(3.5, plans));
    }
}
