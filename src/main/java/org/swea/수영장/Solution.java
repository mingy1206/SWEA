package org.swea.수영장;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            int[] prices = new int[4];
            int[] planes = new int[13];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) prices[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 12; i++) planes[i] = Integer.parseInt(st.nextToken());
            int[] dp = new int[13];
            for(int i = 1; i <= 12; i++){
                int minValue = Math.min(planes[i-1]*prices[0], prices[1]);
                dp[i] = dp[i-1] + minValue;
                if (i >=3) dp[i] = Math.min(dp[i], dp[i-3] + prices[2]);
            }
            int result = Math.min(dp[12], prices[3]);
            System.out.println("#" + t +" " + result);
        }

    }

}
/*
2
10 40 100 300
0 0 2 9 1 5 0 0 0 0 0 0
10 100 50 300
0 0 0 0 0 0 0 0 6 2 7 8
 */