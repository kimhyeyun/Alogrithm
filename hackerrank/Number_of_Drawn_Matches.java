import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.google.gson.*;

public class Number_of_Drawn_Matches {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

//        int year = Integer.parseInt(bufferedReader.readLine().trim());
//
//        int result = getNumDraws(year);

        String competition = bufferedReader.readLine();

        int year = Integer.parseInt(bufferedReader.readLine().trim());

        int result = getWinnerTotalGoals(competition, year);

        System.out.println(result);
    }

    private static int getNumDraws(int year) {
        int page = 1;
        int totalPage = 1;
        int result = 0;

        while (page <= totalPage) {
            for (int i = 0; i <= 10; i++) {
                String URL = "https://jsonmock.hackerrank.com/api/football_matches?year=" + year + "&page=" + page +
                        "&team1goals=" + i + "&team2goals=" + i;
                try {
                    URL url = new URL(URL);

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.connect();

                    int responseCode = con.getResponseCode();

                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = con.getInputStream();
                        InputStreamReader reader = new InputStreamReader(inputStream);

                        Gson gson = new Gson();
                        JsonObject responseJson = gson.fromJson(reader, JsonObject.class);
                        totalPage = responseJson.get("total_pages").getAsInt();

                        JsonArray data = responseJson.getAsJsonArray("data");

                        result += responseJson.get("total").getAsInt();
                        System.out.println(responseJson.get("total").getAsInt());

                        page += 1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /*
     * Complete the 'getWinnerTotalGoals' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING competition
     *  2. INTEGER year
     */

    public static int getWinnerTotalGoals(String competition, int year) {
        int answer = 0;

        String c = competition.replaceAll(" ", "%20");
        String winner = getWinner(c, year).replaceAll(" ", "%20");

        System.out.println(winner);

        answer += getGoal(c, year, winner, 1);
        answer += getGoal(c, year, winner, 2);

        return answer;
    }


    private static int getGoal(String competition, int year, String winner, int teamNumber) {
        int page = 1, totalPage = 1;
        int sum = 0;

        while (page <= totalPage) {
            try {
                String URL = "https://jsonmock.hackerrank.com/api/football_matches?competition=" + competition + "&year=" + year;

                if (teamNumber == 1) URL += "&team1=" + winner + "&page=" + page;
                else URL += "&team2=" + winner + "&page=" + page;

                URL url = new URL(URL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                int responseCode = con.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = con.getInputStream();
                    InputStreamReader reader = new InputStreamReader(inputStream);

                    Gson gson = new Gson();
                    JsonObject responseJson = gson.fromJson(reader, JsonObject.class);
                    totalPage = responseJson.get("total_pages").getAsInt();

                    JsonArray data = responseJson.getAsJsonArray("data");
//
                    System.out.println("teamNumber = " + teamNumber);
//
                    System.out.println("page : " + page + " totalPages : " + totalPage);

                    for (JsonElement ele : data) {
                        JsonObject obj = ele.getAsJsonObject();

                        int team1Goal = obj.get("team1goals").getAsInt();
                        int team2Goal = obj.get("team2goals").getAsInt();

//                        if (teamNumber == 1 && team1Goal > team2Goal) sum += team1Goal;
//                        else if (teamNumber == 2 && team2Goal > team1Goal) sum += team2Goal;

                        if(teamNumber == 1) {
                            sum += team1Goal;
                            System.out.println(team1Goal);
                        }
                        else{
                            sum += team2Goal;
                            System.out.println(team2Goal);
                        }
                    }
                }
                page += 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sum;
    }

    private static String getWinner(String competition, int year) {
        String URL = "https://jsonmock.hackerrank.com/api/football_competitions?name="
                + competition + "&year=" + year;

        String winner = "";
        try {
            URL url = new URL(URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = con.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);

                Gson gson = new Gson();
                JsonObject responseJson = gson.fromJson(reader, JsonObject.class);
                JsonArray data = responseJson.getAsJsonArray("data");

                winner = data.get(0).getAsJsonObject().get("winner").getAsString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return winner;
    }

}
