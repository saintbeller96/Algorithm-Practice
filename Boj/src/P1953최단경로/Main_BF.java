package P1953최단경로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BF {
	static class Edge{
		int u, v, w;
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}
	static int V, E;
	static ArrayList<Edge> edges;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		V = Integer.parseInt(stk.nextToken());
		E = Integer.parseInt(stk.nextToken());
		int s = Integer.parseInt(br.readLine());
		edges = new ArrayList<Edge>();
		for(int i = 0; i<E; i++) {
			stk = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(stk.nextToken());
			int v = Integer.parseInt(stk.nextToken());
			int w = Integer.parseInt(stk.nextToken());
			edges.add(new Edge(u, v, w));
		}
		bellman(s);
	}
	
	static void bellman(int start) {
		//dist���� ������ �����ϴ� �ִ� �Ÿ� ����
		int[] dist = new int[V+1];
		for(int i = 1; i<=V; i++) {
			//�ִ밪���� �ʱ�ȭ
			dist[i] = Integer.MAX_VALUE;
		}
		//�������� 0���� �ٲ���
		dist[start] = 0;
		
		//������ ����-1 ��ŭ �ݺ� ����
		for(int i = 1; i < V; i++) {
			for(Edge edge : edges) {
				int u = edge.u;//���� ����
				int v = edge.v;//���� ����
				//���� ���� ������ ����� �Ÿ��� ���� ������ ����� �Ÿ��� ���� ������ ����ġ �պ��� ũ�ٸ� ����
				dist[v] = Math.min(dist[v], dist[u] + edge.w);
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
	}
}
