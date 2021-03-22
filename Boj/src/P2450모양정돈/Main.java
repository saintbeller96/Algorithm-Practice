package P2450모양정돈;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N;
	static int[] arr;
	static int[][] order = {{1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}};
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int[] num = new int[4];
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
			num[arr[i]]++;
		}
		
		int[][] targets = new int[6][N];
		for(int i = 0; i<6; i++) {
			int[] o = order[i];
			int idx = 0;
			for(int j : o) {
				for(int k = 0; k< num[j]; k++) {
					targets[i][idx++] = j;
				}
			}
		}
		
		int answer = Integer.MAX_VALUE;
		for(int i = 0; i<6; i++) {
			int[][] table = new int[4][4];
			int[] target = targets[i];
			int cnt = 0;
			for(int j = 0; j<N; j++) {
				table[arr[j]][target[j]]++;
			}
			
			cnt += Math.min(table[1][2], table[2][1]);
			cnt += Math.max(table[1][3], table[3][1]);
			cnt += Math.max(table[2][3], table[3][2]);
			
			answer = Math.min(answer, cnt);
		}
		
		System.out.println(answer);
		return;
	}
}
