package P20166문자열지옥에빠진호석;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int R, C, K;
	static char[][] map;
	static List<String> set = new ArrayList<>();
	static Map<String, Integer> visited = new HashMap<>();
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1,-1};
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		map = new char[R][C];
		for(int i = 0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		StringBuilder answer = new StringBuilder();
		for(int k = 0; k<K; k++) {
			String word = br.readLine();
			int cnt = visited.getOrDefault(word, 0);
			if(cnt == 0) {
				for(int i = 0; i<R; i++) {
					for(int j = 0; j<C; j++) {
						if(map[i][j] == word.charAt(0)) {
							cnt += search(word, i,j,0);
						}
					}
				}
				visited.putIfAbsent(word, cnt);
			}
			answer.append(cnt).append('\n');
		}
		System.out.println(answer);
	}
 	static int search(String str, int r, int c, int depth) {
 		if(depth == str.length()-1) {
 			return 1;
 		}
 		int ret = 0;
 		for(int d = 0; d<8; d++) {
 			int nr = (r+dr[d]+R)%R;
 			int nc = (c+dc[d]+C)%C;
 			if(map[nr][nc] == str.charAt(depth+1)) {
 				ret += search(str, nr, nc, depth+1);
 			}
 		}
 		return ret;
 	}
}
