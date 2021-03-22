package K2021합승택시요금;

import java.util.*;

public class Solution {
	
	ArrayList<Edge>[] adjList;
	static class Edge{
		int to, cost;
		public Edge(int b, int c) {
			this.to = b;
			this.cost = c;
		}
	}
	public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        int[] v = new int[n+1];
        adjList = new ArrayList[n+1];
        for(int i = 0; i<=n; i++) {
        	adjList[i] = new ArrayList<Edge>();
        }
        for(int[] f : fares) {
        	int from = f[0];
        	int to = f[1];
        	int cost = f[2];
        	adjList[from].add(new Edge(to, cost));
        	adjList[to].add(new Edge(from, cost));
        }
        int[][] dist = new int[n+1][n+1];
        int min = Integer.MAX_VALUE;
		for(int i = 0; i<=n; i++) {
			for(int j = 0; j<=n; j++) {
				dist[i][j] = Integer.MAX_VALUE;				
			}
		}
        dijkstra(n, s, dist);
        dijkstra(n, a, dist);
        dijkstra(n, b, dist);
        
        for(int i = 1; i<=n; i++) {
        	min = Math.min(min, dist[s][i] +  dist[a][i] + dist[b][i]);
        }
        return min;
    }
	
	public void dijkstra(int n, int s, int[][] dist) {
		PriorityQueue<int[]> pque = new PriorityQueue<>((x, y)->Integer.compare(x[1], y[1]));
		dist[s][s] = 0;
		pque.offer(new int[] {s, 0});
		
		while(!pque.isEmpty()) {
			int[] p = pque.poll();
			int v = p[0];
			int cost = p[1];
			
			if(dist[s][v] < cost) continue;
			for(Edge edge: adjList[v]) {
				int u = edge.to;
				int addCost = edge.cost;
				if(dist[s][u] > cost + addCost) {
					dist[s][u] = cost + addCost;
					pque.offer(new int[] {u, cost+addCost});
				}
			}
		}
	}
	
	void print(int[][] dist, int n) {
		for(int i = 1; i<=n; i++) {
			System.out.println();
			System.out.println("시작점: " + i);
			for(int j = 1; j<=n; j++) {
				if(i == j) continue;
				System.out.println("도착점 비용: " + j + " " + dist[i][j]);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Solution sol = new Solution();
		int n = 6;
		int s = 4;
		int a = 6;
		int b = 2;
		int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
		System.out.println(sol.solution(n, s, a, b, fares));
	}
}
