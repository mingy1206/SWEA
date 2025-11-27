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
        if(M>0){
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++) brokenButtons[i] = Integer.parseInt(st.nextToken());
        }

        if(N == 100){
            System.out.println(0);
        }else{
            int simple = Math.abs(N - 100);
            int left = left();
            int right = right();
            System.out.println(Math.min(Math.min(simple,left),right));
//            System.out.println("simple: " + simple);
//            System.out.println("left: " + left);
//            System.out.println("right: " + right);
        }
    }

    private static boolean canPress(int point) {
        String pointStr = String.valueOf(point);

        for(int i = 0; i < pointStr.length(); i++) {
            int digit = pointStr.charAt(i) - '0';
            for(int bb : brokenButtons) {
                if(digit == bb) return false;
            }
        }
        return true;
    }

    private static int left(){
        for(int point = N; point >= 0; point--){
            if(canPress(point)){
                return String.valueOf(point).length() + (N - point);
            }
        }
        return Integer.MAX_VALUE;
    }

    private static int right(){
        for(int point = N; point <= 1000000; point++){
            if(canPress(point)){
                return String.valueOf(point).length() + (point - N);
            }
        }
        return Integer.MAX_VALUE;
    }
}
