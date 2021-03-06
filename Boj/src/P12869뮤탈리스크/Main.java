package P12869뮤탈리스크;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static int[] attack = {9, 3, 1};
	private static boolean[][][] visited = new boolean[61][61][61];
	private static int[][] perms = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int[] scv = new int[3];
		for(int i = 0; i<N; i++) {
			scv[i] = Integer.parseInt(stk.nextToken());
		}
		System.out.println(BFS(scv));
	}
	private static int BFS(int[] scv) {
		int answer = 0;
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {scv[0], scv[1], scv[2], 0});
		visited[scv[0]][scv[1]][scv[2]] = true;
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			if(p[0] == 0 && p[1] == 0 && p[2] == 0) {
				answer = p[3];
				break;
			}
			int[] tmp = new int[3];
			for(int[] perm:perms) {
				for(int i = 0; i<3; i++) {
					tmp[i] = (p[perm[i]] - attack[i] > 0) ? p[perm[i]] - attack[i]:0;
				}
				if(!visited[tmp[0]][tmp[1]][tmp[2]]) {
					visited[tmp[0]][tmp[1]][tmp[2]] = true;
					que.offer(new int[] {tmp[0], tmp[1], tmp[2], p[3]+1});
				}
			}
		}
		return answer;
	}
}
