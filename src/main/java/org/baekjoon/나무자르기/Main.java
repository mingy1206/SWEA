package org.baekjoon.나무자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int M;
    private static int[] trees;
    private static long result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trees = new int[N];
        result = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++) trees[i] = Integer.parseInt(st.nextToken());

        parametricSearch();
        System.out.println(result);
    }
    private static void parametricSearch(){
        int low = 1;
        int high = 1000000001;
        long cuttingLength = 0;
        while(low <= high){
            int mid = (low+high)/2;
            cuttingLength = cutting(mid);
            if(cuttingLength < M) {
                high = mid-1;
            }
            else{
                result = mid;
                low = mid+1;
            }
        }

    }

    private static long cutting(int height){
        long cuttingLength = 0;
        for(int i = 0; i < N; i++){
            if(trees[i] <= height) continue;
            cuttingLength += trees[i] - height;
        }
        return cuttingLength;
    }

}
