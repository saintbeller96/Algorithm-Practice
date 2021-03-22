package P리틀프렌즈사천성;

import java.util.*;

public class Solution {
	int[] dr = {1, 0, -1, 0};
	int[] dc = {0, 1, 0, -1};
	char[][] map;
	int R, C;
	static class State{
		int r, c;
		int dir = -1;
		boolean flag;
		public State(int r, int c, int d,boolean flag) {
			this.r = r;
			this.c = c;
			this.flag = flag;
			this.dir = d;
		}
		
	}
	public String solution(int m, int n, String[] board) {
        String answer = "";
        R = m;
        C = n;
        map = new char[R][C];
        Map<Character, int[]> position = new HashMap<>();
        Set<Character> tiles = new TreeSet<>();
        for(int i = 0; i<R; i++) {
        	map[i] = board[i].toCharArray();
        	for(int j = 0; j<C; j++) {
        		if(map[i][j] >= 'A' && map[i][j] <= 'Z') {
        			position.put(map[i][j], new int[] {i, j});
        			tiles.add(map[i][j]);
        		}
        	}
        }
    	Set<Character> completed = new HashSet<>();
    	int cnt = 0;
        while(true) {
        	for(char target: tiles) {
        		if(completed.contains(target)) continue;
        		if(bfs(target, position)) {
        			answer += target;
        			completed.add(target);
        			System.out.println(target);
        			print();
        			break;
        		}
        	}
        	if(completed.size() == tiles.size()) break;
        }
        return answer;
    }
	
	void print() {
		for(int i = 0; i<R; i++) {
			for(int j = 0; j<C; j++) {
				System.out.printf("%2c", map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	boolean bfs(char target, Map<Character, int[]> position) {
		
		int[] pos = position.get(target);
		boolean[][] visited = new boolean[R][C];
		Queue<State> que = new LinkedList<>();
		int r = pos[0];
		int c = pos[1];
		que.offer(new State(r, c,-1, false));
		visited[r][c] = true;
		while(!que.isEmpty()) {
			State s = que.poll();
			if((s.r!=r || s.c!=c) && map[s.r][s.c] == target) {
				map[r][c] = map[s.r][s.c] = '.';
				return true;
			}
			if(s.dir == -1){
				for(int d = 0; d<4; d++) {
					int nr = s.r + dr[d];
					int nc = s.c + dc[d];
					if(nr>=0 && nr<R && nc>=0 && nc<C && !visited[nr][nc] && (map[nr][nc] == '.'|| map[nr][nc] == target)) {
						que.offer(new State(nr, nc, d, false));
						visited[nr][nc] = true;
					}
				}
			}else {
				if(s.flag) {
					for(int d = 0; d<4; d++) {
						if(d%2 != s.dir%2) continue;
						int nr = s.r + dr[d];
						int nc = s.c + dc[d];
						if(nr>=0 && nr<R && nc>=0 && nc<C && !visited[nr][nc]&& (map[nr][nc] == '.' || map[nr][nc] == target)) {
							que.offer(new State(nr, nc, d, true));
							visited[nr][nc] = true;
						}
					}
				}else {
					for(int d = 0; d<4; d++) {
						boolean b = d%2 != s.dir%2;
						int nr = s.r + dr[d];
						int nc = s.c + dc[d];
						if(nr>=0 && nr<R && nc>=0 && nc<C && !visited[nr][nc]&& (map[nr][nc] == '.'|| map[nr][nc] == target)) {
							que.offer(new State(nr, nc, d, b));
							visited[nr][nc] = true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		int m = 5;
		int n = 5;
		String[] board = { "FGHEI", "BAB..", "D.C*.", "CA..I", "DFHGE" };
		System.out.println(s.solution(m, n, board));
	}
}
