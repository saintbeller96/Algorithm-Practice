package P2629양팔저울;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] plumbs;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		plumbs = new int[N];
		StringTokenizer stk = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			plumbs[i] = Integer.parseInt(stk.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		int[] balls = new int[M];
		stk = new StringTokenizer(br.readLine());
		
		for(int i = 0; i<M; i++) {
			balls[i] = Integer.parseInt(stk.nextToken());
		}
		
		boolean[][] dp = new boolean[N+1][100001];

		dfs(dp, 40000, 0);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<M; i++) {
			boolean flag = true;
			for(int j = 1; j<=N; j++) {
				if(dp[j][40000 + balls[i]]) {
					sb.append('Y').append(' ');
					flag = false;
					break;
				}
			}
			if(flag) sb.append('N').append(' ');
		}
		System.out.println(sb);
	}
	
	static void dfs(boolean[][] dp, int curWeight, int depth) {
		if(depth == N+1) return;
		if(dp[depth][curWeight]) return;
		else dp[depth][curWeight] = true;
		
		dfs(dp, curWeight + plumbs[depth], depth+1);
		dfs(dp, curWeight, depth+1);
		dfs(dp, curWeight - plumbs[depth], depth+1);
	}

}
