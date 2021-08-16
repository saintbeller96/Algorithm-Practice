package P13707합분해2_DP;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int K = Integer.parseInt(stk.nextToken());
		long MOD = 1000000000;
		long[][] dp = new long[N+1][K+1];
		for (int n = 1; n <=N ; n++) {
			for (int k = 1; k <=K ; k++) {
				if(n == 1) dp[n][k] = k;
				else dp[n][k] = (dp[n][k-1] + dp[n-1][k])%MOD;
			}
		}
		System.out.println(dp[N][K]);
	}
}
