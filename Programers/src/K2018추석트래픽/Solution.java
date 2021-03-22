package K2018추석트래픽;

import java.sql.Timestamp;
import java.time.*;
import java.util.*;

public class Solution {
	public int solution(String[] lines){
        int answer = 0;
        int N = lines.length;
        
        long[][] stamps = new long[N][2];
        for(int i = 0; i<N; i++) {
        	String line = lines[i];
        	String[] s = line.split(" ");
        	
        	long end = Timestamp.valueOf(s[0] + " " + s[1]).getTime();
        	long t = (long)(1000L*Double.parseDouble(s[2].substring(0, s[2].length()-1)));
        	long start = end -t +1;
        	stamps[i][0] = start;
        	stamps[i][1] = end;
        }
        
        for(int i = 0; i<N; i++) {
        	long start = stamps[i][0];
        	long end = stamps[i][1];
        	
        	int cnt = 0;
        	int cnt2 = 0;
        	for(int j = i; j<N; j++) {
        		long s = stamps[j][0];
        		long e = stamps[j][1];
        		
        		if(end + 1000 > s) cnt++;
        		if(start-1000 > e) cnt2++;
        	}
        	answer = Math.max(answer, Math.max(cnt, cnt2));
        }
        
        return answer;
    }
	
	
	public static void main(String[] args){
		Solution sol = new Solution();
		String[] lines = {"2016-09-15 20:59:57.421 0.351s",
				"2016-09-15 20:59:58.233 1.181s",
				"2016-09-15 20:59:58.299 0.8s",
				"2016-09-15 20:59:58.688 1.041s",
				"2016-09-15 20:59:59.591 1.412s",
				"2016-09-15 21:00:00.464 1.466s",
				"2016-09-15 21:00:00.741 1.581s",
				"2016-09-15 21:00:00.748 2.31s",
				"2016-09-15 21:00:00.966 0.381s",
				"2016-09-15 21:00:02.066 2.62s"};
		
		System.out.println(sol.solution(lines));

	}

}
