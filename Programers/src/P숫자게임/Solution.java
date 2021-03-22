package P숫자게임;

import java.util.*;

public class Solution {
	public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int range = 2*w+1;
        int i = 1;
        for(int s : stations) {
        	int l = s-w;
        	int r = s+w;
        	if(i<=l-1) {
        		answer += (l-1-i)/(range)+1;
        	}
        	i = r+1;
        }
        if(i<=n) {
        	answer += (n-i)/(range)+1;
        }
        return answer;
    }
	
	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		int n = 16;
		int[] stations = {9};
		int w = 2;
		System.out.println(s.solution(n, stations, w));
	}
}
