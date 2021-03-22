package P1495기타리스트;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] dp;
	static int[] volumes;
	static int N, M, S, answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		S = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		volumes = new int[N];
		dp = new int[M+1][N];
		for(int i = 0; i<=M; i++) {
			for(int j = 0; j<N; j++) {
				dp[i][j] = -1;
			}
		}
		stk = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			volumes[i] = Integer.parseInt(stk.nextToken());
		}
		int answer = play(S, 0);
		System.out.println(answer);
	}
	static int play(int P,  int depth) {
		if(P < 0 || P > M) return -1;
		if(depth >= N) return P;
		if(dp[P][depth] != -1) return dp[P][depth];
		
		return dp[P][depth] = Math.max(play(P-volumes[depth], depth+1), play(P+volumes[depth], depth+1));
	}
}
