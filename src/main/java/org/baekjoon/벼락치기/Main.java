package org.baekjoon.벼락치기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[] dp = new int[T + 1];
        int total_penalty = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());   // 걸리는 시간 (무게)
            int penalty = Integer.parseInt(st.nextToken()); // 벌금 (가치)

            total_penalty += penalty;

            for (int j = T; j >= time; j--) {
                dp[j] = Math.max(dp[j], dp[j - time] + penalty);
            }
        }

        System.out.println(total_penalty - dp[T]);
    }
}
