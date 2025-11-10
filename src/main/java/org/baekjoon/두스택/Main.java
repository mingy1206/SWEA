package org.baekjoon.두스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
누적 합 자체는 간단함
결국 K개의 비워내는 조합을 구하는 것
간순하게 두 배낭에서 K-m, m개씩 뽑아서 쓰기
K는 최대 2*10^5 충분히 가능
 */
public class Main {
    private static long min_value;
    private static long[] A;
    private static long[] B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        A = new long[N];
        B = new long[N];
        min_value = Long.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            if(i==0) A[i] = Integer.parseInt(st.nextToken());
            else A[i] = A[i-1]+Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            if(i==0) B[i] = Integer.parseInt(st.nextToken());
            else B[i] = B[i-1]+Integer.parseInt(st.nextToken());
        }

        if(K==0){
            min_value = Math.max(A[A.length-1], B[B.length-1]);
        }
        else {
            poping(N, K);
        }

        System.out.println(min_value);
    }

    public static void poping(int N, int K){
        for(int i = 0; i <= K; i++){
            int j = K - i;

            if(i > N || j > N || j < 0) continue;


            long val_A;
            long val_B;

            if (i == N) val_A = 0;
            else val_A = A[N - i - 1];

            if (j == N) val_B = 0;
            else val_B = B[N - j - 1];


            long selected = Math.max(val_A, val_B);
            min_value = Math.min(min_value, selected);
        }
    }

}
/*
3 2
3 1 4
1 5 9
 */