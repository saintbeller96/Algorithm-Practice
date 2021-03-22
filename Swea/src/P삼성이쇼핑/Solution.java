package P삼성이쇼핑;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N, M;
	private static int[][] cloth;
	private static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer stk = null;
	    T = Integer.parseInt(br.readLine());
	    for (int t = 1; t <=T; t++) {
	    	stk = new StringTokenizer(br.readLine());
	    	N = Integer.parseInt(stk.nextToken());
	    	M = Integer.parseInt(stk.nextToken());
	    	cloth = new int[M+1][2];
	    	dp = new int[M+1][N+1];
	    	for(int i = 1; i<=M; i++) {
	    		stk = new StringTokenizer(br.readLine());
	    		cloth[i][0] = Integer.parseInt(stk.nextToken());
	    		cloth[i][1] = Integer.parseInt(stk.nextToken());
	    	}
	    	int answer = dp();
	    	System.out.println("#" + t + " " + answer);
	    }
	}
	private static int myDP(int i, int m) {
		if(dp[i][m] > 0) return dp[i][m];
		if(i == M) {
			return 0;
		}		
		int n1 = 0;
		if(m+cloth[i][0] <= N) {
			n1 = cloth[i][1] + myDP(i+1, m+cloth[i][0]);
		}
		int n2 = myDP(i+1, m);
		dp[i][m] = Math.max(n1, n2);
		return dp[i][m];
	}
	private static int dp() {
		for(int i = 1; i<=M; i++) {
			for(int j = 1; j <= N; j++) {
				dp[i][j] = dp[i-1][j];
				if(j - cloth[i][0] >= 0) {
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-cloth[i][0]] + cloth[i][1]);
				}
			}
		}
		return dp[M][N];
	}

}
