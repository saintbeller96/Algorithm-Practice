package P15686치킨배달;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int M, N, answer = Integer.MAX_VALUE;
	static ArrayList<int[]> house, chicken;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		house = new ArrayList<>();
		chicken = new ArrayList<>();
		
		for(int i = 0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				int a = Integer.parseInt(stk.nextToken());
				if(a == 1) {
					house.add(new int[] {i, j});
				}else if(a == 2) {
					chicken.add(new int[] {i, j});
				}
			}
		}
		
		int[] arr = new int[chicken.size()];
		for(int i = 0; i< chicken.size(); i++) {
			arr[i] = i;
		}
		int[] num = new int[M];
		comb(arr, num, 0, 0);
		
		System.out.println(answer);
	}
	
	static void comb(int[] arr, int comb[], int cnt, int start) {
		if(cnt == M) {
			int sum = 0;
			for(int[] h : house) {
				int d = Integer.MAX_VALUE;
				for(int i : comb) {
					d = Math.min(d, dist(chicken.get(i), h));
				}
				sum += d;
			}
			answer = Math.min(sum, answer);
			return;
		}
		
		for(int i = start; i<chicken.size(); i++) {
			comb[cnt] = arr[i];
			comb(arr, comb, cnt+1, i+1);
		}
	}
	static int dist(int[] a, int[] b) {
		return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
	}
}
