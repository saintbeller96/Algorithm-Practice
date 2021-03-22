package P여행경로;

import java.util.*;
import java.util.Map.Entry;

public class Solution {
	private HashMap<String, ArrayList<String>> adjList;
	private boolean flag;
	private String[] answer;
	public String[] solution(String[][] tickets) {
        adjList = new HashMap<String, ArrayList<String>>();
        for(String[] ticket: tickets) {
        	String src = ticket[0];
        	String dest = ticket[1];
        	
        	if(!adjList.containsKey(src)) {
        		adjList.put(src, new ArrayList<>());
        	}
        	adjList.get(src).add(dest);
        }
        
        adjList.forEach((k, v)->{
        	Collections.sort(v);
        });
        
//        for(Entry<String, ArrayList<String>> e : adjList.entrySet()) {
//        	Collections.sort(e.getValue());
//        }
        
        ArrayList<String> list= new ArrayList<String>();
        list.add("ICN");
        dfs(list, "ICN", tickets.length+1);
        
        
        return answer;
    }
	
	void dfs(ArrayList<String> list, String src,  int total) {
		if(flag) return;
		if(list.size() == total) {
			answer = list.toArray(new String[0]);
			flag = true;
			return;
		}
		if(adjList.containsKey(src)) {
			ArrayList<String> adj = adjList.get(src);
			for(int i = 0; i<adj.size(); i++) {
				String dest = adj.get(i);
				if(dest.equals("")) continue;
				adj.set(i, "");
				list.add(dest);
				dfs(list, dest, total);
				adj.set(i, dest);
				list.remove(list.size()-1);
			}
		}
		
	}


	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		String[][] tickets = {{"ICN", "A"}, {"ICN", "B"}, {"B", "ICN"}};
		s.solution(tickets);
		
	}
}
