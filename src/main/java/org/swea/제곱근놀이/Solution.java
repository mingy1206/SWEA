package org.swea.제곱근놀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    private static long[] memory = new long[100000];
    public static void main(String[] args) throws IOException {
        for(int i = 0; i < 100000; i++) memory[i] = i*i;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            long result = 0;
            long N = Long.parseLong(br.readLine());

            while (N!=2){
                double sqrt = Math.sqrt(N);
                if(sqrt%1 == 0){
                    N = (long) sqrt;
                    result++;
                }

                else{
                    long nextSquarNum = (long) Math.ceil(sqrt);
                    long nextSquarRootNum = nextSquarNum*nextSquarNum;


                    result = result + nextSquarRootNum - N + 1;
                    N = nextSquarNum;
                }
            }

            System.out.println("#" + t + " " + result);
        }
    }

}
/*
4
2
3
4
99
 */