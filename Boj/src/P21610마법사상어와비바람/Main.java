package P21610마법사상어와비바람;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] A;
    static int[] dr = {0, 0,-1,-1,-1,0,1,1,1};
    static int[] dc = {0,-1,-1, 0, 1,1,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        A = new int[N][N];
        for(int i = 0; i<N; i++){
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        boolean[][] cloud = new boolean[N][N];
        cloud[N-1][0] = cloud[N-1][1] = cloud[N-2][0] = cloud[N-2][1] = true;
        for(int i = 0; i<M; i++){
            stk = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(stk.nextToken());
            int s = Integer.parseInt(stk.nextToken());
            moveCloud(cloud, d, s);
            raining(cloud);
            doMagic(cloud);
            createCloud(cloud);
        }
        int answer = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                answer += A[i][j];
            }
        }
        System.out.println(answer);
    }
    static void moveCloud(boolean[][] cloud, int d, int s){
        boolean[][] newCloud = new boolean[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(cloud[i][j]){
                    int nr = (i + s*dr[d]%N + N)%N;
                    int nc = (j + s*dc[d]%N + N)%N;
                    cloud[i][j] = false;
                    newCloud[nr][nc] = true;
                }
            }
        }
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                cloud[i][j] = newCloud[i][j];
            }
        }
    }

    static void raining(boolean[][] cloud){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(cloud[i][j]){
                    A[i][j]++;
                }
            }
        }
    }
    static void doMagic(boolean[][] cloud){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(cloud[i][j]){
                    for(int d = 1; d<=8; d++){
                        if(d%2 != 0) continue;
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if(nr>=0 && nr<N && nc >= 0 && nc < N){
                            if(A[nr][nc] > 0){
                                A[i][j]++;
                            }
                        }
                    }
                }
            }
        }
    }
    static void createCloud(boolean[][] cloud){
        boolean[][] newCloud = new boolean[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(A[i][j] >= 2 && !cloud[i][j]){
                    A[i][j] -=2;
                    newCloud[i][j] = true;
                }
            }
        }
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                cloud[i][j] = newCloud[i][j];
            }
        }
    }

    static void print(){
        System.out.println();
        for(int i = 0; i<N; i++){
            for (int j = 0; j < N; j++) {
                System.out.printf("%3d", A[i][j]);
            }
            System.out.println();
        }
    }
    static void printCloud(boolean[][] cloud){
        System.out.println();
        for(int i = 0; i<N; i++){
            for (int j = 0; j < N; j++) {
                System.out.printf("%3d", cloud[i][j]?1:0);
            }
            System.out.println();
        }
    }
}
