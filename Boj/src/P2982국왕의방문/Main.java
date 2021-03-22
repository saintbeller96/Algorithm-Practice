package P2982국왕의방문;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, A, B, K, G;
	static int[][] adjMatrix;
	static ArrayList<Integer> cross;
	static Edge[] edges;
	static class Vertex{
		int vn, time;
		public Vertex(int vn, int time) {
			this.vn = vn;
			this.time = time;
		}
	}
	static class Edge{
		int n, u, v, l;
		int s, e;
		public Edge(int n, int u, int v, int l) {
			this.n = n;
			this.u = u;
			this.v = v;
			this.l = l;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		stk = new StringTokenizer(br.readLine());
		A = Integer.parseInt(stk.nextToken());
		B = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		G = Integer.parseInt(stk.nextToken());
		
		adjMatrix = new int[N+1][N+1];
		//교차로
		cross = new ArrayList<Integer>();
		
		edges = new Edge[M+1];
		stk = new StringTokenizer(br.readLine());
		for(int i = 0; i<G; i++) {
			int c = Integer.parseInt(stk.nextToken());
			cross.add(c);
		}
		for(int i = 1; i<=M; i++) {
			stk = new StringTokenizer(br.readLine());
			int U = Integer.parseInt(stk.nextToken());
			int V = Integer.parseInt(stk.nextToken());
			int L = Integer.parseInt(stk.nextToken());
			adjMatrix[U][V] = i;
			adjMatrix[V][U] = i;
			
			edges[i] = new Edge(i, U, V, L);
		}
		System.out.println(dijk());
		
	}
	
	static int dijk() {
		int s = cross.get(0);
		int e = -1;
		int sum = 0;
		for(int i = 1; i<G; i++) {
			e = cross.get(i);
			int edgeIdx = adjMatrix[s][e];
			edges[edgeIdx].s = sum;
			edges[edgeIdx].e = sum + edges[edgeIdx].l -1;
			//System.out.println(edges[edgeIdx].s + " " + edges[edgeIdx].e);
			sum += edges[edgeIdx].l;
			s = e;
		}
		
		int[] dist = new int[N+1];
		for(int i = 1; i<=N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		dist[A] = K;
		
		PriorityQueue<Vertex> que = new PriorityQueue<Vertex>((a, b)->Integer.compare(a.time, b.time));
		que.offer(new Vertex(A, K));
		
		while(!que.isEmpty()) {
			Vertex v = que.poll();
			if(dist[v.vn] >= v.time) {
				for(int u = 1 ;u <= N; u++) {
					int idx = adjMatrix[v.vn][u];
					if(idx != 0) {
						int wait = 0;
						int curTime = v.time ;
						s = edges[idx].s;
						e = edges[idx].e;
						
						if(curTime >= s && curTime <= e) {
							wait = e - curTime + 1 + edges[idx].l;
						}else {
							wait = edges[idx].l;
						}
						//System.out.println(v.vn + " " + u + ": " + s + "~" + e + ", " + curTime + " " + wait);
						if(dist[u] > curTime + wait) {
							dist[u] = curTime + wait;
							que.add(new Vertex(u, dist[u]));
						}
					}
				}
			}
		}
		return dist[B]-K;
	}

}
