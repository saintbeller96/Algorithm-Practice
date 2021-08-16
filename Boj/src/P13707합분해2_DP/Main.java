package P13707합분해2_DP;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int K = Integer.parseInt(stk.nextToken());
		int MOD = 1000000000;
		int[][] dp = new int[5001][5001];
		for (int n = 1; n <=5000 ; n++) {
			dp[n][1] = 1;
			dp[1][n] = n;
		}
		for (int n = 2; n <=N ; n++) {
			for (int k = 2; k <=K ; k++) {
				dp[n][k] = (dp[n][k-1] + dp[n-1][k])%MOD;
			}
		}
		System.out.println(dp[N][K]);
	}
}
