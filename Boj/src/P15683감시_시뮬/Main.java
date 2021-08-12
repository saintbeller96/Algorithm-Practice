package P15683감시_시뮬;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int R, C, answer;
	static int[][] map;
	static class Cctv{
		int r, c;
		int type;
		public Cctv(int r, int c, int type) {
			this.r = r;
			this.c = c;
			this.type = type;
		}
	}
	static int[][][] dtype = {{},
			{{0}, {1}, {2}, {3}},
			{{1, 3}, {0, 2}},
			{{0,1},{1, 2}, {2, 3}, {3, 0}},
			{{3, 0, 1}, {0, 1, 2}, {1, 2, 3}, {2, 3, 0}},
			{{0,1,2,3}}
	};

	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		map = new int[R][C];
		List<Cctv> cctvs = new ArrayList<>();
		for(int i = 0; i<R; i++){
			stk = new StringTokenizer(br.readLine());
			for(int j = 0; j<C; j++){
				map[i][j] = Integer.parseInt(stk.nextToken());
				if(map[i][j] > 0 && map[i][j] < 6){
					cctvs.add(new Cctv(i, j, map[i][j]));
				}
			}
		}
		int[] direction = new int[cctvs.size()];
		answer = Integer.MAX_VALUE;
		dfs(direction, cctvs, 0);
		System.out.println(answer);
	}
	static void dfs(int[] direction, List<Cctv> cctvs,  int depth){
		if(depth == cctvs.size()){
			boolean[][] sight = new boolean[R][C];
			for(int i = 0; i< cctvs.size(); i++){
				Cctv cctv = cctvs.get(i);
				int[][] dir = dtype[cctv.type];

				for(int d : dir[direction[i]]){
					light(sight, cctv, d);
				}
			}
			int cnt = 0;
			for (int i = 0; i <	R ; i++) {
				for (int j = 0; j < C; j++) {
					if(!sight[i][j]) {
						if(map[i][j] == 6) continue;
						cnt++;
					}
				}
			}
			answer = Math.min(answer, cnt);
			return;
		}
		Cctv cctv = cctvs.get(depth);
		int[][] dir = dtype[cctv.type];
		for (int i = 0; i < dir.length; i++) {
			direction[depth] = i;
			dfs(direction, cctvs, depth+1);
		}
	}
	static void light(boolean[][] sight, Cctv cctv, int d) {
		int r = cctv.r;
		int c = cctv.c;
		sight[r][c] = true;
		while(true){
			r+= dr[d];
			c+= dc[d];
			if(r<0 || r>=R || c<0 || c>=C){
				break;
			}
			sight[r][c] = true;
			if(map[r][c] == 6) break;
		}
	}
}
