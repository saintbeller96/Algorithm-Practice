package P풍선터뜨리기;

import java.util.*;
import java.util.Map.Entry;

public class Solution {
	public int solution(int[] a) {
        int answer = 0;
        int N = a.length;
        
        int[][] check = new int[N][2];
        
        int left = Integer.MAX_VALUE;
        for(int i = 0; i<N; i++) {
        	left = Math.min(left, a[i]);
        	check[i][0] = left;
        }
        int right = Integer.MAX_VALUE;
        for(int i = N-1; i>=0; i--) {
        	right = Math.min(right, a[i]);
        	check[i][1] = right;
        }
        
        for(int i = 0; i<N; i++) {
        	if(check[i][0] >= a[i] || check[i][1] >= a[i]) answer++;
        }
        
        return answer;
    }

	
	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		int[] a= {-16,27,65,-2,58,-92,-71,-68,-61,-33};
		System.out.println(s.solution(a));
	}
}
