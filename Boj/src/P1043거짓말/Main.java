package P1043거짓말;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[] A;
	static int[] set;
	static int[][] parties;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		set = new int[N+1];
		for(int i = 1; i<=N; i++) {
			set[i] = i;
		}
		
		stk = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken());
		A = new int[n];
		for(int i = 0; i<n; i++) {
			A[i] = Integer.parseInt(stk.nextToken());
		}
		parties = new int[M][];
		for(int i = 0 ;i<M; i++) {
			stk = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(stk.nextToken());
			parties[i] = new int[k];
			for(int j = 0; j<k; j++) {
				parties[i][j] = Integer.parseInt(stk.nextToken());
			}
			int s = parties[i][0];
			for(int j = 1; j<k;j++) {
				union(s, parties[i][j]);
			}
		}
		
		int cnt = 0;
		for(int[] party : parties) {
			
			boolean flag = false;
			loop: for(int p : party) {
				for(int a : A) {
					if(find(p) == find(a)) {
						flag = true;
						break loop;
					}
				}
			}
			if(!flag) cnt++;
		}
		System.out.println(cnt);
		return;
 	}
 	
 	static void union(int x, int y) {
 		x = find(x);
 		y = find(y);
 		if(x != y) set[y] = x;
 	}
 	static int find(int x) {
 		if(x == set[x]) return set[x];
 		return set[x] = find(set[x]);
 	}
}
