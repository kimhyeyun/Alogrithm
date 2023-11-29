package ssafy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	
	public static void main(String[] args) {
		solution2();
	}
	
	static int max = 0;
	public static void solution2() {
		Scanner sc = new Scanner(System.in);
		
		int testCase  = sc.nextInt();
		for(int i = 1; i<= testCase; i++) {
			max = 0;
			int N = sc.nextInt();
			
			List<int[]> list = new ArrayList<>();
			
			for(int j = 0;j<N;j++) {
				int t = sc.nextInt();
				int p = sc.nextInt();
				
				list.add(new int[] {t, p});
			}
			
			
			DFS(list, 0, 0, new LinkedList<Integer>());
			
			System.out.println(max);
		}
	}
	
	public static void DFS(List<int[]> list, int index, int sum, Queue<Integer> cars) {
		if(index == list.size()) {
			max = Math.max(max, sum);
			return;
		}
		
		for(int i = index; i< list.size(); i++) {
			if(list.get(i)[0] == 1) cars.add(list.get(i)[1]);
			else {
				// 무조건 포기 경우 
				if(!cars.contains(list.get(i)[1]) || cars.isEmpty()) continue;
					
				Queue<Integer> tmp = new LinkedList<Integer>();
				tmp.addAll(cars);
				
				//구매 
				cars.clear();
				DFS(list, i + 1, sum + list.get(i)[1], cars); 
				
				
				//포기 
				DFS(list, i+1, sum, tmp);
			}
		}
		
		max = Math.max(max, sum);
		return;
	}

	public static void solution1() {
		Scanner sc = new Scanner(System.in);
		
		int testCase  = sc.nextInt();
		for(int i = 1; i<= testCase; i++) {
			int N = sc.nextInt();
			
			int[][] arr= new int[N][2];
			for(int j = 0;j<N;j++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				arr[j][0] = a;
				arr[j][1] = b;
			}
			
			int count =0;
			boolean[] isDestroyed = new boolean[N];
			int index = -1;
			
			while(++index < N) {
				if(isDestroyed[index]) continue;
				
				// 본
				isDestroyed[index] = true;
				
				count += 1;
				int power = arr[index][0] + arr[index][1];
				
				int next = index + 1;				
				while(true) {
					if(next >= N) break;
					if(arr[next][0] <= power) {
						isDestroyed[next] = true;
						next += 1;
					}else break;
				}
				
			}
			
			System.out.println("#"+i + " " + count);
		}
		
	}
}
