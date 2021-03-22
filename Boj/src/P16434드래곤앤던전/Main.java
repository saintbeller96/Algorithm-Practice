package P16434드래곤앤던전;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(stk.nextToken());
		int atk = Integer.parseInt(stk.nextToken());
		
		int[][] rooms = new int[N][3];
		for(int i = 0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			rooms[i][0] = Integer.parseInt(stk.nextToken());
			rooms[i][1] = Integer.parseInt(stk.nextToken());
			rooms[i][2] = Integer.parseInt(stk.nextToken());
		}
		long s = 0, e = Long.MAX_VALUE;
		long answer = e;
		
		while(s < e) {
			long mid = (s+e)/2;
			long curH = mid;
			long curAtk = atk;
			for(int i = 0; i<N; i++) {
				if(rooms[i][0] == 1) {
					int atkMonster = rooms[i][1];
					int hpMonster = rooms[i][2];
					
					long maxTurnHero =  hpMonster%curAtk == 0 ? hpMonster/curAtk : hpMonster/curAtk+1;		
					long maxTurnMonster = curH%atkMonster == 0 ? curH/atkMonster: curH/atkMonster+1;
					
					if(maxTurnHero <= maxTurnMonster) {
						curH -=(maxTurnHero-1)*atkMonster;
						if(curH <= 0) break;
					}else {
						curH = 0;
						break;
					}
				}else {
					curAtk += rooms[i][1];
					curH += rooms[i][2];
					if(curH > mid) curH = mid;
				}
			}
			if(curH > 0) {
				answer = Math.min(answer, mid);
				e = mid;
			}else {
				s = mid+1;
			}
		}
		
		System.out.println(answer);
	}
}
