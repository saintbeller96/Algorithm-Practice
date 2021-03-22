package P2116주사위쌓기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] dices;
	static int[] opposite = {0, 6, 4, 5, 2, 3, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		int N = Integer.parseInt(br.readLine());
		dices = new int[N][7];
		for(int i = 0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 1; j<=6; j++) {
				int n = Integer.parseInt(stk.nextToken());
				dices[i][j] = n;
			}
		}
		
		int answer = 0;
		//첫번째 놓는 주사위는 6번 반복
		for(int i = 1; i<=6; i++) {
			int top = dices[0][i];
			int idx = i;
			int sum = 0;
			int max = 0;
			for(int j =1; j<=6; j++) {
				if(j != idx && j != opposite[idx]) {
					max = Math.max(max, dices[0][j]);
				}
			}
			sum += max;
			for(int j = 1; j<N; j++) {
				max = 0;
				for(int k = 1; k <= 6; k++) {
					if(top == dices[j][k]) {
						top = dices[j][opposite[k]];
						idx = k;
						break;
					}
				}
				
				for(int k = 1; k <= 6; k++) {
					if(k != idx && k != opposite[idx]) {
						max = Math.max(max, dices[j][k]);
					}
				}
				sum += max;
			}
			answer = Math.max(sum, answer);
			
		}
		System.out.println(answer);
		
	}

}
