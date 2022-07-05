import java.util.Arrays;

public class 시험장_나누기 {
    static int n, root, cnt;
    static int[] parent, left, right, people;
    public static int solution(int k, int[] num, int[][] links) {

        n = num.length;
        parent = new int[n];
        left = new int[n];
        right = new int[n];
        people = num;

        Arrays.fill(parent, -1);

        for (int i = 0; i < links.length; i++) {
            left[i] = links[i][0];
            right[i] = links[i][1];

            if(left[i] != -1) parent[left[i]] = i;
            if(right[i] != -1) parent[right[i]] = i;
        }

        for (int i = 0; i < n; i++) {
            if (parent[i] == -1) {
                root = i;
                break;
            }
        }

        int lo = 0, hi = 10001;
        for (int i = 0; i < n; i++) {
            lo = Math.max(lo, people[i]);
        }
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (getGroup(mid) <= k) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return hi + 1;
    }

    private static int getGroup(int limit) {
        cnt = 0;
        DFS(root, limit);

        return cnt + 1;
    }

    private static int DFS(int cur, int limit) {
        int l = 0, r = 0;
        if (left[cur] != -1) {
            l = DFS(left[cur], limit);
        }
        if (right[cur] != -1) {
            r = DFS(right[cur], limit);
        }

        if(people[cur] + l + r <= limit) return people[cur] + l + r;
        if (people[cur] + Math.min(l, r) <= limit) {
            cnt += 1;
            return people[cur] + Math.min(l, r);
        }
        cnt += 2;
        return people[cur];
    }

    public static void main(String[] args) {
        int[] num = {12, 30, 1, 8, 8, 6, 20, 7, 5, 10, 4, 1};
        int[][] links = {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {8, 5}, {2, 10}, {3, 0}, {6, 1}, {11, -1}, {7, 4}, {-1, -1}, {-1, -1}};

        solution(3, num, links);
    }
}
