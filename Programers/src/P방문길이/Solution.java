package P방문길이;

import java.util.*;

public class Solution {
	int[] dr = {1, 0, -1, 0};
	int[] dc = {0, 1, 0, -1};
	String format = "%d %d -> %d %d";
	Map<Character, int[]> dir = new HashMap<Character, int[]>() {
		{
			put('U', new int[] {0, 1});
			put('D', new int[] {0,-1});
			put('L', new int[] {-1, 0});
			put('R', new int[] {1, 0});
		}
	};
	public int solution(String dirs) {
        int answer = 0;
        Set<String> visited = new HashSet<>();
        int r = 0;
        int c = 0;
        for(char d : dirs.toCharArray()) {
        	int[] _d = dir.get(d);
        	int nr = r + _d[0];
        	int nc = c + _d[1];
        	if(nr >= -5 && nr <=5 && nc>=-5 && nc<=5) {
        		String f1 = String.format(format, r, c, nr, nc);
        		String f2 = String.format(format, nr, nc, r, c);
        		if(!visited.contains(f1) && !visited.contains(f2)) {
        			visited.add(String.format(format, r, c, nr, nc));
        			visited.add(String.format(format, nr, nc, r, c));
        			answer++;
        		}
        		r = nr;
        		c = nc;
        	}
        }
        return answer;
    }
	
	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		String dirs = "LULLLLLLU";
		System.out.println(s.solution(dirs));
	}
}
