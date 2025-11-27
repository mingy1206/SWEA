package org.baekjoon.리모컨;

// 2개의 경우의 수를 구하면 됨
// 1번 100에서 N는까지 + or -로 가 경우
// 2번 N부터 시작해서 왼쪽 N부터 시작해서 오른쪽을 한 칸씩 탐색하면서
// 가능한 수를 찾고 거기서 + or - 했을 경우

import com.sun.jdi.IntegerValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int result;
    private static int N;
    private static int M;
    private static int[] brokenButtons;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        result = 0;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        brokenButtons = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) brokenButtons[i] = Integer.parseInt(st.nextToken());
        if(N == 100){
            System.out.println(0);
        }else{
            int simple = Math.abs(N - 100);
            int left = left();
            int right = right();
            System.out.println("simple: " + simple);
            System.out.println("left: " + left);
            System.out.println("right: " + right);
        }
    }

    private static int left(){
        int point = N;
        int left = 0;
        while(point >= 0){
            String pointStr = String.valueOf(point);
            boolean flag = false;
            for(int bb :  brokenButtons){
                for(int i = 0; i < pointStr.length(); i++){
                    if(pointStr.charAt(i) == bb) break;
                    else flag = true;
                }
                if(!flag) break;
            }
            if(flag){
                System.out.println(left);
                left = pointStr.length() + Math.abs(N-point);
                return left;
            }
            else{
                point--;
            }

        }
        return left;
    }

    private static int right(){
        int point = N;
        int right = 0;
        while(point <= 500000){
            String pointStr = String.valueOf(point);
            boolean flag = false;
            for(int bb :  brokenButtons){
                for(int i = 0; i < pointStr.length(); i++){
                    System.out.println(pointStr.charAt(i)+" "+ bb);
                    if(pointStr.charAt(i) == bb) break;
                    else flag = true;
                }
                if(!flag) break;
            }
            if(flag){
                System.out.println(right);
                right = pointStr.length() + Math.abs(N-point);
                return right;
            }
            else{
                point++;
            }

        }
        return right;
    }

}
