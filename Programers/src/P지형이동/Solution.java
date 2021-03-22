package P지형이동;

import java.util.*;

public class Solution {
	int[][] map;
	int[] dr = {1, 0, -1, 0};
	int[] dc = {0, 1, 0, -1};
	int N;
	static class Edge{
		int from,to, cost;
		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", cost=" + cost + "]";
		}		
	}
	List<Edge>[] adjList;
	int[] set;
	public int solution(int[][] land, int height) {
        int answer = 0;
        N = land.length;
        map = new int[N][N];
        
        //마킹
        int label = 1;
        for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(map[i][j] == 0) {
					fill(land, label++, i, j, height);
				}
			}
		}
        
        //MST
        set = new int[label];
        for(int i = 0; i<label; i++) {
        	set[i] = i;
        }
        PriorityQueue<Edge> pque = new PriorityQueue<>((a, b)->Integer.compare(a.cost, b.cost));
        for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				addEdge(land, pque,  i, j);
			}
		}
        
        //크루스칼
        while(!pque.isEmpty()) {
        	Edge e = pque.poll();
        	if(union(e.from, e.to)) {
        		answer += e.cost;
        	}
        }     
        return answer;
    }
	
	private void fill(int[][] land, int n, int r, int c, int h) {
		map[r][c] = n;
		for(int d = 0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr>=0 && nr<N && nc>=0 && nc<N && map[nr][nc] == 0 && h >= Math.abs(land[r][c] - land[nr][nc])) {
				fill(land, n, nr, nc, h);
			}
		}
	}
	
	private void addEdge(int[][] land, PriorityQueue<Edge> pque, int r, int c) {		
		int from = map[r][c];
		for(int d= 0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr>=0 && nr<N && nc>=0 && nc<N && from != map[nr][nc]) {
				pque.offer(new Edge(from, map[nr][nc], Math.abs(land[r][c] - land[nr][nc])));
			}
		}
	}
	
	private int find(int s) {
		if(s == set[s]) return s;
		return set[s] = find(set[s]);
	}
	
	private boolean union(int s1, int s2) {
		int x = find(s1);
		int y = find(s2);
		if(x!=y) {
			set[y] = x;
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		//int[][] land = new int[][] {{1, 4, 8, 10}, {5, 5, 5, 5}, {10, 10, 10, 10}, {10, 10, 10, 20}};
		//int height = 3;
		int[][] land = {{10, 11, 10, 11}, {2, 21, 20, 10}, {1, 20, 21, 11}, {2, 1, 2, 1}};
		int height = 1;
		
		System.out.println(s.solution(land, height));
	}
}
