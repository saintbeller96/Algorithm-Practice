package P11657타임머신;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static class Edge{
		int u, v, w;
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}
	static ArrayList<Edge> edges;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		edges = new ArrayList<Edge>();
		for(int i = 0; i<M; i++) {
			stk = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(stk.nextToken());
			int v = Integer.parseInt(stk.nextToken());
			int w = Integer.parseInt(stk.nextToken());
			
			edges.add(new Edge(u, v, w));
		}
		bellman(1);

	}
	static void bellman(int start) {
		//dist���� ������ �����ϴ� �ִ� �Ÿ� ����
		long[] dist = new long[N+1];
		for(int i = 1; i<=N; i++) {
			//�ִ밪���� �ʱ�ȭ
			dist[i] = Integer.MAX_VALUE;
		}
		//�������� 0���� �ٲ���
		dist[start] = 0;
		//������ ����-1 ��ŭ �ݺ� ����
		for(int i = 1; i <= N; i++) {
			for(Edge edge : edges) {
				int u = edge.u;//���� ����
				int v = edge.v;//���� ����
				if(dist[u] != Integer.MAX_VALUE) {
					//���� ���� ������ ����� �Ÿ��� ���� ������ ����� �Ÿ��� ���� ������ ����ġ �պ��� ũ�ٸ� ����
					dist[v] = Math.min(dist[v], dist[u] + edge.w);
				}
			}
		}
		//���� ����Ŭ �ִ��� Ȯ��
		for(Edge edge : edges) {
			int u = edge.u;//���� ����
			int v = edge.v;//���� ����
			if(dist[v] == Integer.MAX_VALUE) continue;
			if(dist[v]  > dist[u] + edge.w) {
				System.out.println("-1");
				return;
			}
		}
		

		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= N; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				sb.append(-1).append('\n');
			} else {
				sb.append(dist[i]).append('\n');
			}
		}
		System.out.println(sb);
		
	}

}
