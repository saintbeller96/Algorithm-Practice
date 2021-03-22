package P4012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N, Answer;
	private static int[][] map;
	private static boolean[] b;
	private static ArrayList<int[]> combs;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			b = new boolean[N];
			combs = new ArrayList<int[]>();
			for(int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			divide(N/2, 0, 0);
			
			Answer = cook();
			
			System.out.println("#" + t + " " + Answer);			
		}			
	}
	private static void divide(int len, int start, int cur) {
		if(cur == len) {
			int[] p1 = new int[N/2];
			int idx1 = 0;
			for(int i = 0; i < N; i++) {
				if(b[i]) {
					p1[idx1++] = i;
				}
			}	
			combs.add(p1);
			return;
		}else {
			for(int i = start; i < N; i++) {
				b[i] = true;
				divide(len, i+1, cur+1);
				b[i] = false;
			}
		}
	}
	
	private static int cook() {		
		int min = Integer.MAX_VALUE;
		for (int k = 0; k < combs.size()/2; k++) {
			int[] p1 = combs.get(k);
			int[] p2 = combs.get(combs.size()-k-1);
			
			int sum1 = 0;
			for (int i : p1) {
				for (int j :p1) {
					sum1 += map[i][j];
				}
			}
			int sum2 = 0;
			for (int i :p2) {
				for (int j :p2) {
					sum2 += map[i][j];
				}
			}
			int dif = Math.abs(sum1 - sum2);
			min = min < dif ? min : dif;

		}
		return min;
	}

}
