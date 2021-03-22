package P2515전시장;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N, S;
	static long[] dp;
	static int[][] pictures;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		S = Integer.parseInt(stk.nextToken());
		dp = new long[N+1];
		pictures = new int[N+1][2];
		for(int i = 1; i<=N; i++) {
			stk = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			pictures[i][0] = h;
			pictures[i][1] = c;
		}
		
		Arrays.sort(pictures, (a, b)->Integer.compare(a[0], b[0]));

		int[] maxHeightBefore = new int[N+1];
		maxHeightBefore[0] = 0;
		for(int i = 1; i<=N; i++) {
			int height = pictures[i][0];
			int j = maxHeightBefore[i-1];
			for(; j<i; j++) {
				if(height - pictures[j][0] < S) break;
			}
			maxHeightBefore[i] = j-1;			
		}
		for(int i = 1; i<=N; i++) {
			int prev = maxHeightBefore[i];
			dp[i] = Math.max(dp[prev] + pictures[i][1], dp[i-1]);
		}
		
		System.out.println(dp[N]);
		return;
	}
}
