package P17144미세먼지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, T;
	static int[][] filter = { { -1, -1 }, { -1, -1 } };
	static int[][][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		T = Integer.parseInt(stk.nextToken());
		map = new int[R][C][2];
		int idx = 0;
		for (int i = 0; i < R; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j][0] = Integer.parseInt(stk.nextToken());
				if (map[i][j][0] == -1) {
					filter[idx][0] = i;
					filter[idx++][1] = j;
				}
			}
		}

		for (int t = 1; t <= T; t++) {
			simulation();
		}
		int answer = 0;
		for(int i = 0; i<R; i++) {
			for(int j = 0; j<C; j++) {
				if(map[i][j][0] != -1) {
					answer += map[i][j][0];
				}
			}
		}
		System.out.println(answer);

	}

	private static void simulation() {
		
		for(int r = 0; r <R; r++) {
			for(int c = 0; c<C; c++) {
				if(map[r][c][0] != 0) {
					spread(r, c);
				}
			}
		}
		
		for(int r = 0; r <R; r++) {
			for(int c = 0; c<C; c++) {
				map[r][c][0] += map[r][c][1];
				map[r][c][1] = 0;
			}
		}	
		//print();
		wind();
		//print();
	}

	private static void spread(int r, int c) {
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int i = r + dr[d];
			int j = c + dc[d];
			if (i >= 0 && i < R && j >= 0 && j < C) {
				if (map[i][j][0] != -1) {
					cnt++;
				}
			}
		}

		int n = map[r][c][0] / 5;
		if (n >= 0)
			map[r][c][1] -= n * cnt;

		for (int d = 0; d < 4; d++) {
			int i = r + dr[d];
			int j = c + dc[d];
			if (i >= 0 && i < R && j >= 0 && j < C) {
				if (map[i][j][0] != -1) {
					map[i][j][1] += n;
				}
			}
		}
	}

	private static void wind() {
		int ar = filter[0][0] ;
		int ac = filter[0][1] ;
		int r = ar;
		int c = ac + 1 ;
		
		int a = 0;
		
		for(; c < C-1; c++) {
			int tmp = map[r][c][0];
			map[r][c][0] = a;
			a = tmp;
		}
		for(; r > 0 ; r--) {
			int tmp = map[r][c][0];
			map[r][c][0] = a;
			a = tmp;
		}
		for( ; c>0; c--) {
			int tmp = map[r][c][0];
			map[r][c][0] = a;
			a = tmp;
		}
		for( ; r< ar; r++) {
			int tmp = map[r][c][0];
			map[r][c][0] = a;
			a = tmp;
		}
		for(; c < ac; c++) {
			int tmp = map[r][c][0];
			map[r][c][0] = a;
			a = tmp;
		}
		
		ar = ar+1;
		r = ar;
		c = ac+1;
		a = 0;
		for(; c < C-1; c++) {
			int tmp = map[r][c][0];
			map[r][c][0] = a;
			a = tmp;
		}
		for(; r < R-1 ; r++) {
			int tmp = map[r][c][0];
			map[r][c][0] = a;
			a = tmp;
		}
		for( ; c > 0; c--) {
			int tmp = map[r][c][0];
			map[r][c][0] = a;
			a = tmp;
		}
		for( ; r > ar; r--) {
			int tmp = map[r][c][0];
			map[r][c][0] = a;
			a = tmp;
		}
		for(; c < ac; c++) {
			int tmp = map[r][c][0];
			map[r][c][0] = a;
			a = tmp;
		}
		
	}
	
	static void print() {
		for (int i = 0; i < R; i++) {
			for(int j = 0; j <C; j++) {
				System.out.print(map[i][j][0] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
