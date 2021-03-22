package K2019길찾기게임;

import java.util.*;

public class Solution {
	static class Node{
		int n, x, y;
		Node left, right;
		public Node(int n, int x, int y) {
			this.n = n;
			this.x = x;
			this.y = y;
		}
	}
	Node[] nodes;
	public int[][] solution(int[][] nodeinfo) {
		nodes = new Node[nodeinfo.length];
        int[][] answer = new int[2][nodeinfo.length];
        for(int i = 0; i<nodeinfo.length; i++) {
        	nodes[i] = new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]);
        }
        Arrays.sort(nodes, (a, b)->Integer.compare(a.x, b.x));
        Arrays.sort(nodes, (a, b)->Integer.compare(b.y, a.y));
        
        Node root = nodes[0];
        for(int i = 1; i<nodes.length; i++) {
        	setChild(root, nodes[i]);
        }
        
        List<Integer> pre = new ArrayList<>();
        List<Integer> post = new ArrayList<>();
        
        preorder(root, pre);
        postorder(root, post);
        
        for(int i = 0; i<nodeinfo.length; i++) {
        	answer[0][i] = pre.get(i);
        	answer[1][i] = post.get(i);
        }
        
        return answer;
    }
	
	private void setChild(Node parent, Node child) {
		if(parent.x < child.x) {
			if(parent.right == null) {
				parent.right = child;
			}else {
				setChild(parent.right, child);
			}
		}else {
			if(parent.left == null) {
				parent.left = child;
			}else {
				setChild(parent.left, child);
			}
		}
	}
	
	private void preorder(Node node, List<Integer> order) {
		if(node == null) return;
		order.add(node.n);
		preorder(node.left, order);
		preorder(node.right, order);
	}
	private void postorder(Node node, List<Integer> order) {
		if(node == null) return;
		postorder(node.left, order);
		postorder(node.right, order);
		order.add(node.n);
	}
	
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[][] nodeinfo = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
		
		System.out.println(sol.solution(nodeinfo));

	}

}
