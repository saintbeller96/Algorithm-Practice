package P17404RGB거리2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] color2cost = new int[N][3];
		for(int i = 0; i < N; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				color2cost[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		int[][][] dp = new int[3][N][3];
		
		for(int c = 0; c<3; c++) {
			dp[c][0][c] = color2cost[0][c];
			for(int c2 = 0; c2<3; c2++) {
				if(c2 == c) {
					dp[c][1][c2] = Integer.MAX_VALUE;
				}else {
					dp[c][1][c2] = dp[c][0][c] + color2cost[1][c2];
				}
			}
			for(int i = 2; i<N; i++) {
				dp[c][i][0] = Math.min(dp[c][i-1][1], dp[c][i-1][2]) + color2cost[i][0];
				dp[c][i][1] = Math.min(dp[c][i-1][2], dp[c][i-1][0]) + color2cost[i][1];
				dp[c][i][2] = Math.min(dp[c][i-1][1], dp[c][i-1][0]) + color2cost[i][2];
			}
		}
		int answer = Integer.MAX_VALUE;
		for(int c = 0; c<3; c++) {
			for(int c2 = 0; c2<3; c2++) {
				if(c2 != c) {
					answer = Math.min(answer, dp[c][N-1][c2]);
				}
			}
		}
		System.out.println(answer);
	}
}
