package P20313출퇴근;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, A, B, K;
	static int[][] roads;
	static class Edge{
		int u, v, t, id;
		public Edge(int u, int v, int t, int id) {
			this.u = u;
			this.v = v;
			this.t = t;
			this.id = id;
		}
	}
	static class Vertex{
		int id, spend_magic;
		long cost;
		public Vertex(int id, long cost) {
			this.id = id;
			this.cost = cost;
		}
	}
	static ArrayList<Edge>[] adjList;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		A = Integer.parseInt(stk.nextToken());
		B = Integer.parseInt(stk.nextToken());
		
		adjList = new ArrayList[N+1];
		for(int i = 1; i<=N; i++) {
			adjList[i] = new ArrayList<Edge>();
		}
		for(int i = 0; i<M; i++) {
			stk = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(stk.nextToken());
			int v = Integer.parseInt(stk.nextToken());
			int t = Integer.parseInt(stk.nextToken());
			
			adjList[u].add(new Edge(u, v, t, i));
			adjList[v].add(new Edge(v, u, t, i));
		}
		
		K = Integer.parseInt(br.readLine());
		roads = new int[K+1][M];
		for(int k =1; k<=K; k++) {
			stk = new StringTokenizer(br.readLine());
			for(int i = 0; i<M; i++) {
				roads[k][i] = Integer.parseInt(stk.nextToken());
			}
		}
		for(int i = 1; i<=N; i++) {
			for(Edge e : adjList[i]) {
				roads[0][e.id] = e.t;
			}
		}
		System.out.println(dijk());
		return;
	}
 	
 	static long dijk() {
 		PriorityQueue<Vertex> pque = new PriorityQueue<Vertex>((a, b)->Long.compare(a.cost, b.cost));
 		long dist[] = new long[N+1];
 		for(int i = 1; i<=N; i++) {
 			dist[i] = Long.MAX_VALUE;
 		}
 		
 		dist[A]= 0;
 		
 		pque.offer(new Vertex(A, 0));
 		for(int k = 0; k<=K; k++) {
 			for(int i = 1; i<=N; i++) {
 				if(dist[i] != Integer.MAX_VALUE) {
 					pque.offer(new Vertex(i, dist[i]));
 				}
 			}
 			while(!pque.isEmpty()) {
 	 			Vertex v = pque.poll();
 	 			if(dist[v.id] >= v.cost) {
 	 				for(Edge e : adjList[v.id]) {
 	 					int dest = e.v;
 	 					int newCost = roads[k][e.id];
	 					if(dist[dest] > v.cost + newCost) {
	 						dist[dest] = v.cost + newCost;
	 						pque.offer(new Vertex(dest, dist[dest]));
	 					}
 	 				}
 	 			}
 	 		}
 		}
 		
 		return dist[B];
 	}
 	
}
