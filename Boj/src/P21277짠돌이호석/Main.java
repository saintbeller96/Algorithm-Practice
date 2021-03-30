package P21277짠돌이호석;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int[][] map;
	static int N1,M1,N2, M2;
	static class Point{
		int r, c;
		public Point(int x, int y) {
			this.r = x;
			this.c = y;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + r;
			result = prime * result + c;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (r != other.r)
				return false;
			if (c != other.c)
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "[r=" + r + ", c=" + c + "]";
		}
		
	}
	static Set<Point> puzzle1;
	static Set<Point> puzzle2;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());	
		
		puzzle1 = new HashSet<>();
		puzzle2 = new HashSet<>();
		
		N1 = Integer.parseInt(stk.nextToken());
		M1 = Integer.parseInt(stk.nextToken());
		
		int maxR = -1000, maxC = -1000;
		int minR = 1000, minC = 1000;
		
		for(int i=0; i<N1; i++) {
			String line = br.readLine();
			for(int j = 0; j<M1; j++) {
				if(line.charAt(j) == '1') {
					puzzle1.add(new Point(i, j));
				}
			}
		}
		
		stk = new StringTokenizer(br.readLine());	
		N2 = Integer.parseInt(stk.nextToken());
		M2 = Integer.parseInt(stk.nextToken());
		
		for(int i=0; i<N2; i++) {
			String line = br.readLine();
			for(int j = 0; j<M2; j++) {
				if(line.charAt(j) == '1') {
					puzzle2.add(new Point(i, j));
					maxR = Math.max(maxR, i);
					minR = Math.min(minR, i);
					maxC = Math.max(maxC, j);
					minC = Math.min(minC, j);
				}
			}
		}
		int answer = Integer.MAX_VALUE;
		
		int R = Math.max(N1, N2);
		int C = Math.max(M1, M2);
		
		for(int k = 0; k<4; k++) {
			for(int i = -R; i<=R; i++) {
				for(int j = -C; j<=C; j++) {
					int maxR2 = maxR;
					int minR2 = minR;
					int maxC2 = maxC;
					int minC2 = minC;
					boolean flag = true;
					for(Point p : puzzle1) {
						int r = p.r + i;
						int c = p.c + j;
						Point Np = new Point(r, c);
						if(puzzle2.contains(Np)) {
							flag = false;
							break;
						}
						maxR2 = Math.max(maxR2, r);
						minR2 = Math.min(minR2, r);
						maxC2 = Math.max(maxC2, c);
						minC2 = Math.min(minC2, c);	
					}
					if(flag) {
						answer = Math.min(answer, (maxR2-minR2+1)*(maxC2-minC2+1));
					}
				}
			}
			rotate();
		}
		
		System.out.println(answer);
		return;
	}
	
	static void rotate() {
		Set<Point> newPuzzle = new HashSet<>();
		int maxR = 0;
		for(Point p: puzzle1) {
			maxR = Math.max(maxR, p.r);
		}
		for(Point p: puzzle1) {
			newPuzzle.add(new Point(p.c, maxR-p.r));
		}
		puzzle1 = newPuzzle;
	}
}
