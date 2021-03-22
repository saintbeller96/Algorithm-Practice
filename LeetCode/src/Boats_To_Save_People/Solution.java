package Boats_To_Save_People;

import java.util.*;

public class Solution {
	public int numRescueBoats(int[] people, int limit) {
        int answer = 0;
        int N = people.length;
        Arrays.sort(people);
        int l = 0;
        int r = N-1;
        while(l<r) {
        	int sum = people[l] + people[r];
        	if(sum <= limit) {
        		l++;r--;
        		answer++;
        	}else {
        		r--;
        		answer++;
        	}
        }
        if(l == r) {
        	answer++;
        }
        
        return answer;
    }

	public static void main(String[] args) {
		Solution s = new Solution();
		int[] people = {1, 2, 4, 5};
		int limit = 6;
		System.out.println(s.numRescueBoats(people, limit));
	}

}
