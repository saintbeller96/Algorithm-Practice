package P3109빵집;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static char[][] map;
	static boolean[][] visit;
	static int[] dr = {-1, 0, 1};
	static int[] dc = {1, 1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		
		map = new char[R][C];
		visit = new boolean[R][C];
		for(int i = 0 ; i<R; i++) {
			String str = br.readLine();
			for(int j = 0 ; j<C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		int cnt = 0;
		for(int r = 0; r<R; r++) {
			if(linkPipe(r, 0)) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
	
	static boolean linkPipe(int r, int c) {
		boolean flag = false;
		if(c == C-1) {
			return true;
		}
		for(int d = 0; d<3 ; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr >=0 && nr < R && nc >=0 && nc < C && map[nr][nc] == '.') {
				if(!visit[nr][nc]) {
					visit[nr][nc] = true;
					flag = linkPipe(nr, nc);
					if(flag) {
						break;
					}
				}
			}
		}
		return flag;
	}
}
