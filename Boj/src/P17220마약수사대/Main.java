package P17220마약수사대;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static class Node{
		int n;
		boolean isRoot;
		ArrayList<Integer> children;
		public Node(int n) {
			this.n = n;
			this.isRoot = true;
			children = new ArrayList<Integer>();
		}
	}
	static Node[] nodes;
	static int answer = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		
		nodes = new Node[26];
		for(int i = 0; i<M; i++) {
			stk = new StringTokenizer(br.readLine());
			int s = stk.nextToken().charAt(0) -'A';
			int d = stk.nextToken().charAt(0) -'A';
			if(nodes[s] == null) {
				nodes[s] = new Node(s);
			}
			if(nodes[d] == null) {
				nodes[d] = new Node(d);
				nodes[d].isRoot = false;
			}
			nodes[s].children.add(d);
		}
		
		//�˰��ִ� ���� ����å ����
		stk = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(stk.nextToken());
		for(int i = 0; i<R; i++) {
			int r = stk.nextToken().charAt(0) -'A';
			nodes[r] = null;
		}
		
		ArrayList<Node> roots = new ArrayList<Node>();
		for(int i = 0; i<26; i++) {
			if(nodes[i] != null && nodes[i].isRoot) roots.add(nodes[i]);
		}
		
		boolean[] visited= new boolean[26];
		answer = 0;
		for(Node rnode : roots) {
			dfs(visited, rnode, 0);
		}
		
		System.out.println(answer);
		
	}
	static void dfs(boolean[] visited, Node node, int depth) {
		if(visited[node.n]) {
			return;
		}
		if(node.children.size() == 0) {
			return;
		}
		
		visited[node.n] = true;
		
		for(int i = 0; i<node.children.size(); i++) {
			int nChild = node.children.get(i);
			if(nodes[nChild] != null) {
				answer++;
				dfs(visited, nodes[nChild], depth+1);
			}
		}
	}
}
