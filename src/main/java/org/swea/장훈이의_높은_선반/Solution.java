package org.swea.장훈이의_높은_선반;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class
Solution {
    private static int N;
    private static int B;
    private static int[] P;
    private static int min_result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            min_result = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            P = new int[N];
            for (int i = 0; i < N; i++)
                P[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(P);
            dfs(0, 0);
            System.out.println("#" + t + " " + (min_result - B));
        }
    }
    public static void dfs(int index, int pTop) {
        if (pTop >= B) {//기저 조건
            if (pTop < min_result)
                min_result = pTop;
            return;
        } else if (index >= N) {
            return;
        } else {
            dfs(index + 1, pTop + P[index]);
            dfs(index+1, pTop);
        }
    }
}