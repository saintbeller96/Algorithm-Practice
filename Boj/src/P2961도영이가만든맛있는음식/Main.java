package P2961도영이가만든맛있는음식;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		N = Integer.parseInt(br.readLine());
		int[][] ingr = new int[N][2];
		for(int i = 0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			ingr[i][0] = s;
			ingr[i][1] = b;
		}
		int min = recur(ingr, 0,0,1,0);
		System.out.println(min);
	}
	public static int recur(int[][] ingr, int cnt, int curlen, int s, int b) {
		if(curlen == N) {
			if(cnt == 0) return Integer.MAX_VALUE;
			else return Math.abs(s - b);
		}
		int f1 = recur(ingr, cnt+1, curlen+1, s*ingr[curlen][0], b+ingr[curlen][1]);
		int f2 = recur(ingr, cnt, curlen+1, s, b);
		return f1 < f2 ? f1:f2;
	}

}
