package org.swea.벽돌깨기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    private static int T, N, W, H;
    private static int[][] blockList;
    private static int minimumRestBlock;
    private static int[][] directions = {{0,1},{1,0},{-1,0},{0,-1}};
    private static int[] choices;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int t=1; t<=T; t++){
            minimumRestBlock = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            blockList = new int[H][W];
            choices = new int[N];

            for(int i=0; i<H; i++){
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<W; j++){
                    blockList[i][j] = Integer.parseInt(st.nextToken());
                }
            }



            recursion(0);


            System.out.println("#"+t+" "+minimumRestBlock);

            }
        }
    private static void recursion(int depth){
        if(depth == N){
            int[][] simulationList = copyArray(blockList);

            for(int i=0; i<N; i++){
                int x = choices[i];
                int y;
                for(y = 0; y<H; y++){
                    if(simulationList[y][x] != 0)break;
                }
                if(y!=H){
                    blockBreak(simulationList, x,y);
                    blockArrange(simulationList);
                }

            }

            minimumRestBlock = Math.min(blockCount(simulationList), minimumRestBlock);
            return;
        }



        for(int x=0; x<W; x++){
            choices[depth] = x;
            recursion(depth+1);
        }
    }


    private static void blockBreak(int[][]simulationList, int x, int y) {
        if(y<0 || y>=H || x<0 || x>=W || simulationList[y][x] == 0) return;

        int breakNum = simulationList[y][x];
        simulationList[y][x] = 0;

        for(int d=0; d<4; d++){
            int[] direction = directions[d];
            int ny = y,nx = x;
            for(int i=0; i<breakNum-1; i++){
                ny = ny + direction[0];
                nx = nx + direction[1];
                if(ny<0 || ny>=H || nx<0 || nx>=W) break;

                if(simulationList[ny][nx] > 0) blockBreak(simulationList, nx, ny);
            }
        }
    }

    private static int blockCount(int[][] simulationList){
        int cnt = 0;
        for(int y=0; y<H; y++){
            for(int x=0; x<W; x++){
                   if(simulationList[y][x] !=0) cnt++;
            }
        }
        return cnt;
    }

    private static void blockArrange(int[][] simulation){
        for(int x=0; x<W; x++){
            int[] temp = new int[H];
            int brickCount  = 0;
            for(int y=0; y<H; y++){
                if(simulation[y][x] != 0) temp[brickCount ++] = simulation[y][x];
            }
            for(int y=0; y<H; y++){
                simulation[y][x] = 0;
            }
            int index = 0;
            for(int y = H - brickCount; y < H; y++){
                simulation[y][x] = temp[index++];
            }
        }
    }
    private static int[][] copyArray(int[][] original){
        int[][] copy = new int[H][W];
        for(int i=0; i<H; i++){
            copy[i] = Arrays.copyOf(original[i], W);
        }
        return copy;
    }

}



