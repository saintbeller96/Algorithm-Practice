package P17471게리맨더링;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int N;
	static int[] sectors;
	static int[] comb;
	static HashMap<Integer, ArrayList<Integer>> adjList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		adjList = new HashMap<Integer, ArrayList<Integer>>();
		N = Integer.parseInt(br.readLine());
		sectors = new int[N+1];
		comb = new int[N];
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int total = 0;
		for(int i = 1; i<=N; i++) {
			sectors[i] = Integer.parseInt(stk.nextToken());
			total += sectors[i];
			comb[i-1] = i;
		}
		
		for(int i = 1; i<=N; i++) {
			if(!adjList.containsKey(i)) {
				adjList.put(i, new ArrayList<Integer>());
			}
			stk = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(stk.nextToken());
			for(int j = 0; j < n; j++) {
				adjList.get(i).add(Integer.parseInt(stk.nextToken()));
			}
		}
		int min = Integer.MAX_VALUE;
		for(int c = 1; c<=N/2; c++) {
			ArrayList<TreeSet<Integer>> combs = comb(new ArrayList<TreeSet<Integer>>(), new int[c], c, 0, 0);
			for(TreeSet<Integer> ts : combs) {
				int ret = compare(ts, total);
				if(ret != -1)
					min = Math.min(min, ret);
			}
		}
		if(min == Integer.MAX_VALUE) min = -1;
		System.out.println(min);
	}
	static int compare(TreeSet<Integer> sector1, int total) {
		TreeSet<Integer> sector2 = new TreeSet<Integer>();
		for(int i = 1; i<=N; i++) {
			if(!sector1.contains(i)) {
				sector2.add(i);
			}
		}
		
		boolean[] visited = new boolean[N+1];
		int cnt1 = dfs(sector1, visited, sector1.first());
		int cnt2 = dfs(sector2, visited, sector2.first());
		
		if(cnt1 == sector1.size() && cnt2 == sector2.size()) {
			int sum = 0;
			for(int i : sector1) {
				sum+=sectors[i];
			}
			return Math.abs(sum - (total - sum));
		}
		return -1;
	}
	
	static int dfs(TreeSet<Integer> sector, boolean[] visited, int v) {
		int cnt = 1;
		visited[v] = true;
		if(adjList.containsKey(v)) {
			for(int u : adjList.get(v)) {
				if(sector.contains(u) && !visited[u]) {
					cnt += dfs(sector, visited, u);
				}
			}
		}
		return cnt;
	}
	
	static ArrayList<TreeSet<Integer>> comb(ArrayList<TreeSet<Integer>> list, int[] num, int R,  int start, int depth) {
		if(depth == R) {
			TreeSet<Integer> tmp = new TreeSet<Integer>();
			for(int c : num) {
				tmp.add(c);
			}
			list.add(tmp);
			return null;
		}
		for(int i = start; i<N; i++) {
			num[depth] = comb[i];
			comb(list, num, R, i+1, depth+1);
		}
		return list;
	}
}