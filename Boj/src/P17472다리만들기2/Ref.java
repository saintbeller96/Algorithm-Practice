package P17472다리만들기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Ref {

	static int N, M;
	static int[][] map, adjMatrix;
	static boolean[][] visited; 
	static List<Edge> edges;
	static int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	
	static int islandIdx, totalCost; // ���� ��, �ٸ��� ���� 
	
	// kruskal 
	static int[] repres;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;
		
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		for(int r = 0; r < N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		visited = new boolean[N][M];
		islandIdx = 2;
		divideIsland();
		
		edges = new ArrayList<>();
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] > 1) {
					makeEdgeList(r, c);
				}
			}
		}
		
		// Kruskal�� �̿��Ͽ� ã�ƺ��� !
		totalCost = 0;
		kruskal();
		System.out.println(totalCost);
	}
	
	static void kruskal() {
		// �ּҰ� ���� Ƣ������� ���� ���� 
		Collections.sort(edges);
		
		// makeset
		makeSet();
		
		// union ó�� 
		for(int i = 0; i < edges.size(); i++) {
			Edge edge = edges.get(i);
			union(edge.a, edge.b, edge.cost);
		}
		
		// ��� ����Ǿ� �ִ��� Ȯ�� 
		int repre = findSet(2);
//		System.out.println(repre);
		for(int i = 3; i < repres.length; i++) {
			if(repre != findSet(repres[i])) {
				totalCost = -1;
				return;
			}
		}
	}
	
	static void makeSet() {
		repres = new int[islandIdx];
		for(int i = 0; i < repres.length; i++) {
			repres[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(repres[a] == a) {
			return a;
		}
		return repres[a] = findSet(repres[a]);
	}
	
	static void union(int a, int b, int cost) {
		a = findSet(a);
		b = findSet(b);
		
		if(a != b) {
			repres[a] = b;
			totalCost += cost;
		}
	}
	
	static void makeEdgeList(int row, int col) {
		int base = map[row][col];
		
		for(int[] dir : dirs) {
			// �� �������� ��� ���� 
			for(int l = 1;  ;l++) {
				int dr = row + dir[0] * l;
				int dc = col + dir[1] * l;
				
				if(isIn(dr, dc)) {
					if(map[dr][dc] == 0) { // �ٴٸ� ? 
						continue;
					} else if(map[dr][dc] == base){ // �����̸�?
						break;
					} else {
						if(l > 2) {
							// �� �տ������� �ٸ��� �����̹Ƿ� l - 1;
							edges.add(new Edge(base, map[dr][dc], l - 1));
						} 
						break;
					}
				} else {
					break;
				}
			}
		}
	}
	
	static void dfs(int row, int col, int cnt) {
		map[row][col] = cnt;
		
		for(int[] dir : dirs) {
			int dr = dir[0] + row;
			int dc = dir[1] + col;
			if(isIn(dr, dc) && map[dr][dc] == 1) {
				dfs(dr, dc, cnt);
			}
		}
	}

	static void divideIsland() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 1) {
					dfs(i, j, islandIdx);
					islandIdx++;
				}
			}
		}
	}
	
	static boolean isIn(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}
	
	static class Edge implements Comparable<Edge> {
		int a, b, cost;

		public Edge(int a, int b, int cost) {
			super();
			this.a = a;
			this.b = b;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
}