package K2018방금그곡;

import java.util.*;

public class Solution {
	static class Result{
		int playTime;
		String title;
		public Result(int playTime, String title) {
			this.playTime = playTime;
			this.title = title;
		}
	}
	public String solution(String m, String[] musicinfos) {
        m = replace(m);
        List<Result> result = new ArrayList<>();
        for(String info : musicinfos) {
        	String[] splits = info.split(",");
        	int playTime = getPlayTime(splits[0], splits[1]);
        	String title = splits[2];
        	String melody = replace(splits[3]);
        	
        	int len = melody.length();
        	if(len < playTime) {
        		for(int i = len; i<playTime; i++) {
        			melody += melody.charAt(i-len);
        		}
        	}else {
        		melody = melody.substring(0, playTime);
        	}
        	////
        	if(melody.contains(m)) {
        		result.add(new Result(playTime, title));
        	}        	
        }
        Collections.sort(result, (a, b)->Integer.compare(b.playTime, a.playTime));
        
        if(result.size() ==0 ) {
        	return "(None)";
        }
        
        return result.get(0).title;
    }
	
	public int getPlayTime(String start, String end) {
		int s = toInteger(start);
		int e = toInteger(end);
		return e-s;
	}
	
	public int toInteger(String s) {
		String[] splits = s.split(":");
		int h = Integer.parseInt(splits[0]);
		int m = Integer.parseInt(splits[1]);
		return h*60+m;
	}
	
	public String replace(String s) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<s.length(); i++) {
			char c = s.charAt(i);
			if(i+1<s.length() && s.charAt(i+1) == '#') {
				c = Character.toLowerCase(c);
				i++;
			}
			sb.append(c);
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		Solution sol = new Solution();
		String m = "ABCDEFG";
		String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
		System.out.println(sol.solution(m, musicinfos));
	}
}
