package org.baekjoon.구간합구하기4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] sum = new int[N+1];
        sum[0] = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            int num = Integer.parseInt(st.nextToken());
            sum[i] = sum[i-1] + num;
        }

        for(int l = 0; l < M; l++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            System.out.println(sum[j] - sum[i-1]);

        }

    }
}
/*
5 4 3 2 1
5 9 12 14 15

5 3
5 4 3 2 1
1 3
2 4
5 5
 */