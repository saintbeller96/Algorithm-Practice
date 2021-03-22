package P14631로만들기;

import java.util.Scanner;

public class Main {
	static int[] su=new int[1000001];
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		int N=scann.nextInt();
		
		su[1]=0;  // 1-> 1
		su[2]=1;  // 2 -> 1
		for (int i = 2; i <=N; i++) {
			su[i]=su[i-1]+1;
			if(i%2==0){su[i]= Math.min(su[i], su[i/2]+1);}
			if(i%3==0){su[i]= Math.min(su[i], su[i/3]+1);}
		}
		System.out.println(su[N]);
	}

}
