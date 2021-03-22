package P5653줄기세포배양;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Solution {
	static class Cell{
		int life, state, age;
		boolean isBreed;
		public Cell(int life, int statue, int w) {
			this.life = life;
			this.state = statue;
			this.age = w;
			this.isBreed = false;
		}
		@Override
		public String toString() {
			return "Cell [life=" + life + ", state=" + state + "]";
		}
		
	}
	static class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
	}
	static HashMap<Point, Cell> grid;
	static int N, M, K;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		int T = Integer.parseInt(br.readLine());
		for(int t= 1; t<=T; t++) {
			int answer = 0;
			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			K = Integer.parseInt(stk.nextToken());
			
			grid = new HashMap<Point, Cell>();
			
			for(int i = 0; i<N; i++) {
				stk = new StringTokenizer(br.readLine());
				for(int j = 0; j<M; j++) {
					int life = Integer.parseInt(stk.nextToken());
					if(life != 0) grid.put(new Point(i, j), new Cell(life, 0, 0));
				}
			}
			
			answer = simulation();
			
			System.out.println("#" + t + " " + answer);
		}
	}
	
	static int simulation() {
		int nAlive = grid.size();//살아있는 세포 수 갱신
		for(int t = 1; t<= K; t++) {
			//번식할 세포를 저장할 임시 Map
			HashMap<Point, Cell> tmp = new HashMap<Point, Cell>();
			for(Entry<Point, Cell> e : grid.entrySet()) {
				int x = e.getKey().x;
				int y = e.getKey().y;
				Cell cell = e.getValue();
				//비활성화 상태면
				if(cell.state == 0) {
					//대기한 시간 증가
					cell.age++;
					//대기한 시간이 생명력과 같아진다면 활성 상태로 변경
					if(cell.age == cell.life) {
						cell.state = 1;
						cell.age = 0;
					}
				}else if(cell.state == 1) {
					cell.age++;
					//번식한 상태
					if(cell.isBreed) {
						if(cell.age == cell.life) {
							cell.state = -1;
							nAlive--;
						}
					}
					//번식하지 않은 상태
					else {
						//번식가능한 4방향 탐색
						for(int d = 0; d<4; d++) {
							int nx = x + dx[d];
							int ny = y + dy[d];
							Point p = new Point(nx, ny);
							//번식할 위치에 세포가 없고
							if(!grid.containsKey(p)) {
								//같은 장소에 번식할 세포가 없다면
								if(!tmp.containsKey(p)) {
									//번식할 세포 Map에 추가
									tmp.put(p, new Cell(cell.life, 0,0));
									nAlive++;
								}
								//같은 장소에 번식할 세포가 있다면 생명력이 더 큰 세포를 추가
								else {
									Cell c = tmp.get(p);
									if(c.life < cell.life) {
										tmp.put(p, new Cell(cell.life, 0,0));
									}
								}
							}
						}
						//세포의 나이가 생명력과 동일하다면 죽은 세포로 처리
						if(cell.age == cell.life) {
							cell.state = -1;
							nAlive--;
						}
						//이세포가 번식했음
						cell.isBreed = true;
					}
				}
				//세포 진행 끝
			}
			//번식할 세포들을 grid에 추가
			for(Entry<Point, Cell> e : tmp.entrySet()) {
				grid.put(e.getKey(), e.getValue());
			}
		}
		return nAlive;
	}
}
