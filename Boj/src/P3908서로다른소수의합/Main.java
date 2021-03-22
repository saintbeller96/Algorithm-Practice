package P3908서로다른소수의합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int T;
	static int N, K;
	static Integer[] primes;
	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		primes = findPrimeNumber(1120);
		int[][][] dp = new int[1121][primes.length+1][15];
		
		for(int tc = 0; tc<T; tc++) {
			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			K = Integer.parseInt(stk.nextToken());
			
			for(int i = 0; i<=N; i++) {
				for(int j = 0; j<=primes.length; j++) {
					for(int k = 0; k<=K; k++) {
						dp[i][j][k] = -1;
					}
				}
			}

			sb.append(dfs(dp, 0, 0, 0)).append('\n');
		}
		System.out.println(sb);
		return;
	}
 	
 	static int dfs(int[][][] dp, int n,int p, int k) {
 		if(k == K) return n == N ? 1 : 0;
 		if(n >= N || p == primes.length) return 0;
 		if(dp[n][p][k] != -1) return dp[n][p][k];
 		//현재 소수를 고르지 않음
 		dp[n][p][k] = dfs(dp, n, p+1, k);
 		
 		//현재 소수를 고름
 		if(n+primes[p] <= N) {
 			dp[n][p][k] += dfs(dp, n+primes[p], p+1, k+1);
 		}
 		return dp[n][p][k];
 	}
 	
 	static Integer[] findPrimeNumber(int n) {
 		boolean[] check = new boolean[n+1];
 		check[0] = check[1] = true;
 		
 		for(int i=2; i<=n; i++) {
 			if(check[i]) continue;
 			
 			for(int j = 2*i; j <= n; j+=i) {
 				check[j] = true;
 			}
 		}
 		List<Integer> list = new ArrayList<>();
 		for(int i = 2; i<=n; i++) {
 			if(!check[i]) {
 				list.add(i);
 			}
 		}
 		return list.toArray(new Integer[0]);
 	}
}
