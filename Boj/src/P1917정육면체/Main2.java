package P1917정육면체;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2 {
	private static int[][] map;
	private static int[] dr = {1, 0, -1, 0};
	private static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		map = new int[6][6];
		for(int t = 0 ; t< 3; t++) {
			
			for(int i = 0; i <6; i++) {
				stk = new StringTokenizer(br.readLine());
				for(int j = 0; j<6; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			
			int up = 0;
			a: for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 6; j++) {
					if(map[i][j] == 1) {
						up = i;
						break a;
					}
				}
			}
			int down = 0;
			a: for(int i = 5; i >=0; i--) {
				for(int j = 0; j < 6; j++) {
					if(map[i][j] == 1) {
						down = i;
						break a;
					}
				}
			}
			int left = 0;
			a: for(int j = 0; j < 6; j++) {
				for(int i = 0; i < 6; i++) {
					if(map[i][j] == 1) {
						left = j;
						break a;
					}
				}
			}
			int right = 0;
			a: for(int j = 5; j >=0; j--) {
				for(int i = 0; i < 6; i++) {
					if(map[i][j] == 1) {
						right = j;
						break a;
					}
				}
			}
			
			int[][] cmap = new int[down - up + 1][right - left + 1];
			boolean[][] b = new boolean[down - up + 1][right - left + 1];
			for (int i = 0; i <= down - up; i++) {
				for (int j = 0; j <= right - left; j++) {
					cmap[i][j] = map[up + i][left + j];
				}
			}
			
			int R = down - up+1;
			int C = right - left + 1;

			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					if(cmap[i][j] == 1) {
						boolean f = true;
						for(int d = 0; d<2; d++) {
							int r = i + dr[d];
							int c = j + dc[d];
							if(r >=0 && r < R && c >= 0 && c < C) {
								if(cmap[r][c] != 0) {
									int r2 = i + 2*dr[d];
									int c2 = j + 2*dc[d];
									if(r2 >=0 && r2 < R && c2 >= 0 && c2 < C) {
										if(cmap[r2][c2] == 1) {
											cmap[i][j] = 2;
											cmap[r2][c2] = 2;
											f = false;
										}
									}
									
								}
							}
						}
						if(f) {
							if(R - i -1>=0 && R - i -1< R && C - j -1>= 0 && C - j -1< C) {
								if(cmap[R - i-1][C - j-1] == 1) {
									cmap[i][j] = 2;
									cmap[R - i-1][C - j-1] = 2;
								}
							}
						}
						
					}
				}
			}
			boolean flag = false;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(cmap[i][j] == 1) {
						flag = true;
					}
				}
			}
			if(flag) {
				System.out.println("no");
			}else {
				System.out.println("yes");
			}	
		}

	}
	private static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

}
