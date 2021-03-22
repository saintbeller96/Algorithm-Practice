package P1987알파벳;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static int answer;
	static boolean flag;
	static char[][] map;
	static boolean[] visited;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());

		map = new char[R][C];
		visited = new boolean[26];
		answer = 1;
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		DFS(0,0,1);
		System.out.println(answer);
	}

	static void DFS(int r, int c, int depth) {
		answer = Math.max(answer, depth);
		visited[map[r][c]-'A'] = true;
		if(answer == 26) {
			return;
		}
		if(depth == 26) {
			answer = 26;
			return;
		}
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr >= 0 && nr < R && nc >=0 && nc < C) {
				if(!visited[map[nr][nc]-'A']) {
					visited[map[nr][nc]-'A'] = true;
					DFS(nr, nc, depth+1);
					visited[map[nr][nc]-'A'] = false;
				}
			}
		}
	}

}
