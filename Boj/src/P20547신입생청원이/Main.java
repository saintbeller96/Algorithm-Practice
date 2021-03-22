package P20547신입생청원이;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int M, N, L;
	static int[] shooters;
	static int[][] animals;
	static Map<String, List<String>> adjList;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		for(int i = 0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			String lecture = stk.nextToken();
			String start = stk.nextToken();
			String end = stk.nextToken();
		}
		
	}
 	
}
