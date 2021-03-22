package P1247최적경로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, answer;
	static int visitAll;
	static int[] home, company;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		home = new int[2];
		company = new int[2];
		for(int t = 1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			int[][] clients = new int[N][2];
			
			stk = new StringTokenizer(br.readLine());
			
			company[0] = Integer.parseInt(stk.nextToken());
			company[1] = Integer.parseInt(stk.nextToken());
			
			home[0] = Integer.parseInt(stk.nextToken());
			home[1] = Integer.parseInt(stk.nextToken());
			
			for(int i = 0;i<N; i++) {
				clients[i][0] = Integer.parseInt(stk.nextToken());
				clients[i][1] = Integer.parseInt(stk.nextToken());
			}
			answer = Integer.MAX_VALUE;
			visitAll = (1<<N)-1;
			
			DFS(clients, home[0], home[1], 0, 0);
			
			System.out.println("#" + t + " " + answer);
		}
	}
	
	static void DFS(int[][] clients, int x, int y, int cnt, int v) {
		if(cnt > answer) {
			return;
		}
		if(v == visitAll) {
			cnt += Math.abs(x - company[0]) + Math.abs(y-company[1]);
			answer = Math.min(cnt, answer);
			return;
		}
		for(int i = 0; i<N; i++) {
			int cx = clients[i][0];
			int cy = clients[i][1];
			if((v & (1<<i)) != 0) continue;
			DFS(clients, cx, cy, cnt + Math.abs(x-cx) + Math.abs(y-cy), v|(1<<i));
		}
	}
}
