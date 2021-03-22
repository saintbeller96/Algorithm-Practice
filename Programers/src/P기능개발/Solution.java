package P기능개발;

import java.util.*;
import java.util.Map.Entry;

public class Solution {
	
	public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int curW = 0;
        Queue<Integer> que = new LinkedList<>();
        for(int w : truck_weights) {
        	while(true) {
        		if(que.isEmpty()) {
        			que.offer(w);
        			curW += w;
        			answer++;
        			break;
        		}else if(que.size() == bridge_length) {
        			curW -=que.poll();
        		}else {
        			if(curW + w <= weight) {
        				que.offer(w);
        				curW+=w;
        				answer++;
        				break;
        			}else {
        				answer++;
        				que.offer(0);
        			}
        		}
        	}
        }
        
        return answer + bridge_length;
    }
	
	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		int bridge_length = 2;
		int weight = 10;
		int[] truck_weights = {7, 4, 5, 6};
		s.solution(bridge_length, weight, truck_weights);
	}
}
