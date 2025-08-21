package org.swea.무선충전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 이번 문제는 지도 초기화, 충전기 공유, 탐색만 잘하면 됨
// 경로와 위치를 다 지정해 줌. 인원 및 지도 크기 고정, BC 및 탐색 범위 적음
// 지도 초기화는 중복 순열로 초기화, 10X10 배열에 각각 BC개수 만큼의 공간을 할당 어떤 BC가 존재하는지 확인
// using 배열을 통해 사용 중인 충전기 확인하여 전력량 분산 혹은 비교 연산을 통한 다른 충전기 사용하기

public class Solution {
    private static class BC {
        int x;
        int y;
        int coverage;
        int performance;
        public BC(int x, int y, int coverage, int performance){
            this.x = x;
            this.y = y;
            this.coverage = coverage;
            this.performance = performance;
        }
    }

    private static final int N = 10;
    private static int BCNum;
    private static int finishTime;
    private static int[] A;
    private static int[] B;
    private static List<BC> BCList;
    private static boolean[] using;
    private static int[][] room;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++){
            room = new int[N+1][N+1];
            st = new StringTokenizer(br.readLine());
            finishTime = Integer.parseInt(st.nextToken());
            BCNum = Integer.parseInt(st.nextToken());
            A = new int[finishTime];
            B = new int[finishTime];
            BCList = new ArrayList<>();
            using = new boolean[BCNum];




            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < finishTime; i++)
                A[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < finishTime; i++)
                B[i] = Integer.parseInt(st.nextToken());

            for(int i = 0; i < BCNum; i++){
                st = new StringTokenizer(br.readLine());
                int[] BCInfo = new int[4];
                for(int j = 0; j < 4; j++) BCInfo[j] = Integer.parseInt(st.nextToken());

                BCList.add(new BC(BCInfo[0], BCInfo[1], BCInfo[2], BCInfo[3]));

            }

        }
    }
    
    private static void roomSetting(){

    }
}
