package P21610마법사상어와비바람;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        A = new int[N][N];
        for(int i = 0; i<N; i++){
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                A[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        print();
        for(int i = 0; i<M; i++){
            stk = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(stk.nextToken());
            int s = Integer.parseInt(stk.nextToken());

        }
    }

    static void print(){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                System.out.printf("%3d", A[i][j]);
            }
            System.out.println();
        }
    }
}
