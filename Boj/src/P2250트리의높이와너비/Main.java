package P2250트리의높이와너비;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static class Node{
		int parent, left, right;
		int order;
		@Override
		public String toString() {
			return "Node [parent=" + parent + ", left=" + left + ", right=" + right + ", " + this.order +"]";
		}
	}
	static int N;
	static Node[] nodes;
	static int order = 1;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		N = Integer.parseInt(br.readLine());
		nodes = new Node[N+1];
		for(int i = 1; i<=N; i++) {
			nodes[i] = new Node();
		}
		for(int i = 0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(stk.nextToken());
			int l = Integer.parseInt(stk.nextToken());
			int r = Integer.parseInt(stk.nextToken());
			nodes[n].left = l;
			if(l != -1) nodes[l].parent = n;
			nodes[n].right = r;
			if(r != -1) nodes[r].parent = n;
		}
		int root = 0;
		for(int i = 1; i<=N; i++) {
			if(nodes[i].parent == 0) {
				root = i;
				break;
			}
		}
		inorder(root);
		
		Queue<Integer> que = new LinkedList<>();
		que.offer(root);
		int level = 1;
		int maxWidth = 0;
		int maxLevel = 0;
		while(!que.isEmpty()) {
			int len = que.size();
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for(int i = 0; i<len; i++) {
				int node = que.poll();
				max = Math.max(max, nodes[node].order);
				min = Math.min(min, nodes[node].order);

				int left = nodes[node].left;
				int right = nodes[node].right;
				if(left != -1) que.offer(left);
				if(right != -1) que.offer(right);
			}
			if(maxWidth < max - min +1) {
				maxWidth = max - min+1;
				maxLevel = level;
			}
			level++;
		}
		
		System.out.println(maxLevel + " " + maxWidth);
 	}
 	static void inorder(int node) {
 		if(node == -1) return;
 		
 		inorder(nodes[node].left);
 		nodes[node].order = order++;
 		inorder(nodes[node].right);
 		
 	}
}
