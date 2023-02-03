import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main_BOJ_2668_숫자고르기 {
    static int[] arr;
    static boolean[] isVisited;
    static List<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            int x = Integer.parseInt(br.readLine());
            arr[i] = x;
        }

        list = new LinkedList<>();
        isVisited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            isVisited[i] = true;
            DFS(i, i);
            isVisited[i] = false;
        }

        Collections.sort(list);
        System.out.println(list.size());
        for (int l : list) {
            System.out.println(l);
        }
    }

    private static void DFS(int index, int target) {
        if (arr[index] == target) {
            list.add(target);
        }

        if (!isVisited[arr[index]]) {
            isVisited[arr[index]] = true;
            DFS(arr[index], target);
            isVisited[arr[index]] = false;
        }
    }
}
