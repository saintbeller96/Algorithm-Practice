package P9088;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	private static int T, Answer;
	private static int[] diamonds;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			stk = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(stk.nextToken());
			int k = Integer.parseInt(stk.nextToken());
			diamonds = new int[n];
			for (int i = 0; i < n; i++) {
				diamonds[i] = Integer.parseInt(br.readLine());
			}
			Arrays.sort(diamonds);			
			Answer = 0;
			for (int i = 0; i < n; i++) {
				int dest = diamonds[i]+k+1;
				int idx = upper(dest);
				Answer = Answer > idx - i? Answer : idx - i;				
			}
			
			System.out.println("#" + t + " " + Answer);
		}
	}
	private static int upper(int n) {
		int s = 0;
		int e = diamonds.length;
		int mid = -1;
		while(e > s) {
			mid = (e+s)/2;
			if(diamonds[mid] < n) {
				s = mid+1;
			}else {
				e = mid;
			}
		}
		return e;
	}

}
