package P1953최단경로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_DIJK2 {
	//���� Ŭ����
	static class Vertex{
		//n�� ���� ��ȣ, cost�� �������� ��� ����
		int n, cost;
		public Vertex(int n, int cost) {
			this.n = n;
			this.cost = cost;
		}
	}
	static class Edge{
		//u: ����, v: ����, w: ����ġ
		int u, v, w;
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}
	static int V, E;
	static ArrayList<Edge>[] list;//���� ����Ʈ
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		V = Integer.parseInt(stk.nextToken());
		E = Integer.parseInt(stk.nextToken());
		int s = Integer.parseInt(br.readLine());

		//���� ����Ʈ ����
		list = new ArrayList[V+1];
		for(int i = 0; i<=V; i++) {
			list[i] = new ArrayList<Edge>();
		}
		for(int i = 0; i<E; i++) {
			stk = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(stk.nextToken());
			int v = Integer.parseInt(stk.nextToken());
			int w = Integer.parseInt(stk.nextToken());
			//���� ����Ʈ�� ���� ���� ����
			list[u].add(new Edge(u, v, w));
		}
		dijk(s);
	}
	
	static int dijk(int start) {
		//cost ���� ���������ϴ� �켱����ť
		PriorityQueue<Vertex> que = new PriorityQueue<Vertex>((a, b)->Integer.compare(a.cost, b.cost));
		
		//dist: ������ ��ϵǴ� �ִܰŸ� ���
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
			//���� u�� ��ϵ� �ִܰŸ��� �� ������ ���� ����
			if(dist[u.n] >= u.cost) {
				for(Edge e : list[u.n]) {
					int z = e.v;//���� ����
					int cost = e.w;//���̿� �ִ� ������ ����ġ
					
					//���� ��ϵ� �ִܰŸ����� �̹��� ����� �ִܰŸ��� �� ���� ���
					if(dist[z] > u.cost + cost) {
						//�ִܰŸ� ���� �� ť�� ����
						dist[z] = u.cost + cost;
						que.offer(new Vertex(z, u.cost + cost));
					}
				}
			}
		}
		//���
		for(int i = 1; i<=V; i++) {
			if(i == start) {
				System.out.println(0);
			}
			else if(dist[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			}else {
				System.out.println(dist[i]);
			}
		}
		return 0;
	}
}
