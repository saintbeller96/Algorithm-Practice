package P1868;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
	private static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	private static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };
	private static int T, N, Answer;
	private static char[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N+2][N+2];
			ArrayList<int[]> zeros = new ArrayList<int[]>();
			for (int i = 1; i <= N; i++) {
				String str = br.readLine();
				for(int j = 0; j < N; j++) {
					map[i][j+1] = str.charAt(j);
				}
			}
			Answer = 0;
			//0의 위치 찾기
			for (int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					int n = checkArea(i, j);
					if(n == 0) {
						zeros.add(new int[] {i, j});
					}
				}
			}
			
			for(int[] pos : zeros) {
				int i = pos[0];
				int j = pos[1];
				if(map[i][j] == '.') {
					draw(i, j);
					Answer++;
				}
			}		
			
			for (int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(map[i][j] == '.') {
						Answer++;
					}
				}
			}
						
			System.out.println("#" + t + " " + Answer);
		}

	}
	
	private static int checkArea(int i, int j) {
		int num = 0;
		for(int d = 0; d<8;d++) {
			int r = i + dr[d];
			int c = j + dc[d];
			if(map[r][c] == '*') {
				num++;
			}
		}
		return num;
	}
	
	private static void draw(int i, int j) {
		int num = checkArea(i, j);
		if(num == 0) {
			map[i][j] = '0';
			for(int d = 0; d<8;d++) {
				int r = i + dr[d];
				int c = j + dc[d];
				if(map[r][c] == '.') {
					draw(r, c);
				}
			}
		}else {
			map[i][j] = (char)(num + '0');
			return;
		}	
	}
}
