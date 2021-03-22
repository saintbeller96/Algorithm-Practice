package P점프와순간이동;

import java.util.*;

public class Solution {
	public int solution(int n) {
        int ans = 0;
        for(int i = 0; i<Integer.toBinaryString(n).length(); i++) {
        	if((n & (1<<i)) != 0) {
        		ans++;
        	}
        }
        return ans;
    }

	
	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		int n = 5;
		System.out.println(s.solution(n));
	}
}
