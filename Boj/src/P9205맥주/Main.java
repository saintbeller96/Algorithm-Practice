package P9205맥주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int T, N;
	private static int[][] conv;
	private static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;		
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			visited = new boolean[102];
			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			
			stk = new StringTokenizer(br.readLine());
			int si = Integer.parseInt(stk.nextToken());
			int sj = Integer.parseInt(stk.nextToken());
			
			conv = new int[N][2];
			for(int i = 0; i<N; i++) {//0 집, 1 편의점, 2 페스티벌
				stk = new StringTokenizer(br.readLine());
				conv[i][0] = Integer.parseInt(stk.nextToken());
				conv[i][1] = Integer.parseInt(stk.nextToken());
			}		
			stk = new StringTokenizer(br.readLine());
			int ei = Integer.parseInt(stk.nextToken());
			int ej = Integer.parseInt(stk.nextToken());		
			
			System.out.println(BFS(si, sj, ei, ej));			
		}
	}
	private static String BFS(int si, int sj, int ei, int ej) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] { si, sj});
		while(!que.isEmpty()) {
			int[] p = que.poll();
			int d = getDistance(p[0], p[1], ei, ej);
			if(d <= 1000) {
				return "happy";
			}
			for(int n = 0; n<N; n++) {
				if(getDistance(p[0], p[1], conv[n][0], conv[n][1]) <= 1000 && !visited[n]) {
					que.offer(new int[] {conv[n][0], conv[n][1]});
					visited[n] = true;
				}
			}	
		}
		return "sad";
	}
	
	private static int getDistance(int i1, int j1, int i2, int j2) {
		return Math.abs(i1 - i2) + Math.abs(j1 - j2);
	}
}
