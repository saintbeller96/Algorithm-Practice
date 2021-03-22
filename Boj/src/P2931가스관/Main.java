package P2931가스관;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static char[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc= {0, 0, -1, 1};
	static char[] pipes = {'|', '-', '1', '2', '3', '4', '+'};
	static int R, C;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		
		int curR = -1;
		int curC = -1;
		map = new char[R][];
		
		for(int i = 0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0; j<C; j++) {
				if(map[i][j] == 'M') {
					curR = i;
					curC = j;
				}
			}
		}

		int dir = -1;
		
		for(int d = 0; d<4; d++) {
			int r = curR + dr[d];
			int c = curC + dc[d];
			if(r >=0 && r< R && c>=0 && c<C && map[r][c] != '.' && map[r][c] != 'Z') {
				curR = r;
				curC = c;
				dir = d;
				break;
			}
		}
		
		while(map[curR][curC] != '.') {
			dir = flow(map[curR][curC], dir);
			curR += dr[dir];
			curC += dc[dir];
		}
		
		int answerR = curR+1;
		int answerC = curC+1;
		
		char ap = '0';
		for(int p = 0; p<7; p++) {
			
			if(canFlow(pipes[p], dir)) {
				int d = flow(pipes[p], dir);
				int r = curR + dr[d];
				int c = curC + dc[d];
				
				if(r >=0 && r < R && c >=0 && c<C) {
					if(canFlow(map[r][c], d)) {
						
						int cnt = 0;
						for(int dd = 0; dd<4; dd++) {
							int nr = curR + dr[dd];
							int nc = curC + dc[dd];
							if(nr >=0 && nr < R && nc >=0 && nc<C && canFlow(map[nr][nc], dd)) cnt++;
						}
						if(cnt == 2 || (cnt == 4 && pipes[p] == '+')) {
							System.out.println(answerR + " " + answerC + " " + pipes[p]);
							return;
						}
					}else if(map[r][c] == 'Z'){
						ap = pipes[p];
					}
				}
			}
		}
		System.out.println(answerR + " " + answerC + " " + ap);
	}
	
	static int flow(char pipe, int dir) {
		switch(pipe) {
		case '|':
		case '-':
		case '+':
			break;
		case '1':
			if(dir == 0) dir = 3;
			else if(dir == 2) dir = 1;
			break;
		case '2':
			if(dir == 1) dir = 3;
			else if(dir == 2) dir = 0;
			break;
		case '3':
			if(dir == 3) dir = 0;
			else if(dir == 1) dir = 2;
			break;
		case '4':
			if(dir == 3)  dir = 1;
			else if(dir == 0) dir = 2;
			break;
		}
		return dir;
	}
	static boolean canFlow(char pipe, int dir) {
		switch(pipe) {
		case '|':
			if(dir == 0 || dir == 1) return true;
			break;
		case '-':
			if(dir == 2|| dir == 3) return true;
			break;
		case '+':
			return true;
		case '1':
			if(dir == 2 || dir == 0) return true;
			break;
		case '2':
			if(dir == 1 || dir == 2) return true;
			break;
		case '3':
			if(dir == 3 || dir == 1) return true;
			break;
		case '4':
			if(dir == 0 || dir == 3) return true;
			break;
		}
		return false;
	}
}
