//package P5653줄기세포배양;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.PriorityQueue;
//import java.util.StringTokenizer;
// 
///***
// * 
// *  최대시간 k=300 즉, (시작가로+k*2)*(시작세로+k*2)(넓이= 최대 반경.)
// * 
// * 
// */
//public class Solution {
//    static int[][] map;
//    static ArrayList<Point> inactive; // 비활성 세포
//    static PriorityQueue<Point> active; // 활성 세포
//    static int[] di = { -1, 0, 1, 0 };
//    static int[] dj = { 0, -1, 0, 1 };
// 
//    public static void main(String[] args) throws NumberFormatException, IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int TC = Integer.parseInt(br.readLine());
//        for (int tc = 1; tc <= TC; tc++) {
//            StringBuilder sb = new StringBuilder();
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            int N = Integer.parseInt(st.nextToken());
//            int M = Integer.parseInt(st.nextToken());
//            int K = Integer.parseInt(st.nextToken());
//            map = new int[N + K * 2][M + K * 2];
//            inactive = new ArrayList<>();
//            active = new PriorityQueue<>();
//            for (int i = 0; i < N; i++) {
//                st = new StringTokenizer(br.readLine());
//                for (int j = 0; j < M; j++) {
//                    int temp = Integer.parseInt(st.nextToken());
//                    if (temp != 0) {
//                        int x = i + K;
//                        int y = j + K;
//                        int life = temp;
//                        map[x][y] = life; // 기록하는 이유는 나중에 증식할때 겹치는 경우, 더 큰값으로 변경하고 이전 큐값 버리기위해..
//                        inactive.add(new Point(x, y, life, 0));
////                      check[x][y] = true;
//                    }
//                }
//            } // 여기까지 입력 완료.
//            for (int time = 1; time<=K ; time++) { // 시뮬레이션 고고.
//                for (int c = 0; c < inactive.size(); c++) { // 모든 비활성 세포들에 대하여 활성상태로 전환되는 애들 옮겨 담기
//                    Point cell = inactive.get(c);
//                    cell.now++;
//                    if (cell.life < cell.now) { // 생명력이 1인 애들이 1시간 지났다고 바로 번식하는게 아님. 다시 한시간 더 지나서부터 번식함.
//                        active.add(cell);
//                    }
//                } // 활성인 애들 따로 다 담았음.
//                 
//                while (!active.isEmpty()) {
//                    Point cell = active.poll(); // 생명력 큰 놈들부터 먼저 나옴.
//                    for (int d = 0; d < 4; d++) {
//                        int ni = cell.x + di[d];
//                        int nj = cell.y + dj[d];
//                         
//                        if(map[ni][nj]==0) { // 어차피 생명력 큰애들부터 나와서 번식 먼저 시키니까 그냥 빈칸인지만 검사하면 됨.
//                            inactive.add(new Point(ni,nj,cell.life, 0));
//                            map[ni][nj] = cell.life;
//                        }
//                    }
//                }
//                 
//                for (int c = 0; c < inactive.size(); c++) { // 번식하고 나서 수명 다된 애들은 죽으면 됨.
//                    Point cell = inactive.get(c);
//                    if (cell.life * 2 == cell.now) {
//                        inactive.remove(c);
//                        map[cell.x][cell.y]=-1;
//                        c--;
//                    } 
//                } // 활성인 애들 따로 다 담았음.
//            }
//            int cnt = 0;
//            for (int[] arr : map) {
//                for (int a : arr) {
//                    if (a == 0 || a == -1)
//                        continue;
//                    else
//                        cnt++;
//                }
//            }
//            System.out.println("#" + tc + " " + cnt);
//        }
//    }
// 
//    static class Point implements Comparable<Point> {
//        int x;
//        int y;
//        int life;
//        int now;
// 
//        public Point(int x, int y, int life, int now) {
//            super();
//            this.x = x;
//            this.y = y;
//            this.life = life;
//            this.now = now;
//        }
// 
//        @Override
//        public String toString() {
//            return "Point [x=" + x + ", y=" + y + ", life=" + life + ", now=" + now + "]";
//        }
// 
//        @Override
//        public int compareTo(Point o) { // 내림차순
//            return o.life - this.life;
//        }
//    }
//}