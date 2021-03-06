package P16236아기상어;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<int[]> fishes;
	static int[] dr = {1,-1, 0 ,0};
	static int[] dc = {0, 0,-1, 1};
 	static class Shark{
		public int r, c, size, eat, time;
		public Shark() {};
		public Shark(int r, int c, int size, int eat, int time) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.eat = eat;
			this.time = time;
		}
		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", size=" + size + ", eat=" + eat + ", time=" + time + "]";
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		fishes = new ArrayList<int[]>();
		
		Shark shark = null;
		
		for(int i = 0 ; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if(map[i][j] == 9) {
					map[i][j] = 0;
					shark = new Shark(i, j, 2, 0, 0);
				}else if(map[i][j] != 0) {
					fishes.add(new int[] {i, j, map[i][j]});
				}
			}
		}
		while(true) {
			Shark s = go(map, shark);
			if(s.r == 100 && s.c == 100) {
				break;
			}else {
				map[s.r][s.c] = 0;
				//System.out.println(s);
				shark = s;
				shark.eat++;
				if(shark.eat == shark.size) {
					shark.size++;
					shark.eat = 0;
				}
			}
		}
		System.out.println(shark.time);
	}
	static Shark go(int[][] map, Shark shark) {
		Queue<Shark> que = new LinkedList<Shark>();
		boolean[][] visited = new boolean[N][N];
		boolean flag = false;
		visited[shark.r][shark.c] = true;
		que.offer(shark);
		
		Shark ret = new Shark(100,100, 0,0,0);
		
		while(!que.isEmpty()) {
			int s = que.size();
			for(int i =0; i < s; i++) {
				Shark cShark = que.poll();

				if(map[cShark.r][cShark.c] != 0 && map[cShark.r][cShark.c] < cShark.size) {
					flag = true;
					if(cShark.r < ret.r) {
						ret = cShark;
					}else if(cShark.r == ret.r) {
						if(cShark.c < ret.c) {
							ret = cShark;
						}
					}
				}
				
				for(int d = 0; d<4; d++) {
					int nr = cShark.r + dr[d];
					int nc = cShark.c + dc[d];
					if(nr >=0 && nr < N && nc >= 0 && nc < N && map[nr][nc] <= cShark.size) {
						if(!visited[nr][nc]) {
							visited[nr][nc] = true;
							que.offer(new Shark(nr, nc, cShark.size, cShark.eat, cShark.time + 1));
						}
					}
				}
			}
			if(flag) break;
		}
		return ret;
	}
	static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
