package P21317징검다리건너기;

import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[] shortJumps;
	static int[] longJumps;
	static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		N = Integer.parseInt(br.readLine());
		
		shortJumps = new int[N+1];
		longJumps = new int[N+1];
		for(int i = 1; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			shortJumps[i] = Integer.parseInt(stk.nextToken());
			longJumps[i] = Integer.parseInt(stk.nextToken());
		}
		K = Integer.parseInt(br.readLine());
		//dfs(1, 0, false);
		int[] memo = new int[N+1];
		Arrays.fill(memo, -1);
		
		System.out.println(dp(memo, 1, false));
	}
	static void dfs(int idx, int sum, boolean flag) {
		if(sum >= answer) return;
		if(idx == N) {
			answer = Math.min(answer, sum);
			return;
		}
		if(idx > N) return;
		
		//작은 점프
		dfs(idx+1, sum + shortJumps[idx], flag);
		//큰 점프
		dfs(idx+2, sum + longJumps[idx], flag);
		
		//매우 큰 점프
		if(!flag) {
			dfs(idx+3, sum + K, true);
		}
	}
	
	static int dp(int[] memo, int idx, boolean flag) {
		if(idx == N) {
			return 0;
		}
		
		int ret = Integer.MAX_VALUE;
		ret = Math.min(ret, shortJumps[idx] + dp(memo, idx+1, flag));
		if(idx+2<=N) ret = Math.min(ret, longJumps[idx] + dp(memo, idx+2, flag));
		if(!flag && idx+3<=N) ret = Math.min(ret, K + dp(memo, idx+3, flag));
		
		return memo[idx] = ret;
	}
}