package P3865학회원;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	static int T;
	static Map<String, Set<String>> adjList;
	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		int n = 0;
		while((n = Integer.parseInt(br.readLine()))!=0){
			Map<String, Set<String>> initMap = new TreeMap<>();
			Set<String> academies = new TreeSet<>();
			
			adjList = new TreeMap<>();
			
			String academy = "";
			for(int i = 0; i<n; i++) {
				String[] str = br.readLine().split(":");
				if(i == 0) academy = str[0];
				if(!initMap.containsKey(str[0])) {
					initMap.put(str[0], new HashSet<>());
				}
				academies.add(str[0]);
				for(String s : str[1].substring(0, str[1].length()-1).split(",")) {
					initMap.get(str[0]).add(s);
				}
			}
			
			Map<String, Set<String>> graph = new TreeMap<>();
			
			for(Entry<String, Set<String>> e : initMap.entrySet()) {
				String ac = e.getKey();
				adjList.put(ac, new TreeSet<>());
				graph.put(ac, new TreeSet<>());
				for(String element : e.getValue()) {
					//학회인 경우
					if(academies.contains(element)) {
						adjList.get(ac).add(element);
					}else {
						graph.get(ac).add(element);
					}
				}
			}
			Set<String> visited = new TreeSet<>();
			Set<String> members = new TreeSet<>();
			dfs(members, visited, graph, academy);
			System.out.println(members.size());
		}
	}
	
	static void dfs(Set<String> members, Set<String> visited, Map<String, Set<String>> graph, String cur) {
		if(visited.contains(cur)) return;
		visited.add(cur);
		for(String s : graph.get(cur)) {
			members.add(s);
		}

		if(adjList.get(cur) != null) {
			for(String s : adjList.get(cur)) {
				dfs(members, visited, graph, s);
			}
		}
	}
}
