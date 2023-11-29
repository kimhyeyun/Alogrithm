import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class lg_biztechi {
    public int solution(int n, int m) {
        int answer = 0;

        for (int i = n; i <= m; i++) {
            if(1 <= i && i <= 9) answer += 1;
            else {
                if(isPalindrome(i)) answer += 1;
            }
        }

        return answer;
    }

    private boolean isPalindrome(int num) {
        String n = String.valueOf(num);
        int j = n.length() - 1;

        for (int i = 0; i < n.length() / 2; i++) {
            if(n.charAt(i) != n.charAt(j)) return false;
            j -= 1;
        }

        return true;
    }


    public String solution(String s, int[][] interval) {
        StringBuilder sb;

        String str = s;
        for (int[] i : interval) {
            int start = i[0] - 1;
            int end = i[1] - 1;

            String s1 = str.substring(0, start);
            String s2 = str.substring(end + 1);

            sb = new StringBuilder(str.substring(start, end + 1));
            sb.reverse();

            str = s1 + sb.toString() + s2;

            System.out.println(str);
        }

        return str;
    }

    public int[][] solution(int n, int m, int[][] records) {
        List<int[]> ansList = new ArrayList<>();

        int[] storages = new int[n + 1];
        int[] countOfData = new int[m + 1];
        List<Data> dataList = new ArrayList<>();

        for (int i = 0; i < records.length; i++) {
            int[] record = records[i];

            dataList.add(new Data(record[1], i, record[0], record[2]));
            countOfData[record[1]] += 1;
            storages[record[0]] += 1;
        }

        while (dataList.size() > m) {
            Collections.sort(dataList, (o1, o2) -> {
                if (o1.referenced == o2.referenced) {
                    if (storages[o1.indexOfStorage] == storages[o2.indexOfStorage]) {
                        return o1.index - o2.index;
                    }
                    return storages[o2.indexOfStorage] - storages[o1.indexOfStorage];
                }
                return o2.referenced - o1.referenced;
            });

            int j = 0;
            while (true) {
                if(countOfData[dataList.get(j).num] > 1) break;
                j += 1;
            }

            Data data = dataList.get(j);
            dataList.remove(data);
            storages[data.indexOfStorage] -= 1;
            countOfData[data.num] -= 1;

            ansList.add(new int[]{data.indexOfStorage, data.num});
        }

        int[][] answer = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); i++) {
            answer[i] = ansList.get(i);
        }

        return answer;
    }

    public class Data {
        int num;
        int index, indexOfStorage, referenced;

        public Data(int num, int index, int indexOfStorage, int referenced) {
            this.num = num;
            this.index = index;
            this.indexOfStorage = indexOfStorage;
            this.referenced = referenced;
        }

    }
    @Test
    void test() {
//        solution("abcde", new int[][]{{1, 3}, {1, 4}, {4, 5}});
        solution(3, 5, new int[][]{{1, 1, 5}, {2, 4, 7}, {1, 5, 10}, {3, 1, 10}, {2, 1, 5}, {1, 3, 3}, {3, 2, 8}, {2, 2, 8}, {3, 4, 7}});
    }
}
