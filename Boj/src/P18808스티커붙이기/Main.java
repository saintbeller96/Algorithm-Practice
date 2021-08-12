package P18808스티커붙이기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int R, C, K;
	static boolean[][] board;
	static class Sticker{
		int r, c;
		int[][] arr;
		public Sticker(int r, int c, int[][] arr){
			this.r = r;
			this.c = c;
			this.arr = arr;
		}
		public void rotate(){
			int[][] temp = new int[r][c];
			for(int i = 0; i<r; i++){
				for(int j = 0; j<c; j++){
					temp[i][j] = arr[i][j];
				}
			}
			//회전
			this.arr = new int[c][r];
			for(int i = 0; i<r; i++){
				for(int j = 0; j<c; j++){
					arr[j][r-i-1] = temp[i][j];
				}
			}
			int t = r;
			r = c;
			c = t;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		board = new boolean[R][C];
		Sticker[] stickers = new Sticker[K];
		for(int k = 0; k<K; k++){
			stk = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			int[][] arr = new int[r][c];
			for(int i = 0; i<r; i++){
				stk = new StringTokenizer(br.readLine());
				for(int j = 0; j<c; j++){
					arr[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			stickers[k] = new Sticker(r, c, arr);
		}

		for(Sticker sticker : stickers){
			for(int k = 0; k<4; k++){
				boolean b = check(sticker);
				if(b) break;
				else sticker.rotate();
			}
		}
		int answer = 0;
		for(int i = 0; i<R; i++){
			for(int j = 0; j<C; j++){
				if(board[i][j]) answer++;
			}
		}
		System.out.println(answer);
	}
	static boolean check(Sticker sticker){
		for(int i = 0; i<=R- sticker.r; i++){
			for(int j = 0; j<=C - sticker.c; j++){
				if(canBeAttached(sticker, i, j)){
					attach(sticker, i, j);
					return true;
				}
			}
		}
		return false;
	}

	static boolean canBeAttached(Sticker sticker, int r, int c){
		for(int i = r; i<r+ sticker.r; i++){
			for(int j = c; j<c+ sticker.c; j++){
				if(sticker.arr[i-r][j-c] == 1 && board[i][j]){
					return false;
				}
			}
		}
		return true;
	}
	static void attach(Sticker sticker, int r, int c){
		for(int i = r; i<r+ sticker.r; i++){
			for(int j = c; j<c+ sticker.c; j++){
				if(sticker.arr[i-r][j-c] == 1){
					board[i][j] = true;
				}
			}
		}
	}


	static void printSticker(Sticker sticker){
		System.out.println();
		for(int i = 0; i<sticker.r; i++){
			for(int j = 0; j< sticker.c; j++){
				System.out.print(sticker.arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
