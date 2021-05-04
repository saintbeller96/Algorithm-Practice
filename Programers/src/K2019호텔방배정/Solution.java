package K2019호텔방배정;

import java.util.*;

public class Solution {
	public long[] solution(long k, long[] room_number) {
        int people = room_number.length;
        long[] answer = new long[people];
        Map<Long, Long> map = new HashMap<>();
        
        int idx = 0;
        for(long room : room_number) {
        	if(!map.containsKey(room)) {
        		answer[idx++] = room;
        		map.put(room, room+1);
        	}else {
        		List<Long> list = new ArrayList<>();
        		while(true) {
        			list.add(room);
        			if(map.get(room) == null) {
        				break;
        			}
        			room = map.get(room);
        		}
        		for(long r : list) {
        			map.put(r, room+1);
        		}
        		answer[idx++] = room;
        	}
        }
        return answer;
    }
	
	
	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		long k = 10;
		long[] room_number = {1,3,4,1,3,1};
		System.out.println(Arrays.toString(s.solution(k, room_number)));
	}
}
