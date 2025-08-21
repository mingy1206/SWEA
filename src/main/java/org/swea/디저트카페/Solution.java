package org.swea.디저트카페;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


public class Solution {
    private static int N;
    private static int[][] cafeList;
    private static final int[][] directions = {{-1,1}, {1,1}, {1,-1}, {-1,-1}}; // 왼아, 오아, 오위, 왼위
    private static int maxvisit;
    private static int[] start;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            N = Integer.parseInt(br.readLine());
            cafeList = new int[N][N];
            for (int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    cafeList[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            maxvisit = -1;

            for(int y = 0; y < N; y++){
                for(int x = 0; x < N; x++){
                    start = new int[] {x,y};
                    List<Integer> list = new ArrayList<>();
                    list.add(cafeList[y][x]);
                    search(x,y,0, list);
                }
            }
            System.out.println("#" + t + " " + maxvisit);
        }
    }
    // dfs의 계층 제한적 탐색
    // 각 변의 1 ~ N까지의 탐색
    private static void search(int x, int y, int index, List list) {
        int[] direction = directions[index];
        int nx = x + direction[0];
        int ny = y + direction[1];

        if (!(nx < 0 || nx >= N || ny < 0 || ny >= N)) {
            if (index == 3 && nx == start[0] && ny == start[1]) { //기저조건
                maxvisit = Math.max(maxvisit, list.size());
                return;
            }
            int dessert = cafeList[ny][nx];
            if (list.contains(dessert)) { // 중복된 디저트
                return;
            }

            List<Integer> newList = new ArrayList<>(list);
            newList.add(dessert);
            search(nx, ny, index, newList);
            if ((index + 1) < 4)
                search(nx, ny, index + 1, newList);
        }
    }
}
