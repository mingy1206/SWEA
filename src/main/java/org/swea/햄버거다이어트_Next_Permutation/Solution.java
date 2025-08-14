package org.swea.햄버거다이어트_Next_Permutation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int best_taste;
    private static int[] T;
    private static int[] K;
    private static int N;
    private static int L;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tast_case = Integer.parseInt(br.readLine());
        for(int t = 1; t <= tast_case; t++){

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            T = new int[N];
            K = new int[N];

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                T[i] = Integer.parseInt(st.nextToken());
                K[i] = Integer.parseInt(st.nextToken());
            }

            best_taste = 0;
            for(int i = 1; i <= N; i++){
                int[] arr =new int[N];
                for(int j = i; j <= N; j++) arr[N-j] = 1;
                do{
                    int taste = 0;
                    int kcal = 0;
                    for(int k = 1; k <= i; k++){
                        taste += T[k];
                        kcal += K[k];
                    }
                    if(kcal < L) best_taste = Math.max(taste, best_taste);
                }while (np(arr));
            }

            System.out.println("#" + t + " " + best_taste);

        }
    }
    public static boolean np(int[] arr){
        int i = arr.length - 1;
        while (i >= 0 && arr[i] == 1) i--;
        if(i==0) return false;

        swap(arr, i, i+1);
        int j = arr.length - 1;
        while (i <= j){
            swap(arr, i, j);
            i++;
            j--;
        }
        return true;
    }
    private static void swap(int[] arr, int i, int j){
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}
