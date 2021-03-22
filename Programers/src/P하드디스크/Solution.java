package P하드디스크;

import java.util.*;
import java.util.Map.Entry;

public class Solution {
	public int solution(int[][] jobs) {
        int answer = 0;
        
        PriorityQueue<int[]> readyQue = new PriorityQueue<>((a, b)->Integer.compare(a[1],b[1]));
        Arrays.sort(jobs, (a, b)->Integer.compare(a[1],b[1]));
        Arrays.sort(jobs, (a, b)->Integer.compare(a[0], b[0]));
        Queue<int[]> que = new LinkedList<>();
        
        int time = jobs[0][0];
        int idx = 0;
        
        readyQue.offer(jobs[idx++]);
        while(!readyQue.isEmpty()) {
        	int[] job = readyQue.poll();
        	int s = job[0];
        	int e = job[1];
        	answer+= e -s + time;
        	time += e;
        	while(idx<jobs.length && jobs[idx][0] <= time) {
        		readyQue.offer(jobs[idx++]);
        	}
        	if(readyQue.isEmpty() && idx<jobs.length && time < jobs[idx][0]) {
        		readyQue.offer(jobs[idx]);
        		time = jobs[idx++][0];
        	}
        }
        
        return answer/jobs.length;
    }

	
	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		int[][] jobs = {{0,3}, {1, 3}, {9, 2}};
		System.out.println(s.solution(jobs));
	}
}
