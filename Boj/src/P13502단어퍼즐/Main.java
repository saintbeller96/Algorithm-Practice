package P13502단어퍼즐;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static char[][] map;
	static int[] dr = {1, 1, 1, 0, 0, -1, -1, -1};
	static int[] dc = {1, 0 ,-1, 1, -1, 1, 0, -1};
	static String[] words = {
			//dict.txt �ܾ��
	};
	static class Trie{
		Node root;
		Trie(){
			root = new Node('0');
		}
		void insert(String word) {
			//��Ʈ���� Ž��
			Node curNode = root;
			for(char c : word.toCharArray()) {
				//���� ����� �ڽĿ� ���� �ܾ��� ���ڰ� ������ ����
				if(!curNode.childrens.containsKey(c)) {
					curNode.childrens.put(c, new Node(c));
				}
				//�ش� �ڽ� ���� �̵�
				curNode = curNode.childrens.get(c);
			}
			//�ܾ��� �� ǥ��
			curNode.eow = true;
		}
		boolean find(String word) {
			Node curNode = root;
			for(char c : word.toCharArray()) {
				//���� ����� �ڽĵ��� �ܾ��� ���ڰ� ������ �� Ʈ���̿��� �ش� �ܾ ����
				if(!curNode.childrens.containsKey(c)) {
					return false;
				}
				curNode = curNode.childrens.get(c);
			}
			//��� �ܾ��� ���ڰ� Ʈ���̿� �ְ� ������ ��忡 �ܾ��� ���� ǥ�õǾ��ٸ� ���� Ʈ���̿� �ش� �ܾ ����
			if(curNode.eow) return true;
			return false;
		}
	}
	static class Node{
		char word;
		boolean eow;
		HashMap<Character, Node> childrens;
		Node(char c){
			word = c;
			childrens = new HashMap<Character, Node>();
			eow = false;
		}
	}

	static boolean[][] visited;
	static int answer;
	static boolean flag;
	static void readDict(Trie trie) throws Exception {
		for(String data : words) trie.insert(data);
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[5][5];
		
		Trie trie = new Trie();
		readDict(trie);
		
		for(int i = 0; i<5; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			for(int j = 0; j <5; j++) {
				map[i][j] = stk.nextToken().charAt(0);
			}
		}
		
		answer = 0;
		
		visited = new boolean[5][5];
		for(String word : words) {
			flag = false;
			loop: for(int i = 0; i<5; i++) {
				for(int j = 0; j <5; j++) {
					char c = map[i][j];
					if(trie.root.childrens.containsKey(c)&& word.charAt(0) == c) {
						dfs(trie.root.childrens.get(c), word, i, j, 0);
						if(flag) break loop;
					}
				}
			}
		}
		
		System.out.println(answer);
	}
	
	static void dfs(Node curNode, String dictWord, int r, int c, int depth) {
		if(visited[r][c]) return;
		if(flag) return;
		else if(depth == dictWord.length()-1 && curNode.eow) {
			answer++;
			flag = true;
			return;
		}
		if(depth+1 >= dictWord.length()) return;
		
		visited[r][c] = true;
		for(int d = 0; d<8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr >=0 && nr <5 && nc>=0 && nc<5) {
				if(dictWord.charAt(depth+1) == map[nr][nc] && curNode.childrens.containsKey(map[nr][nc])) {
					dfs(curNode.childrens.get(map[nr][nc]), dictWord, nr, nc, depth+1);
				}
			}
		}
		visited[r][c] = false;
	}
}
