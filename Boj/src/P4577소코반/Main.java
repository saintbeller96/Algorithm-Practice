package P4577소코반;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int R, C;
	static char[] movements;
	static char[][] map;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	static ArrayList<int[]> targets;
	static int[] position;
	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		for(int tc = 1; ; tc++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			R = Integer.parseInt(stk.nextToken());
			C=  Integer.parseInt(stk.nextToken());
			if(R == 0 && C == 0) break;
			
			map = new char[R][];
			targets = new ArrayList<>();
			position = new int[] {-1, -1};

			for(int i = 0; i<R; i++) {
				map[i] = br.readLine().toCharArray();
				for(int j = 0; j<C; j++) {
					char c = map[i][j];
					if(c == '+' || c == 'B') {
						targets.add(new int[] {i, j});
					}else if(c == 'w') {
						position[0] = i;
						position[1] = j;
						map[i][j] = '.';
					}else if(c == 'W') {
						position[0] = i;
						position[1] = j;
						map[i][j] = '+';
						targets.add(new int[] {i, j});
					}
				}
			}
			movements = br.readLine().toCharArray();
			boolean result = false;
			for(char move : movements) {
				int d = direction(move);
				move(d);
				//printTest();
				result = isFinished();
				if(result) break;
			}
			
			if(result) {
				System.out.println("Game " + tc + ": complete");
				print();
			}else {
				System.out.println("Game " + tc + ": incomplete");
				print();
			}
		}
	}
	
	static boolean isFinished() {
		for(int[] pos : targets) {
			int r = pos[0], c = pos[1];
			if(map[r][c] != 'B') return false;
		}
		return true;
	}

	static void move(int direction) {
		
		int nr = position[0] + dr[direction];
		int nc = position[1] + dc[direction];
		
		if(map[nr][nc] == '.' || map[nr][nc] == '+') {
			position[0] = nr;
			position[1] = nc;
		}else {
			if(map[nr][nc] == 'b' ||map[nr][nc] == 'B') {
				//박스 건너편 방향
				int nnr = nr + dr[direction];
				int nnc = nc + dc[direction];
				
				if(nnr <0 || nnr >=R || nnc < 0 || nnc>=C) return;
				
				if(map[nnr][nnc] == '.' ) {
					map[nnr][nnc] = 'b';
					map[nr][nc] = map[nr][nc] == 'b'?'.':'+';
					position[0] = nr;
					position[1] = nc;
				}else if(map[nnr][nnc] == '+') {
					map[nnr][nnc] = 'B';
					map[nr][nc] = map[nr][nc] == 'b'?'.':'+';
					position[0] = nr;
					position[1] = nc;
				}
			}
		}
		
	}
	
	static boolean isOn(int r, int c) {
		for(int[] pos : targets) {
			if(r == pos[0] && c == pos[1]) return true;
		}
		return false;
	}
	
	static int direction(char d) {
		int r = -1;
		switch(d) {
		case 'U': r = 2; break;
		case 'D': r = 0; break;
		case 'L': r = 3; break;
		case 'R': r = 1;break;
		}
		return r;
	}
	static void print() {
		for(int i = 0; i<R; i++) {
			for(int j = 0; j<C; j++) {
				if(i == position[0] && j == position[1]) {
					char w = map[i][j] == '+'?'W':'w';
					System.out.print(w);
				}else {
					System.out.print(map[i][j]);
				}
			}
			System.out.println();
		}
	}
	static void printTest() {
		for(int i = 0; i<R; i++) {
			for(int j = 0; j<C; j++) {
				if(i == position[0] && j == position[1]) {
					char w = map[i][j] == '+'?'W':'w';
					System.out.printf("%2c", w);
				}else {
					System.out.printf("%2c", map[i][j]);
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
