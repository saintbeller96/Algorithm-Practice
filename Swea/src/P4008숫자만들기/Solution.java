package P4008숫자만들기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, answer;
	static int[] operands, numbers;
	static int max, min;
	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			//0 + 1 - 2 * 3 /
			operands = new int[4];
			numbers = new int[N];
			stk = new StringTokenizer(br.readLine());
			for(int i = 0; i<4; i++) {
				operands[i] = Integer.parseInt(stk.nextToken());
			}
			stk = new StringTokenizer(br.readLine());
			for(int i = 0; i<N; i++) {
				numbers[i] = Integer.parseInt(stk.nextToken());
			}
			
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			
			dfs(0, numbers[0]);
			System.out.println("#" + tc + " " + (max - min));
		}

	}
	static void dfs(int depth, int result) {
		if(depth == N-1) {
			min = Math.min(min, result);
			max = Math.max(max, result);
			return;
		}
		
		for(int i = 0; i<4; i++) {
			if(operands[i] == 0) continue;
			operands[i]--;
			int result_ = result;
			if(i== 0) result_ += numbers[depth+1];
			else if(i==1) result_ -= numbers[depth+1];
			else if(i==2) result_ *= numbers[depth+1];
			else if(i==3) result_ /= numbers[depth+1];
			
			dfs(depth+1, result_);
			operands[i]++;
		}
	}
}
