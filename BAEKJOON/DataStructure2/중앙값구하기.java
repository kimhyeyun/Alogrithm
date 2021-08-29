package DataStructure2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 중앙값구하기 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer;

        int testCase = Integer.parseInt(br.readLine());

        // 시간 초과
        /*
         * for(int t = 0; t < testCase; t++){ int M = Integer.parseInt(br.readLine());
         * List<Integer> list = new LinkedList<>(); int nowIdx = 0;
         * 
         * sb.append(M/2+1).append("\n"); stringTokenizer = new
         * StringTokenizer(br.readLine());
         * 
         * for(int i = 1; i <= M; i++){ int x =
         * Integer.parseInt(stringTokenizer.nextToken()); list.add(x);
         * Collections.sort(list);
         * 
         * if(i%2 != 0){ sb.append(list.get(i/2) + " "); nowIdx++; }
         * 
         * if(nowIdx == 10){ sb.append("\n"); nowIdx = 0; } if(i%10 == 0)
         * stringTokenizer = new StringTokenizer(br.readLine()); }
         * 
         * sb.append("\n"); }
         */

        while (testCase-- > 0) {
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();

            int M = Integer.parseInt(br.readLine());
            int cnt = 0;
            stringTokenizer = new StringTokenizer(br.readLine());
            sb.append(M / 2 + 1).append("\n");

            for (int i = 1; i <= M; i++) {
                int x = Integer.parseInt(stringTokenizer.nextToken());

                if (maxHeap.size() == minHeap.size())
                    maxHeap.offer(x);
                else
                    minHeap.offer(x);

                if (!minHeap.isEmpty()) {
                    if (maxHeap.peek() > minHeap.peek()) {
                        int tmp1 = maxHeap.poll();
                        int tmp2 = minHeap.poll();

                        minHeap.offer(tmp1);
                        maxHeap.offer(tmp2);
                    }
                }
                if (i % 2 == 1) {
                    cnt++;

                    if (cnt == 10 || i == M) {
                        sb.append(maxHeap.peek()).append("\n");
                        cnt = 0;
                    } else
                        sb.append(maxHeap.peek() + " ");
                }
                if (i % 10 == 0) {
                    stringTokenizer = new StringTokenizer(br.readLine());
                }
            }
        }
        System.out.print(sb);
    }
}
