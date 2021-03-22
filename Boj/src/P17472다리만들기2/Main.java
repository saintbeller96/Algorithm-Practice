package P17472다리만들기2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static class Edge{
		int u, v, w;
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		
		@Override
		public String toString() {
			return "Edge [u=" + u + ", v=" + v + ", w=" + w + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + u;
			result = prime * result + v;
			result = prime * result + w;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			Edge other = (Edge) obj;
			if (u != other.u)
				return false;
			if (v != other.v)
				return false;
			if (w != other.w)
				return false;
			return true;
		}
	}
	static int[] islands;
	static HashSet<Edge> edgeList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		map = new int[N][M];
		for(int i = 0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		boolean[][] b = new boolean[N][M];
		islands = new int[11];
		//섬에 숫자 마킹
		int n = 1;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(!b[i][j] && map[i][j]==1) {
					islands[n] = n;
					marking(i, j, n++, b);
				}
			}
		}
		//print();
		edgeList = new HashSet<Edge>();
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(map[i][j] != 0) {
					findWay(i, j, map[i][j]);
				}
			}
		}
		//System.out.println(edgeList);
		PriorityQueue<Edge> que = new PriorityQueue<Edge>((x, y)->Integer.compare(x.w, y.w));
		for(Edge e : edgeList) {
			que.offer(e);
		}
		int answer = 0;
		int cnt = 0;
		while(!que.isEmpty()) {
			Edge edge = que.poll();
			if(union(edge.u, edge.v)) {
				answer+=edge.w;
				cnt++;
			}
		}
		if(n-2 != cnt) answer = -1;
		System.out.println(answer);
		
	}
	static int find(int s) {
		if(islands[s] == s) return s;
		return islands[s] = find(islands[s]);
	}
	static boolean union(int s1, int s2) {
		int x = find(s1);
		int y = find(s2);
		if(x != y) {
			islands[y] = x;
			return true;
		}
		return false;
	}
	//해당 섬의 좌표에서 만들수 있는 길이 있는지 확인
	static void findWay(int r, int c, int n) {
		for(int d = 0; d<4; d++) {
			int len = 0;
			int nr = r;
			int nc = c;
			if(nr >=0 && nr <N && nc >= 0 && nc <M){
				//다리를 놓을 수 있는 장소(0)가 있으면
				while(true) {
					nr += dr[d];
					nc += dc[d];
					if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
						break;
					}
					if(map[nr][nc] == n) {
						break;
					}
					if(map[nr][nc] != 0) {
						if(len > 1) {
							int a = Math.max(n, map[nr][nc]);
							int b = Math.min(n, map[nr][nc]);
							edgeList.add(new Edge(a, b, len));
						}
						break;
					}
					len++;
				}
				
			}
		}
	}
	
	//섬에 숫자를 붙혀줌
	static void marking(int r, int c, int n, boolean[][] b) {
		if(b[r][c]) return;
		b[r][c] = true;
		map[r][c] = n;
		for(int d = 0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr >=0 && nr <N && nc >= 0 && nc <M) {
				if(map[nr][nc] != 0) marking(nr, nc, n, b);
			}
		}
	}
	static void print() {
		for (int i = 0; i < map.length; i++) {
			for(int j = 0; j<M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
