package org.swea.햄버거다이어트_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
    private static int N;
    private static int L;
    private static int[] T;
    private static int[] K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int test = Integer.parseInt(br.readLine());
        for(int t = 1; t <= test; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            T = new int[N];
            K = new int[N];

            for (int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                T[i] = Integer.parseInt(st.nextToken());
                K[i] = Integer.parseInt(st.nextToken());
            }

            int[] Tier = new int[L+1];
            int result = 0;
            for(int i  = 0; i < N; i++){
                int taste = T[i];
                int Kcal = K[i];
                if(Kcal > L) continue;

                for(int j = L; j >= Kcal; j--){
                    int originalTaste = Tier[j-Kcal];
                    if(originalTaste + taste > Tier[j]) {
                        int newTaste= originalTaste + taste;
                        Tier[j] = newTaste;
                        result = Math.max(result, newTaste);
                    }
                }
            }

            System.out.println("#" + t + " " + result);
        }
    }
}
