package P2406안정적인네트워크;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static class Edge{
		int s, d, cost;
		public Edge(int s, int d, int cost) {
			this.s = s;
			this.d = d;
			this.cost = cost;
		}
	}
	static int[][] adjMap;
	static int[] set;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		set = new int[n+1];
		for(int i = 1; i<=n; i++) {
			set[i] = i;
		}
		for(int i = 0; i<m; i++) {
			stk = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(stk.nextToken());
			int y = Integer.parseInt(stk.nextToken());
			//x와 y는 합칩
			union(x, y);
		}
		adjMap = new int[n+1][n+1];
		for(int i = 1; i<=n; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 1; j<=n; j++) {
				adjMap[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		int answer = 0;
		ArrayList<int[]> connect = new ArrayList<>();
		PriorityQueue<Edge> que = new PriorityQueue<Edge>((a, b)->Integer.compare(a.cost, b.cost));
		for(int i = 2; i<=n; i++) {
			for(int j = i+1; j<=n; j++) {
				if(i != j) {
					que.offer(new Edge(j, i, adjMap[i][j]));
				}
			}
		}
		while(!que.isEmpty()) {
			Edge edge = que.poll();
			if(union(edge.s, edge.d)) {
				answer += edge.cost;
				connect.add(new int[] {edge.s, edge.d});
			}
		}
		if(answer==0) {
			System.out.println(answer + " " + 0);
		}else {
			System.out.println(answer + " " + connect.size());
			for(int[] c :connect) {
				System.out.println(c[0] + " " + c[1]);
			}
		}
		
	}
	static int find(int s) {
		if(set[s] == s) return set[s];
		return set[s] = find(set[s]);
	}
	static boolean union(int s1, int s2) {
		int x = find(s1);
		int y = find(s2);
		if(x!=y) {
			set[y] = x;
			return true;
		}
		return false;
	}
}
