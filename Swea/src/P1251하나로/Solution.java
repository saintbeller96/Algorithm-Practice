package P1251하나로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N;
	private static double E;
	private static int[] set, rank;
	private static class Edge{
		int u, v;
		double w;
		public Edge(int u, int v, double w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			long[][] islands = new long[N][2];
			PriorityQueue<Edge> que = new PriorityQueue<Edge>((x, y)->Double.compare(x.w, y.w));
			set = new int[N];
			rank = new int[N];
			stk = new StringTokenizer(br.readLine());
			for(int i = 0; i<N; i++) {
				islands[i][0] = Long.parseLong(stk.nextToken());
				set[i] = i;
				rank[i] = 1;
			}
			stk = new StringTokenizer(br.readLine());
			for(int i = 0; i<N; i++) {
				islands[i][1] = Long.parseLong(stk.nextToken());
			}
			E = Double.parseDouble(br.readLine());
			boolean[][] check = new boolean[N][N];
			for(int i = 0; i<N; i++) {
				long x1 = islands[i][0];
				long y1 = islands[i][1];
				for(int j = 0; j<N; j++) {
					if(check[i][j] || check[j][i]) continue;
					if(i!=j) {
						check[i][j] = true;
						check[j][i] = true;
						long x2 = islands[j][0];
						long y2 = islands[j][1];
						double e = ((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2))*E;
						que.offer(new Edge(i, j, e));
					}
				}
			}
			double answer = 0L;
			while(!que.isEmpty()) {
				Edge e = que.poll();
				if(union(e.u, e.v)) {
					answer +=e.w;
				}
			}
			System.out.println("#" + t+" " + (long)(answer+0.5));
		}

	}
	
	private static int find(int s) {
		if(set[s] == s) {
			return s;
		}
		return set[s] = find(set[s]);
	}
	private static boolean union(int s1, int s2) {
		int x = find(s1);
		int y = find(s2);
		if(x != y) {
			if(rank[x] > rank[y]) {
				set[y] = x;
			}else {
				set[x] = y;
			}
			if(rank[x] == rank[y]) {
				rank[x]++;
			}
			return true;
		}
		return false;
	}

}
