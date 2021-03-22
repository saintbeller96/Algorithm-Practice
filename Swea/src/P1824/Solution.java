package P1824;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int T, R, C, Answer;
	private static int[] dr = {1, -1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	private static int[] dir = {'^', 'v', '<', '>'};
	private static int memory = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			stk = new StringTokenizer(br.readLine());
			R = Integer.parseInt(stk.nextToken());
			C = Integer.parseInt(stk.nextToken());
			char[][] map = new char[R][C];
			for (int i = 0; i < R; i++) {
				map[i] = br.readLine().toCharArray();
			}
			memory = map[0][0] - '0';
			
			
			
			System.out.println("#" + t + " " + Answer);
		}
		
	}

}

