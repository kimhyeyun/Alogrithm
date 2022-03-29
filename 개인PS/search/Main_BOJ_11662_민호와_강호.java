package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11662_민호와_강호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        double ax = Integer.parseInt(stringTokenizer.nextToken());
        double ay = Integer.parseInt(stringTokenizer.nextToken());
        double bx = Integer.parseInt(stringTokenizer.nextToken());
        double by = Integer.parseInt(stringTokenizer.nextToken());

        double cx = Integer.parseInt(stringTokenizer.nextToken());
        double cy = Integer.parseInt(stringTokenizer.nextToken());
        double dx = Integer.parseInt(stringTokenizer.nextToken());
        double dy = Integer.parseInt(stringTokenizer.nextToken());

        int interval = 1000000;
        double abx = (bx - ax) / interval;
        double aby = (by - ay) / interval;
        double cdx = (dx - cx) / interval;
        double cdy = (dy - cy) / interval;

        double min = getDistance(ax, ay, cx, cy);

        for (int i = 1; i <= interval; i++) {
            double tmp = getDistance(ax + abx * i, ay + aby * i, cx + cdx * i, cy + cdy * i);

            min = tmp < min ? tmp : min;
        }
        System.out.println(min);
    }

    private static double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
