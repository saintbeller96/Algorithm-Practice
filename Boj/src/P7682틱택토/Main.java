package P7682틱택토;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
	static String[] horse = {"O", "X"};
	static HashMap<String, Boolean> result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		result = new HashMap<String, Boolean>();
		String line = "";
		
		play(new StringBuilder("........."), 0, 1);

		while(!(line=br.readLine()).equals("end")) {
			if(result.containsKey(line)) {
				sb.append("valid\n");
			}else {
				sb.append("invalid\n");
			}
		}
		System.out.println(sb);

	}
	
	static void play(StringBuilder map, int depth, int h) {
		if(depth == 9) {
			result.put(map.toString(), true);
			return;
		}
		
		int s = 0;
		while(s + 2 < 9) {
			char c = map.charAt(s);
			if(c != '.' && c == map.charAt(s+1) && c == map.charAt(s+2)) {
				result.put(map.toString(), true);
				return;
			}
			s+=3;
		}
		s = 0;
		while(s + 6 < 9) {
			char c = map.charAt(s);
			if(c != '.' && c == map.charAt(s+3) && c == map.charAt(s+6)) {
				result.put(map.toString(), true);
				return;
			}
			s++;
		}
		//대각선
		if(map.charAt(0)!='.' && map.charAt(0) == map.charAt(4) && map.charAt(0) == map.charAt(8)) {
			result.put(map.toString(), true);
			return;
		}
		if(map.charAt(2)!='.' && map.charAt(2) == map.charAt(4) && map.charAt(2) == map.charAt(6)) {
			result.put(map.toString(), true);
			return;
		}
		
		for(int i = 0; i<9; i++) {
			if(map.charAt(i) != '.') continue;
			map.replace(i, i+1, horse[h]);
			play(map, depth+1, h^1);
			map.replace(i, i+1, ".");
		}
	}
}
