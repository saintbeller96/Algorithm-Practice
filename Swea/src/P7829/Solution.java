package P7829;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	private static int T, P, Answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			P = Integer.parseInt(br.readLine());
			stk = new StringTokenizer(br.readLine());
			int[] answer = new int[P];
			for (int i = 0; i < answer.length; i++) {
				answer[i] = Integer.parseInt(stk.nextToken());
			}
			Arrays.sort(answer);			
			int Answer = answer[0]*answer[P-1];
			
			System.out.println("#" + t + " " + Answer);
		}
	}

}
