package P22944죽음의비_백트래킹;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
	static int N, H, D, K;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static int answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		H = Integer.parseInt(stk.nextToken());
		D = Integer.parseInt(stk.nextToken());
		char[][] map = new char[N][N];
		Point start = null, end = null;
		List<Point> umbrellas = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'S') {
					start = new Point(i, j);
				} else if (map[i][j] == 'E') {
					end = new Point(i, j);
				} else if (map[i][j] == 'U') {
					umbrellas.add(new Point(i, j));
					K++;
				}
			}
		}
		answer = Integer.MAX_VALUE;
		boolean[] used = new boolean[K];
		dfs(map, used, new Step(start.r, start.c, 0, 0, 0), end, umbrellas);
		System.out.println(answer != Integer.MAX_VALUE ? answer : -1);
		return;
	}

	private static void dfs(char[][] map, boolean[] used, Step step, Point end, List<Point> umbrellas) {
		int remain = Math.abs(step.point.r - end.r) + Math.abs(step.point.c - end.c);
		if (step.umbrella + step.h >= remain) {
			answer = Math.min(answer, step.time + remain);
			return;
		}
		for (int i = 0; i < umbrellas.size(); i++) {
			if(used[i]) continue;
			Point umbPoint = umbrellas.get(i);
			int remainDist = Math.abs(step.point.r - umbPoint.r) + Math.abs(step.point.c - umbPoint.c);
			if (step.h + step.umbrella >= remainDist) {
				used[i] = true;
				int h = step.h;
				if (step.umbrella - remainDist < 0) {
					h -= Math.abs(step.umbrella - remainDist);
				}
				dfs(map, used, new Step(umbPoint.r, umbPoint.c, h, D, step.time + remainDist),end, umbrellas);
				used[i] = false;
			}
		}
	}

	private static class Point {
		int r;
		int c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	private static class Step {
		Point point;
		int h, umbrella, time;
		public Step(int r, int c, int h, int umbrella, int time) {
			this.point = new Point(r, c);
			this.h = h;
			this.umbrella = umbrella;
			this.time = time;
		}
	}
}
