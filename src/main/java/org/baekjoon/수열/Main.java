package org.baekjoon.수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        int maxValue = 0;
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(i < K) maxValue += arr[i];
        }
        int left = 0;
        int right = K;
        int temp = maxValue;
        for(int i = 0; i < N-K; i++){
//            System.out.println(i+"번째");
//            System.out.println(temp +" - " + arr[left]+" + " + arr[right]+" = " + (temp - arr[left] + arr[right]));
            temp = temp - arr[left] + arr[right];
            maxValue = Math.max(maxValue, temp);
            left++;
            right++;
        }
        System.out.println(maxValue);
    }
}
