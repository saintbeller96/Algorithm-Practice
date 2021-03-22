package P5648원자소멸;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N, M, K, Answer;
	private static int[] dx = {0, 0,-1,1}; //위, 아래, 좌, 우
    private static int[] dy = {1,-1,0,0};
	private static class Atom{
		int x, y, direction, energy;
		public Atom(int x, int y, int direction, int energy) {
			this.x = x;
			this.y= y;
			this.direction = direction;
			this.energy = energy;
		}
	}
	private static class Point{
    	int x, y;
    	public Point(int i, int j) {
    		this.x = i;
    		this.y = j;
    	}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
    }
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			ArrayList<Atom> atoms = new ArrayList<Atom>();
			for(int i = 0; i<N; i++) {
				stk = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(stk.nextToken());
				int c = Integer.parseInt(stk.nextToken());
				int d = Integer.parseInt(stk.nextToken());
				int e = Integer.parseInt(stk.nextToken());
				
				Atom atom = new Atom(r, c, d, e);
				atoms.add(atom);		
			}
			Answer = 0;
			simulation(atoms);
			
			System.out.println("#" + t + " " + Answer);
		}

	}
	public static void simulation(ArrayList<Atom> atoms) {		
		while(true) {
			HashMap<Point, ArrayList<Atom>> hm = new HashMap<Point, ArrayList<Atom>>();
			
			for(Atom atom : atoms) {				
				int r = atom.x + dx[atom.direction];
				int c = atom.y + dy[atom.direction];			
				atom.x = r;
				atom.y = c;			
				Point p = new Point(r, c);
				if(!hm.containsKey(p)) {
					hm.put(p, new ArrayList<Atom>());
				}
				hm.get(p).add(atom);
			}
			
			for(Entry<Point, ArrayList<Atom>> e : hm.entrySet()) {
				
				for(Atom atom : e.getValue()) {
					Answer += atom.energy;
					atoms.remove(atom);
				}
			}
		}
		
	}

}
