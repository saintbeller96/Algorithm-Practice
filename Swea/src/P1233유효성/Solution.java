package P1233유효성;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int N;
	private static class Node{
		int lc, rc;
		char op;
		public Node(int l, int r, char v) {
			lc = l;
			rc = r;
			op = v;
		}
	}
	private static Node[] tree;
	private static int answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		StringBuilder sb = null;
		for(int t = 1; t<=10; t++) {
			N = Integer.parseInt(br.readLine());
			tree = new Node[N+1];
			answer = 1;
			for(int i = 1; i<=N; i++) {
				stk = new StringTokenizer(br.readLine());
				if(stk.countTokens() == 4) {
					int n = Integer.parseInt(stk.nextToken());
					char op = stk.nextToken().charAt(0);
					int lc = Integer.parseInt(stk.nextToken());
					int rc = Integer.parseInt(stk.nextToken());
					tree[n] = new Node(lc, rc, op);
				}else {
					int n = Integer.parseInt(stk.nextToken());
					char op = stk.nextToken().charAt(0);
					tree[n] = new Node(-1, -1, op);
					if(op == '*' ||op == '/' ||op == '+' ||op == '-') {
						answer = 0;
					}
				}			
			}		
			//postOrder(1);
			System.out.println("#" + t + " " + answer);
		}

	}
	private static void postOrder(int n) {		
		if(tree[n].lc == -1 && tree[n].rc == -1) {
			if(tree[n].op == '*' ||tree[n].op == '/' ||tree[n].op == '+' ||tree[n].op == '-') {
				answer = 0;
				return;
			}
		}else {
			postOrder(tree[n].lc);
			postOrder(tree[n].rc);
		}
	}

}
