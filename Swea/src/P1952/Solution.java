package P1952;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int T, Answer;
	private static int[] price = new int[4];
	private static int[] plan = new int[13];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			stk = new StringTokenizer(br.readLine());
			for(int i = 0; i<4; i++) {
				price[i] = Integer.parseInt(stk.nextToken());
			}
			stk = new StringTokenizer(br.readLine());
			for(int i = 1; i<=12; i++) {
				plan[i] = Integer.parseInt(stk.nextToken());
			}
			Answer = Integer.MAX_VALUE;
			DFS(1, 0);
						
			System.out.println("#" + t + " " + Answer);
		}
	}
	
	private static void DFS(int month, int sum) {
		if(month > 12) {
			Answer = Answer<sum?Answer:sum;
			Answer = Answer<price[3]?Answer:price[3];
			return;
		}
		if(plan[month] != 0) {
			DFS(month+1, sum + price[0]*plan[month]);
			DFS(month+1, sum + price[1]);
			DFS(month+3, sum + price[2]);
		}else {
			DFS(month+1, sum);
		}
	}
}