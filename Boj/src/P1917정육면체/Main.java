package P1917정육면체;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	private static class Square{
		int[] pos;
		public Square() {
			pos = new int[4];
		}	
	}
	private static int[][] map;
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, 1, -1};
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
			ArrayList<Square> list = new ArrayList<Square>();
			for(int i = 0; i <6; i++) {
				for(int j = 0; j<6; j++) {
					if(map[i][j] == 1) {
						Square s = new Square();
						for(int d = 0; d<4; d++) {
							int r = i + dr[d];
							int c = j + dc[d];
							if(r >=0 && r < 6 && c >= 0 && c < 6) {
								if(map[r][c] == 0) {								
									s.pos[d] = 1;
								}
							}
						}
						list.add(s);
					}
				}
			}
			
			
			
			
		}

	}

}
