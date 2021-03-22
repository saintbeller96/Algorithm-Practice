package P8972미친아두이노;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Main {
	static class Point{
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			Point other = (Point) obj;
			if (c != other.c) return false;
			if (r != other.r) return false;
			return true;
		}
	}
	static int R, C;
	static char[][] map;
	static ArrayList<int[]> posAdu;
	static int[] dr = {0,  1,  1,  1,  0, 0, 0, -1,-1,-1};
	static int[] dc = {0, -1,  0,  1, -1, 0, 1, -1, 0, 1};
	static int jr, jc;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		map = new char[R][C];
		posAdu = new ArrayList<int[]>();
		jr = -1;
		jc = -1;
		for(int i = 0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0; j<C; j++) {
				char c = map[i][j];
				if(c == 'I') {
					jr = i;jc=j;
				}else if(c == 'R') {
					posAdu.add(new int[] {i, j});
				}
			}
		}
		char[] order = br.readLine().toCharArray();
		for(int i = 0; i<order.length; i++) {
			int op = order[i]-'0';
			if(!go(op)) {
				System.out.println("kraj "+ (i+1));
				return;
			}
		}
		print();
		
	}
	
	static boolean go(int op) {
		map[jr][jc] = '.';
		jr += dr[op];
		jc += dc[op];
		for(int[] pos : posAdu) {
			int r = pos[0];
			int c = pos[1];
			if(r == jr && c == jc) {
				return false;
			}
		}
		
		map[jr][jc] = 'I';
		HashMap<Point, Integer> removeMap = new HashMap<>();
		for(int[] pos : posAdu) {
			int r = pos[0];
			int c = pos[1];
			int d = findShortestDirection(r, c);
			pos[0]+=dr[d];
			pos[1]+=dc[d];
			if(pos[0] == jr && pos[1] == jc) {
				return false;
			}
			map[r][c] = '.';
			Point p  = new Point(pos[0], pos[1]);
			if(!removeMap.containsKey(p)) {
				removeMap.put(p, 0);
			}
			removeMap.put(p, removeMap.get(p)+1);
		}
		
		for(Entry<Point, Integer> e : removeMap.entrySet()) {
			Point p = e.getKey();
			if(e.getValue() > 1) {
				Iterator<int[]> iter = posAdu.iterator();
				while(iter.hasNext()) {
					int[] pos = iter.next();
					if(pos[0] == p.r && pos[1] == p.c) {
						iter.remove();
					}
				}
			}else {
				map[p.r][p.c] = 'R';
			}
		}
		return true;
	}
	
	static int findShortestDirection(int r, int c) {
		int md = -1;
		int minDist = Integer.MAX_VALUE;
		for(int d = 1; d<=9; d++) {
			if(d == 5) continue;
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr>=0 && nr<R && nc>=0 && nc<C) {
				int dist = Math.abs(jr-nr) + Math.abs(jc-nc);
				if(dist<=minDist) {
					minDist = dist;
					md = d;
				}
			}
		}
		return md;
	}
	
	static void print() {
		for (int i = 0; i < R; i++) {
			for(int j = 0; j<C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

}
