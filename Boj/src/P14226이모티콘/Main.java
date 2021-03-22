package P14226이모티콘;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int S = Integer.parseInt(br.readLine());
		System.out.println(BFS(S));
	}
	static int BFS(int S) {
		//ȭ�鿡 �ִ� ����, Ŭ�����忡 ����� ����, �ð�
		boolean[][] visited = new boolean[6001][6001];
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {1, 0, 0});
		while(!que.isEmpty()) {
			int[] p = que.poll();
			int emo = p[0];
			int clip = p[1];
			int time = p[2];
			
			if(emo == S) {
				return time;
			}
			
			int copy = emo>clip?emo:clip;
			//����
			if(!visited[emo][copy]) {
				visited[emo][copy] = true;
				que.offer(new int[] {emo, copy, time+1});
			}
			
			//�ٿ��ֱ�
			if(clip != 0 && !visited[emo+clip][clip]) {
				visited[emo+clip][clip]= true;
				que.offer(new int[] {emo+clip, clip, time+1});
			}
			
			//����
			if(emo != 0 && !visited[emo-1][clip]) {
				visited[emo-1][clip] = true;
				que.offer(new int[] {emo-1, clip, time+1});
			}
			
		}
		return -1;
	}
	

}
