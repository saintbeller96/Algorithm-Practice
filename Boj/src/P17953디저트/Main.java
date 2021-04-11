package P17953디저트;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] table;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		table = new int[M][N];
		int[][] memo = new int[M][N];
		for(int i = 0; i<M; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				table[i][j] = Integer.parseInt(stk.nextToken());
				memo[i][j] = -1;
			}
		}
		int answer = 0;
		for(int i = 0; i<M; i++) {
			answer = Math.max(answer, dp(memo, 0, 0));
		}

		System.out.println(answer);
		
	}
	static int dp(int[][] memo, int prev, int day) {
		if(day == N) {
			return 0;
		}
		if(memo[prev][day] != -1) return memo[prev][day];
		
		int ret = -1;
		for(int i = 0; i<M; i++) {
			int value = table[i][day];
			if(day!=0 && i == prev) value = value/2;
			ret = Math.max(ret, value + dp(memo, i, day+1));
		}
		return memo[prev][day] = ret;
	}
}
