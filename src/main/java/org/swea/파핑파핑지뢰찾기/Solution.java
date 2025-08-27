package org.swea.파핑파핑지뢰찾기;
// dfs를 진행하면서 count

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    private static boolean visited[][];
    private static int N;
    private static String[][] gameMap;
    private static final int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {-1, 0}, {0, -1}, {-1, -1}, {1, -1}, {-1, 1}}; //8방 탐색

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            visited = new boolean[N][N];
            gameMap = new String[N][N];
            for (int i = 0; i < N; i++) gameMap[i] = br.readLine().split("");

            visited = new boolean[N][N];
            int result = 0;
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (visited[y][x] || gameMap[y][x].equals("*")) continue;
                    boolean flag = false;
                    for (int[] direction : directions) {
                        int nx = x + direction[0];
                        int ny = y + direction[1];
                        if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                        if (gameMap[ny][nx].equals("*")) {
                            flag = true;
                            break;
                        }
                    }
                    if(!flag){
                        dfs(x,y);
                        result++;
                    }
                }
            }

            for(int y = 0; y < N; y++){
                for(int x = 0; x < N; x++){
                    if(!visited[y][x] && gameMap[y][x].equals(".")){
                        result++;
                    }
                }
            }


            System.out.println("#" + t + " " + result);
        }
    }

    private static void dfs(int x, int y) {
        visited[y][x] = true;
        int count = 0;
        for (int[] direction : directions) {
            int nx = x + direction[0];
            int ny = y + direction[1];
            if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
            if (gameMap[ny][nx].equals("*")) {
                count++;
            }
        }


        if(count > 0) return;
        for (int[] direction : directions) {
            int nx = x + direction[0];
            int ny = y + direction[1];
            if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
            if (gameMap[ny][nx].equals(".") && !visited[ny][nx]) {
                dfs(nx, ny);
            }

        }
    }
}

