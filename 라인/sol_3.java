import java.util.Arrays;

public class sol_3 {
    public static int[] solution(int num_teams, String[] remote_tasks, String[] office_tasks, String[] employees) {
        int[] answer = {};

        boolean[] teams = new boolean[num_teams + 1];
        int[] teamOfEmployee = new int[employees.length];

        for (int i = 1; i <= employees.length; i++) {
            String[] list = employees[i - 1].split(" ");
            teamOfEmployee[i] = Integer.parseInt(list[0]);

            for (int j = 1; j < list.length; j++) {
                /*if (Arrays.stream(office_tasks).anyMatch(list[j])) {

                }*/
            }
        }

        return answer;
    }
    public static void main(String[] args) {

        String[] remote_tasks = {"development", "marketing", "hometask"};
        String[] office_tasks = {"recruitment", "education", "officetask"};
        String[] employees = {"1 development hometask", "1 recruitment marketing", "2 hometask", "2 development marketing hometask", "3 marketing", "3 officetask", "3 development"};

        solution(3, remote_tasks, office_tasks, employees);
    }
}
