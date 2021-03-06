package P1102발전소;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, P, answer;
	static int[][] costs;
	static int[] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		N = Integer.parseInt(br.readLine());
		costs = new int[N][N];
		for(int i = 0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				costs[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		int bit = 0;
		int remain = 0;
		String str = br.readLine();
		for(int i = 0; i<str.length(); i++) {
			if(str.charAt(i) == 'Y') {
				bit = bit|(1<<i);
				remain++;
			}
		}
		dp = new int[(1<<N)];
		Arrays.fill(dp, -1);
		P = Integer.parseInt(br.readLine());
		
		if(remain >= P) {
			System.out.println(0);
		}else {
			int answer = offonoff(bit, remain);
			if(answer == Integer.MAX_VALUE) System.out.println(-1);
			else System.out.println(answer);
		}		
	}
	//on: Ų ������
	static int offonoff(int bit, int remain) {
		if(remain >= P) return 0;
		if(dp[bit] != -1) return dp[bit];
		
		dp[bit] = Integer.MAX_VALUE;
		
		for(int i = 0; i<N; i++) {
			//���� ������ ����
			if((bit&(1<<i)) != 0) {
				for(int j = 0; j<N; j++) {
					//���� ������ ����
					if((bit&(1<<j)) == 0) {
						dp[bit] = Math.min(dp[bit], costs[i][j] + offonoff(bit|(1<<j), remain+1));
					}
				}
			}
		}
		
		return dp[bit];
	}
}
