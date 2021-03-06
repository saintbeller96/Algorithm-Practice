package P3124스패닝;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution2 {
	private static class Edge{
		int u, v, w;
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}
	private static class Vertex{
		int n, d, p;
		public Vertex(int n, int d, int p) {
			this.n = n;
			this.d = d;
			this.p = p;
		}
		
	}
	private static int T, V, E;
	private static int[] set;
	private static HashMap<Integer, ArrayList<int[]>> adjList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			stk = new StringTokenizer(br.readLine());
			V = Integer.parseInt(stk.nextToken());
			E = Integer.parseInt(stk.nextToken());
			adjList = new HashMap<Integer, ArrayList<int[]>>();
			set = new int[V+1];
			long answer = 0L;
			PriorityQueue<Vertex> que = new PriorityQueue<Vertex>((a, b)->Integer.compare(a.d, b.d));
			for(int i = 0; i<E; i++) {
				stk = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(stk.nextToken());
				int v = Integer.parseInt(stk.nextToken());
				int w = Integer.parseInt(stk.nextToken());
				
				if(!adjList.containsKey(u)) {
					adjList.put(u, new ArrayList<int[]>());
				}
				if(!adjList.containsKey(v)) {
					adjList.put(v, new ArrayList<int[]>());
				}
				adjList.get(u).add(new int[] {v, w});
				adjList.get(v).add(new int[] {u, w});
				
				que.offer(new Vertex(u, Integer.MAX_VALUE, 0));
			}
			Vertex s = que.peek();
			s.d  = 0;
			
			while(!que.isEmpty()) {
				Vertex v = que.poll();
				
				for(int[] edge : adjList.get(v.n)) {
					int z = edge[0];
					int w = edge[1];
					
				}
			}
			System.out.println("#" + t + " " + answer);
		}

	}
}
