package P20005보스몬스터전리품;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	static int R, C, P;
	static char[][] map;
	static int[] dps;
	static int bR, bC;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		P = Integer.parseInt(stk.nextToken());
		
		map = new char[R][C];
		for(int i = 0; i<R; i++) {
			String line = br.readLine();
			for(int j = 0; j<C; j++) {
				char c = line.charAt(j);
				if(c == 'B') {
					bR = i;
					bC = j;
				}
				map[i][j] = c;
			}
		}
		dps = new int[26];
		for(int i = 0; i<P; i++) {
			stk = new StringTokenizer(br.readLine());
			int p = stk.nextToken().charAt(0) - 'a';
			int d = Integer.parseInt(stk.nextToken());
			dps[p] = d;
		}
		int hp = Integer.parseInt(br.readLine());
		
		int[] dist = bfs();
		Map<Integer, List<Integer>> order = new TreeMap<>();
		for(int i = 0; i<26; i++) {
			if(dist[i] == 0) continue;
			order.putIfAbsent(dist[i], new ArrayList<>());
			order.get(dist[i]).add(i);
		}
		
		int cur = 0;
		int answer = 0;
		int prevTime = 0;
		for(Entry<Integer, List<Integer>> e : order.entrySet()) {
			if(hp<=0) break;
			int time = e.getKey();
			int cnt = 0;
			int total = 0;
			for(int p : e.getValue()) {
				total += dps[p];
				cnt++;
			}
			
			if(time - prevTime == 1) {
				cur += total;
			}else {
				int c = cur*(time-prevTime-1);
				if(hp - c<=0) break;
				hp -= c;
				cur += total;
			}
			hp -= cur;
			answer += cnt;
			prevTime = time;
		}
		
		System.out.println(answer);
		return;
	}
	
	static int[] bfs() {		
		Queue<int[]> que = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		visited[bR][bC] = true;
		int[] dist = new int[26];
		
		que.offer(new int[] {bR, bC, 0});
		while(!que.isEmpty()) {
			int[] p = que.poll();
			int r = p[0];
			int c = p[1];
			int time = p[2];
			if(map[r][c] != 'B' && map[r][c] != 'X' && map[r][c] != '.') {
				dist[map[r][c]-'a'] = time;
			}
			for(int d = 0; d<4; d++) {
				int nr = r+dr[d];
				int nc = c + dc[d];
				if(nr>=0 && nr<R && nc>=0 && nc<C && !visited[nr][nc] && map[nr][nc] != 'X') {
					visited[nr][nc] = true;
					que.offer(new int[] {nr, nc, time+1});
				}
			}
		}
			
		return dist;
	}
}
