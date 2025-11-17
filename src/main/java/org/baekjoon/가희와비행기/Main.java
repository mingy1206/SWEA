package org.baekjoon.가희와비행기;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.nio.Buffer;
    import java.util.StringTokenizer;

/*
중복 조합으로 -1(down), 1(up)을 sum이 d-1이 0이어야 함. 그전에 0이 되는 값이 있으면 무조건 1
기저조건에서 sum이 0이면 private count++;
 */
public class Main {
    private static long count;
    private static int d;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        count = 0;
        d = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        System.out.println(dp());

    }
    private static long dp() {
        long[][] dp = new long[d + 1][d + 1];

        dp[0][0] = 1;

        for (int i = 0; i < d; i++) {
            for (int j = 0; j <= d; j++) {

                long currentCases = dp[i][j];
                if (currentCases == 0) {
                    continue;
                }

                if (j + 1 <= d) {
                    dp[i + 1][j + 1] = (dp[i + 1][j + 1] + currentCases) % m;
                }

                if (j - 1 >= 0) {
                    if (j - 1 == 0 && i + 1 != d) {
                        continue;
                    }

                    dp[i + 1][j - 1] = (dp[i + 1][j - 1] + currentCases) % m;
                }
            }
        }

        return dp[d][0];
    }
//    private static  void combination(int sum, int k){
//        if(k==d) {
//            if(sum!=0) return;
//            count++;
//            return;
//        }
////        System.out.println(sum+" "+k);
//        if(sum -1 <= 0 && k != d-1)
//            combination(sum+1, k+1);
//        else{
//            combination(sum+1, k+1);
//            combination(sum-1, k+1);
//        }
//    }
}
