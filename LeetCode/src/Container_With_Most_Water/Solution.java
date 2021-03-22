package Container_With_Most_Water;

import java.util.*;

public class Solution {
	
	public int maxArea(int[] height) {
        int l = 0;
        int r = height.length-1;
        int answer = 0;
        while(l<r) {
        	int amount = Math.min(height[l], height[r])*(r-l);
        	answer = Math.max(answer, amount);
        	if(height[l] > height[r]) r--;
        	else l++;
        }
        
        return answer;
    }

	public static void main(String[] args) {
		Solution s = new Solution();
		int[] height = {1,8,6,2,5,4,8,3,7};
		System.out.println(s.maxArea(height));
	}

}
