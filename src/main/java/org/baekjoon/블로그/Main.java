package org.baekjoon.블로그;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[] days = new int[N];
        int result = 0;
        st = new StringTokenizer(br.readLine());
        for(int i =0; i<N; i++){
            days[i] = Integer.parseInt(st.nextToken());
            if(i < X) result += days[i];
        }

        int temp = result;
        int same = 1;
        for(int i =0; i<N-X; i++){
            temp = temp - days[i] + days[i+X];
            if(result == temp) same++;
            else if(temp > result){
                result = temp;
                same = 1;
            }
        }

        if (result == 0)
            System.out.println("SAD");
        else {
            System.out.println(result);
            System.out.println(same);
        }


    }
}
