package P1494;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N;
	private static long Answer;
	private static int[][] map;
    private static int Vx, Vy;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][2];
            Vx = 0;
            Vy = 0;
			for(int i = 0; i<N; i++) {
				stk = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(stk.nextToken());
				int c = Integer.parseInt(stk.nextToken());
                Vx += r;
                Vy += c;
				map[i][0] = r;
				map[i][1] = c;
			}		
			Answer = Long.MAX_VALUE;
			mySolve(0, 0, 0, 0);							
			System.out.println("#" + t + " " + Answer);
		}
	}
	private static void mySolve(int curlen, int start, int vx, int vy) {
		if(curlen == N/2) {
            vx = vx - (Vx - vx);
            vy = vy - (Vy - vy);
			long v = (long)vx*vx + (long)vy*vy;
			Answer = Answer < v ? Answer:v;		
			return;
		}	
		else {
			for(int i = start; i < N; i++) {
				mySolve(curlen+1, i+1, vx + map[i][0], vy + map[i][1]);
			}
		}
	}
}