package P1861;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static int[][] map;
    private static int T, N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = null;
        T = Integer.parseInt(br.readLine());
         
        for(int t = 1; t<= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
             
            int max = 0;
            int min = Integer.MAX_VALUE;
            int maxI = 0;
            int maxJ = 0;
            for (int i = 0; i < N; i++) {
                stk = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(stk.nextToken());
                }
            }
             
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int tmp = go(i, j);
                    if (max < tmp) {
                        maxI = i;
                        maxJ = j;
                        max = tmp;
                        min = map[i][j];
                    }else if(max == tmp) {
                        if(min > map[i][j]) {
                            maxI = i;
                            maxJ = j;
                            max = tmp;
                            min = map[i][j];
                        }
                    }
                }
            }
            System.out.println("#" + t + " " + map[maxI][maxJ] + " " + max);
        }
        br.close();
    }
 
    private static int go(int i, int j) {
        int len = 1;
        int d = check(i, j);
        if (d == -1) {
            return 1;
        }
        len += go(i + dr[d], j + dc[d]);
        return len;
    }
     
    private static int check(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= N) {
            return -1;
        }   
        for (int d = 0; d < 4; d++) {
            if (i + dr[d] >= 0 && i + dr[d] < N && j + dc[d] >= 0 && j + dc[d] < N) {
                if(map[i][j]+ 1 == map[i+dr[d]][j+ dc[d]]) {
                    return d;
                }
            }
        }       
        return -1;
    }
}
