package P1102발전소;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2 {
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
		PriorityQueue<int[]> pque = new PriorityQueue<int[]>((x,y)->Integer.compare(x[1], y[1]));
		
		Arrays.fill(dp, Integer.MAX_VALUE);
		for(int i = 0; i<N; i++) {
			if((bit&(1<<i)) != 0) {
				for(int j = 0; j<N; j++) {
					if((bit&(1<<j)) == 0) {
						pque.offer(new int[]{bit|(1<<j), costs[i][j], remain+1});
					}
				}
			}
		}
		
		while(!pque.isEmpty()) {
			int[] p = pque.poll();
			int curBit = p[0];
			int curCost = p[1];
			int curRemain = p[2];
			if(curRemain >= P) {
				return curCost;
			}
			if(dp[curBit] >= curCost) {
				for(int i = 0; i<N; i++) {
					if((curBit&(1<<i)) != 0) {
						for(int j = 0; j<N; j++) {
							if((curBit&(1<<j)) == 0) {
								if(dp[curBit|(1<<j)] >= curCost + costs[i][j]) {
									dp[curBit|(1<<j)] = curCost + costs[i][j];
									pque.offer(new int[]{curBit|(1<<j), curCost + costs[i][j], curRemain+1});
								}
							}
						}
					}
				}
			}
		}
		
		return Integer.MAX_VALUE;
	}
}
