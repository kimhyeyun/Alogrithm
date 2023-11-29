public class Main_4 {
    public static int[] solution(String[] rsp3) {
        int[] answer = new int[3];

        for (String r : rsp3) {
            if(r.charAt(0) == r.charAt(1) && r.charAt(1) == r.charAt(2) && r.charAt(0) == r.charAt(2)) continue;
            if(r.charAt(0)!= r.charAt(1) && r.charAt(1) != r.charAt(2) && r.charAt(0) != r.charAt(2)) continue;

            char player1 = r.charAt(0);
            char player2 = r.charAt(1);
            char player3 = r.charAt(2);

            if (player1 == player2) {
                switch (player1) {
                    case 'P':
                        if(player3 == 'S') answer[2] += 2;
                        else if (player3 == 'R') {
                            if (answer[0] == answer[1]) {
                                answer[0] += 1;
                                answer[1] += 1;
                            } else if(answer[0] < answer[1]) answer[0] += 2;
                            else answer[1] += 2;
                        }
                        break;
                    case 'S':
                        if(player3 == 'R') answer[2] += 2;
                        else if (player3 == 'P') {
                            if (answer[0] == answer[1]) {
                                answer[0] += 1;
                                answer[1] += 1;
                            } else if(answer[0] < answer[1]) answer[0] += 2;
                            else answer[1] += 2;
                        }
                        break;
                    case 'R':
                        if(player3 == 'P') answer[2] += 2;
                        else if (player3 == 'S') {
                            if (answer[0] == answer[1]) {
                                answer[0] += 1;
                                answer[1] += 1;
                            } else if(answer[0] < answer[1]) answer[0] += 2;
                            else answer[1] += 2;
                        }
                        break;
                }

            } else if (player1 == player3) {
                switch (player1) {
                    case 'P':
                        if(player2 == 'S') answer[1] += 2;
                        else if (player2 == 'R') {
                            if (answer[0] == answer[2]) {
                                answer[0] += 1;
                                answer[2] += 1;
                            } else if(answer[0] < answer[2]) answer[0] += 2;
                            else answer[2] += 2;
                        }
                        break;
                    case 'S':
                        if(player2 == 'R') answer[1] += 2;
                        else if (player2 == 'P') {
                            if (answer[0] == answer[2]) {
                                answer[0] += 1;
                                answer[2] += 1;
                            } else if(answer[0] < answer[2]) answer[0] += 2;
                            else answer[2] += 2;
                        }
                        break;
                    case 'R':
                        if(player2 == 'P') answer[1] += 2;
                        else if (player2 == 'S') {
                            if (answer[0] == answer[2]) {
                                answer[0] += 1;
                                answer[2] += 1;
                            } else if(answer[0] < answer[2]) answer[0] += 2;
                            else answer[2] += 2;
                        }
                        break;
                }

            } else if (player2 == player3) {
                switch (player2) {
                    case 'P':
                        if(player1 == 'S') answer[0] += 2;
                        else if (player1 == 'R') {
                            if (answer[2] == answer[1]) {
                                answer[2] += 1;
                                answer[1] += 1;
                            } else if(answer[2] < answer[1]) answer[2] += 2;
                            else answer[1] += 2;
                        }
                        break;
                    case 'S':
                        if(player1 == 'R') answer[0] += 2;
                        else if (player1 == 'P') {
                            if (answer[2] == answer[1]) {
                                answer[2] += 1;
                                answer[1] += 1;
                            } else if(answer[2] < answer[1]) answer[2] += 2;
                            else answer[1] += 2;
                        }
                        break;
                    case 'R':
                        if(player1 == 'P') answer[0] += 2;
                        else if (player1 == 'S') {
                            if (answer[2] == answer[1]) {
                                answer[2] += 1;
                                answer[1] += 1;
                            } else if(answer[2] < answer[1]) answer[2] += 2;
                            else answer[1] += 2;
                        }
                        break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] resp3 = {"RRR", "SPS"};
        int[] ans = solution(resp3);

        for (int a : ans) {
            System.out.println(a);
        }
    }
}
