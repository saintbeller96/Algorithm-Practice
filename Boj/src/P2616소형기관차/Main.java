package P2616소형기관차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] trains;
	static int[] presum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		trains = new int[N+1];
		presum = new int[N+1];
		StringTokenizer stk = new StringTokenizer(br.readLine());
		for(int i = 1; i<=N; i++) {
			trains[i] = Integer.parseInt(stk.nextToken());
			presum[i] = trains[i];
			presum[i] += presum[i-1];
		}
		
		M = Integer.parseInt(br.readLine());
		
		int[][] memo = new int[N+1][3];
		for(int i = 0; i<=N; i++) {
			for(int j = 0; j<3; j++) {
				memo[i][j] = -1;
			}
		}
		
		int answer = dp(memo, 1, 0);

		System.out.println(answer);
	}
	static int dp(int[][] memo, int idx, int depth) {
		if(idx + M -1> N && depth < 3) {
			return Integer.MIN_VALUE;
		}
		if(depth == 3) {
			return 0;
		}
		if(memo[idx][depth] != -1) return memo[idx][depth];
		
		int sum = presum[idx+M-1] - presum[idx-1];
		int ret = Math.max(dp(memo, idx+1, depth), sum + dp(memo, idx+M, depth+1));
		
		return memo[idx][depth] = ret;
	}
}
