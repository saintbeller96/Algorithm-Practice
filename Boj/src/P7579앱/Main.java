package P7579앱;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	static int N, M;
	static int[][] apps;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		apps = new int[N][2];
		
		stk = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			apps[i][0] = Integer.parseInt(stk.nextToken());
		}
		
		stk = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			apps[i][1] = Integer.parseInt(stk.nextToken());
		}
		
		
		
		return;
	}
}
