package 단지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	private static int[] dr = {1, -1, 0, 0};
	private static int[] dc = {0, 0, 1, -1};
	private static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		ArrayList<Integer> alist = new ArrayList<Integer>();
		
		for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < N; j++) {
            	map[i][j] = str.charAt(j) - '0';
            }
        }
		
		int answer = 0;
		int cnt = 0;
		for(int i = 0 ; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					visited[i][j] = true;
					answer = dfs(map, visited, i, j);	
					cnt++;
					alist.add(answer);
				}
			}
		}
		Collections.sort(alist);
		System.out.println(cnt);
		for(int i : alist) {
			System.out.println(i);
		}
	}

	private static int dfs(int[][] map, boolean[][] visited, int i, int j) {
		int n = 1;
		if(!check(map, visited, i, j)) {
			return 1;
		}
		for(int d = 0; d < 4; d++) {
			int r = i + dr[d];
			int c = j + dc[d];
			if(r < N && r >= 0 && c < N && c>=0) {
				if(map[r][c] == 1 && !visited[r][c]) {
					visited[r][c] = true;
					n = n + dfs(map, visited, r, c);
				}
			}
		}	
		return n;
	}
	private static boolean check(int[][] map, boolean[][] visited, int i, int j) {
		for(int d = 0; d < 4; d++) {
			int r = i + dr[d];
			int c = j + dc[d];
			if(r < N && r >= 0 && c < N && c>=0) {
				if(map[r][c] == 1 && !visited[r][c]) {
					return true;
				}
			}
		}
		return false;
	}
}
