package P배달;

import java.util.*;

public class Solution {
	private static class Edge{
		int to, cost;
		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	private List<Edge>[] adjList;
	public int solution(int N, int[][] road, int K) {
		int answer = 0;
		adjList = new List[N+1];
		for(int i = 0; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		for(int[] edge : road) {
			int s = edge[0];
			int d = edge[1];
			int c = edge[2];
			adjList[s].add(new Edge(d, c));
			adjList[d].add(new Edge(s, c));
		}
		
		int[] dist = dijk(N, K);
		for(int i = 1; i<=N; i++) {
			if(dist[i] <= K) answer++;
		}

		return answer;
    }
	
	private int[] dijk(int n, int k) {
		int[] dist = new int[n+1];
		for(int i = 1; i<=n; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		PriorityQueue<int[]> pque = new PriorityQueue<>((a, b)->Integer.compare(a[1], b[1]));
		pque.offer(new int[] {1, 0});
		dist[1] = 0;
		while(!pque.isEmpty()) {
			int[] p = pque.poll();
			if(dist[p[0]] < p[1]) continue;
			for(Edge edge: adjList[p[0]]) {
				int u = edge.to;
				int cost = edge.cost;
				if(dist[u] > p[1] + cost) {
					dist[u] = p[1] + cost;
					pque.offer(new int[] {u, dist[u]});
				}
			}
		}
		
		return dist;
	}
	
	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		int N = 5;
		int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
		int K = 3;
		System.out.println((s.solution(N, road, K)));
	}
}
