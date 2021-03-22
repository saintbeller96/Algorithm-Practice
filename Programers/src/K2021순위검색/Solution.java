package K2021순위검색;

import java.util.*;
import java.util.Map.Entry;


class Solution {
	String[][] items = {{"cpp", "java", "python"},{"frontend", "backend"},{"junior", "senior"},{"pizza", "chicken"}};
	
    public Integer[] solution(String[] info, String[] query) {
        List<Integer> answer = new ArrayList<>();
        Map<String, List<Integer>> map = new HashMap<>();
        for(String str : info) {
        	String[] s = str.split(" ");
        	StringBuilder sb = new StringBuilder();
        	for(int i =0; i<4; i++) {
        		sb.append(s[i]);
        	}
        	int v =Integer.parseInt(s[4]);
        	map.putIfAbsent(sb.toString(), new ArrayList<>());
        	map.get(sb.toString()).add(v);
        }
        for(Entry<String, List<Integer>> e : map.entrySet()) {
        	Collections.sort(e.getValue());
        }
        
        for(String q : query) {
        	q = q.replaceAll(" and", "");
        	String[] s = q.split(" ");
        	int v =Integer.parseInt(s[4]);
        	Set<String> result = new TreeSet<>();
        	parse("", s, 0, result);
        	int cnt = 0;
        	for(String str: result) {
        		List<Integer> list = map.get(str);
        		if(list == null) continue;
        		cnt+=upperbound(list, v); 		
        	}
        	answer.add(cnt);
        }
        
        
        return answer.toArray(new Integer[0]);
    }
    
    private void parse(String current, String[] query, int depth, Set<String> result) {
    	if(depth == query.length-1) {
    		result.add(current);
    		return;
    	}
    	if(query[depth].equals("-")) {
    		for(String s : items[depth]) {
    			parse(current + s, query, depth+1, result);
    		}
    	}else {
    		parse(current + query[depth], query, depth+1, result);
    	}
    }
    
    private int upperbound(List<Integer> list, int num) {   	
    	int s = 0;
    	int e = list.size();
    	while(s < e) {
    		int mid = (s+e)/2;
    		if(list.get(mid) >= num) {
    			e = mid;
    		}else {
    			s = mid+1;
    		}
    	}
    	
    	return list.size() - e;
    }
    
    public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		Integer[] answer = s.solution(info, query);
		System.out.println(answer);
	}
}
