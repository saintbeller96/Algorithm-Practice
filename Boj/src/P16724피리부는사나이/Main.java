package P16724피리부는사나이;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int R, C;
	static Map<Character, Integer> dir;
	static char[][] map;
	static int[] dr = {-1, 1, 0 ,0};
	static int[] dc = {0, 0, -1 ,1};
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		map = new char[R][C];
		
		dir = new HashMap<>();
		dir.put('U', 0);dir.put('D', 1); dir.put('L', 2);dir.put('R', 3);
		
		for(int i = 0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int[][] visited = new int[R][C];
		int tag = 1;
		int answer = 0;
		for(int i = 0; i<R; i++) {
			for(int j = 0; j<C; j++) {
				if(visited[i][j] == 0) {
					//독립적인 사이클을 찾는 경우만 개수 증가
					if(dfs(i, j, visited, tag++)) answer++;
				}
			}
		}
		System.out.println(answer);
		return;
	}
 	static boolean dfs(int r, int c, int[][] visited, int tag) {
 		if(visited[r][c] == tag) {
 			return true;
 		}else if(visited[r][c] != 0) {
 			return false;
 		}
 		visited[r][c] = tag;
 		int d = dir.get(map[r][c]);
 		return dfs(r + dr[d], c + dc[d], visited, tag);
 	}
}
