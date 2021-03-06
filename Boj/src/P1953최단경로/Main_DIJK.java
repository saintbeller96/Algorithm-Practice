package P1953최단경로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_DIJK {
	static class Vertex{
		int n, cost;
		public Vertex(int n, int cost) {
			this.n = n;
			this.cost = cost;
		}
	}
	static class Edge{
		int u, v, w;
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}
	static int V, E;
	static HashMap<Integer, ArrayList<Edge>> adjList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		V = Integer.parseInt(stk.nextToken());
		E = Integer.parseInt(stk.nextToken());
		int s = Integer.parseInt(br.readLine());
		adjList = new HashMap<Integer, ArrayList<Edge>>();
		for(int i = 0; i<E; i++) {
			stk = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(stk.nextToken());
			int v = Integer.parseInt(stk.nextToken());
			int w = Integer.parseInt(stk.nextToken());
			
			if(!adjList.containsKey(u)) {
				adjList.put(u, new ArrayList<Edge>());
			}
			adjList.get(u).add(new Edge(u, v, w));
		}
		dijk(s);
	}
	
	static int dijk(int start) {
		//cost ���� ���������ϴ� �켱����ť
		PriorityQueue<Vertex> que = new PriorityQueue<Vertex>((a, b)->Integer.compare(a.cost, b.cost));
		
		//������ ��ϵǴ� �ִܰŸ�
		int[] dist = new int[V+1];
		for(int i = 1; i<=V; i++) {
			if(i == start) {
				dist[i] = 0;
			}
			else {
				dist[i] = Integer.MAX_VALUE;
			}
		}
		que.offer(new Vertex(start, 0));

		while(!que.isEmpty()) {
			Vertex u = que.poll();
			if(dist[u.n] >= u.cost) {
				if(!adjList.containsKey(u.n)) continue;
				for(Edge e : adjList.get(u.n)) {
					int z = e.v;//���� ����
					int cost = e.w;//���̿� �ִ� ������ ����ġ
					
					if(dist[z] > u.cost + cost) {
						dist[z] = u.cost + cost;
						que.offer(new Vertex(z, u.cost + cost));
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i<=V; i++) {
			if(i == start) sb.append(0).append('\n');
			else if(dist[i] == Integer.MAX_VALUE) {
				sb.append("INF").append('\n');
			}else {
				sb.append(dist[i]).append('\n');
			}
		}
		System.out.println(sb);
		return 0;
	}
}
