package P15997승부예측;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static HashMap<String, Integer> idxMap;
	static Board[] boards;
	static double[] result = {0.0, 0.0, 0.0, 0.0};
	static class Board {
		int nation;
		int score;
		double rate;
		public Board() {};
		public Board(int n, int s, double r) {
			nation = n;
			score = s;
			rate = r;
		}
		@Override
		public String toString() {
			return "Board [nation=" + nation + ", score=" + score + ", rate=" + rate + "]";
		}
	}

	static class Match {
		int A, B;
		double w, d, l;
		public Match(int a, int b, double w, double d, double l) {
			A = a;
			B = b;
			this.w = w;
			this.d = d;
			this.l = l;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nations = br.readLine().split(" ");
		boards = new Board[4];
		idxMap = new HashMap<String, Integer>();
		for (int i = 0; i < 4; i++) {
			boards[i] = new Board(i, 0, 1.0);
			idxMap.put(nations[i], i);
		}
		
		StringTokenizer stk = null;
		Match[] matches = new Match[6];

		for (int i = 0; i < 6; i++) {
			stk = new StringTokenizer(br.readLine());
			int A = idxMap.get(stk.nextToken());
			int B = idxMap.get(stk.nextToken());
			double w = Double.parseDouble(stk.nextToken());
			double d = Double.parseDouble(stk.nextToken());
			double l = Double.parseDouble(stk.nextToken());
			matches[i] = new Match(A, B, w, d, l);
		}
		match(matches, 0, 1.0);
		for(double d : result) {
			System.out.println(d);
		}
	}

	static void match(Match[] matches, int round, double pos) {
		if (round == 6) {
			Board[] tmp = new Board[4];
			System.arraycopy(boards, 0, tmp, 0, 4);		
			Arrays.sort(tmp, (i, j)->Integer.compare(j.score, i.score));
			Board a = tmp[0]; Board b = tmp[1];Board c = tmp[2]; Board d = tmp[3];
			if(b.score > c.score) {
				result[a.nation] += pos;
				result[b.nation] += pos;
			}else if(a.score == b.score && b.score == c.score) {
				if(c.score == d.score) {
					result[a.nation] += pos/2.0;
					result[b.nation] += pos/2.0;
					result[c.nation] += pos/2.0;
					result[d.nation] += pos/2.0;
				}else {
					result[a.nation] += pos*2.0/3.0;
					result[b.nation] += pos*2.0/3.0;
					result[c.nation] += pos*2.0/3.0;
				}
			}else if(a.score > b.score && b.score == c.score) {
				if(c.score == d.score) {
					result[a.nation] += pos;
					result[b.nation] += pos/3.0;
					result[c.nation] += pos/3.0;
					result[d.nation] += pos/3.0;
				}else {
					result[a.nation] += pos;
					result[b.nation] += pos/2.0;
					result[c.nation] += pos/2.0;
				}
			}
			return;
		}
		int A = matches[round].A;
		int B = matches[round].B;

		double w = matches[round].w;
		double d = matches[round].d;
		double l = matches[round].l;
		
		if(w != 0) {
			boards[A].score += 3;
			match(matches, round + 1, pos * w);
			boards[A].score -= 3;
		}
		
		if(l != 0) {
			boards[B].score += 3;
			match(matches, round + 1, pos * l);
			boards[B].score -= 3;
		}
		
		if(d!= 0) {
			boards[A].score += 1;
			boards[B].score += 1;
			match(matches, round + 1, pos * d);
			boards[A].score -= 1;
			boards[B].score -= 1;
		}
	}
}
