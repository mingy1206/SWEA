package org.swea.Knapsack01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[][] things = new int[N][2];
            for (int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                things[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            }

            int[] DP = new int[K+1];
            for(int i=0; i<N; i++){
                int[] thing = things[i];
                int v = thing[0];
                int c = thing[1];
                for(int j=K; j>=v; j--){
                    DP[j] = Math.max(DP[j], DP[j-v]+c);
                }
            }
            System.out.println("#" + t + " " + DP[K]);
        }
    }

}
