package P17404RGB거리2;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        for (int t = 0; t < T; t++) {
            String s = sc.nextLine();
            StringTokenizer st = new StringTokenizer(s," ");
            String ss = "";
            for (int i = 0; st.hasMoreTokens(); i++) {
                ss = st.nextToken();
                char[] c = ss.toCharArray();
                for (int j = c.length-1; j >=0; j--) {
                    System.out.print(c[j]);
                }
                System.out.print(" ");
            }
            System.out.println();
        }sc.close();
    }
}
