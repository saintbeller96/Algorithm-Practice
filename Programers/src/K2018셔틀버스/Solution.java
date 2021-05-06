package K2018셔틀버스;

import java.util.*;
class Solution {
	public String solution(int n, int t, int m, String[] timetable) {
		int len = timetable.length;
        int[] times = new int[len];
        PriorityQueue<Integer> waitQue = new PriorityQueue<Integer>();
        for(int i = 0; i<len; i++) {
        	waitQue.offer(convertToInt(timetable[i]));
        }
        int busTime = convertToInt("09:00");
        int last = 0;
        for(int k = 0; k<n; k++) {
        	int cnt = 0;
        	int last_boarding_time = 0;
        	while(!waitQue.isEmpty()) {
        		if(waitQue.peek() <= busTime && cnt < m) {
        			cnt++;
        			last_boarding_time = waitQue.poll();
        		}else break;
        	}
        	if(k+1<n) {
        		if(waitQue.isEmpty()) {
        			last = busTime + ((k+1)*t);
        			break;
        		}
        		busTime += t;
        	}else {
        		if(cnt < m) last = busTime;
        		else last = last_boarding_time-1;
        		break;
        	}
        }
        return convertToTime(last);
    }
	private int convertToInt(String time) {
		String[] splits = time.split(":");
		int h = Integer.parseInt(splits[0]);
		int m = Integer.parseInt(splits[1]);
		return h*60+m;
	}
	private String convertToTime(int time) {
		String h = time/60 < 10?"0"+time/60:time/60 + "";
		String m = time%60 < 10?"0"+time%60:time%60 + "";
		return h + ":" + m;
	}
	
	public static void main(String[] args){
		Solution sol = new Solution();
		int n = 2;
		int t = 10;
		int m = 2;
		String[] timetable = {"09:10", "09:09", "08:00"};
		System.out.println(sol.solution(n ,t, m, timetable));
	}

}
