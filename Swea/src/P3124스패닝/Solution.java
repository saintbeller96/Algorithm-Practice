package P3124스패닝;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	private static class Edge{
		int u, v, w;
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}
	private static int T, V, E;
	private static int[] set;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			stk = new StringTokenizer(br.readLine());
			V = Integer.parseInt(stk.nextToken());
			E = Integer.parseInt(stk.nextToken());
			
			set = new int[V+1];
			long answer = 0L;
			PriorityQueue<Edge> que = new PriorityQueue<Edge>((a, b)->Integer.compare(a.w, b.w));
			for(int i = 0; i<E; i++) {
				stk = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(stk.nextToken());
				int v = Integer.parseInt(stk.nextToken());
				int w = Integer.parseInt(stk.nextToken());
				set[u] = u;
				set[v] = v;
				que.offer(new Edge(u, v, w));
			}
			while(!que.isEmpty()) {
				Edge e = que.poll();
				if(union(e.u, e.v)) {
					answer += (long)e.w;
				}
			}
			System.out.println("#" + t + " " + answer);
		}

	}
	private static boolean union(int s1, int s2) {
		int x = find_next(s1);
		int y = find_next(s2);
		if(x != y) {
			set[y] = x;
			return true;
		}
		return false;
	}
	private static int find_next(int s) {
		if(s == set[s]) return s;
		return set[s] = find_next(set[s]);		
	}
}
