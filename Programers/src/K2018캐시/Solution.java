package K2018캐시;

import java.util.*;

public class Solution {

	public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Deque<String> cache = new ArrayDeque<>();
        for(String city : cities) {
        	city = city.toLowerCase();
        	if(cache.isEmpty() || cacheSize == 0) {
        		answer+=5;
        		cache.addFirst(city);
        		continue;
        	}
        	
        	if(cache.contains(city)) {
        		cache.remove(city);
        		cache.addLast(city);
        		answer+=1;
        	}else {
        		if(cache.size() >= cacheSize) {
        			cache.removeFirst();
            		
        		}
        		cache.addLast(city);
        		answer+=5;
        	}
        }
        
        return answer;
    }
	
	public static void main(String[] args){
		Solution sol = new Solution();
		int cacheSize = 5;
		String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};		
		System.out.println(sol.solution(cacheSize, cities));

	}

}
