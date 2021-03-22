package P2982국왕의방문;

import java.util.Scanner;

public class Test {
    static int R; // ��
    static int C; // ��
    static int[][] hall;
    static int a = 1;
    static int M;
    static int z, w;

    public static void main(String[] args) {
        int x = 0;
        int y = 0;
        Scanner sc = new Scanner(System.in);
        C = sc.nextInt();
        R = sc.nextInt();
        // M = sc.nextInt();
        int r = R;
        int c = C;
        int b = R;
        hall = new int[R][C];
        for (int i = 0; i < R; i++) {
            hall[i][0] = b--;
        }
        C--;
        R--;
        int cnt = hall[0][0];
        while (true) {
            for (int i = 0; i < C; i++) {
                hall[x][++y] = ++cnt;
            } //��
            C--;
            if(C < 0) break;
            for (int i = 0; i < R; i++) {
                hall[++x][y] = ++cnt;
            }//��
            R--;
            if(R < 0) break;
            for (int i = 0; i < C; i++) {
                hall[x][--y] = ++cnt;
            }//��
            C--;
            if(C < 0) break;
            for (int i = 0; i < R; i++) {
                hall[--x][y] = ++cnt;
            }//��
            R--;
            if(R < 0) break;
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(hall[i][j] + "  ");
//                if (hall[i][j]==M) {
//                    System.out.println(j+1);
//                    System.out.println(c-i-1);
//                    //System.out.print((j+1)+" "+(r-i));
//                }
            }
            System.out.println();
        }
        if (M > r * c) {
            System.out.println("0");
        }
    }
}
