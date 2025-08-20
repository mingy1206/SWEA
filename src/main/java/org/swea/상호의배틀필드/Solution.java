package org.swea.상호의배틀필드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

public class Solution {
    private static final int[][] directions = {{0,-1},{0,1},{-1,0},{1,0}}; // 상하좌우 순서 [x,y]로 저장
    private static int direction;
    private static int[] point;
    private static String[][] battleMap;
    private static int W, H;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            battleMap = new String[H][W];

            for(int h = 0; h < H; h++){
                st = new StringTokenizer(br.readLine());
                String str = st.nextToken();
                battleMap[h] = str.split("");

            }
            st = new StringTokenizer(br.readLine());
            int commandNum = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            String[] commands = st.nextToken().split("");

            point = new int[2];
            direction = -1;
            for(int h = 0; h < H; h++){
                boolean find = false;
                for(int w = 0; w < W; w++)
                    if(Objects.equals(battleMap[h][w], "^")){
                        point[0] = w;
                        point[1] = h;
                        direction = 0;
                        find = true;
                    }else if(Objects.equals(battleMap[h][w], "v")){
                        point[0] = w;
                        point[1] = h;
                        direction = 1;
                        find = true;
                    }else if(Objects.equals(battleMap[h][w], "<")){
                        point[0] = w;
                        point[1] = h;
                        direction = 2;
                        find = true;
                    }else if(Objects.equals(battleMap[h][w], ">")){
                        point[0] = w;
                        point[1] = h;
                        direction = 3;
                        find = true;
                    }
                if(find) break;
            }

            for(String command : commands){
                CommandCenter(command);
            }

            System.out.print("#" + t + " ");
            for(int h = 0; h < H; h++){
                for(int w = 0; w <W; w++)
                    System.out.print(battleMap[h][w]);
                System.out.println();
            }


        }
    }
    private static void CommandCenter(String command){ // 지통실
        int x,y,dx,dy,nx,ny;
        switch(command){
            case "U":
                direction = 0;
                x = point[0];
                y = point[1];
                dx = directions[direction][0];
                dy = directions[direction][1];

                nx = x + dx;
                ny = y + dy;
                if((nx < 0 || nx >= W)||(ny < 0 || ny >= H)) {
                    battleMap[y][x] = "^";
                    break;
                }

                if (Objects.equals(battleMap[ny][nx], ".")){
                    battleMap[y][x] = ".";
                    battleMap[ny][nx] = "^";
                    point[0] = nx;
                    point[1] = ny;
                    break;
                }
                else if (Objects.equals(battleMap[ny][nx], "*") || Objects.equals(battleMap[ny][nx], "#") || Objects.equals(battleMap[ny][nx], "-")){
                    battleMap[y][x] = "^";
                    break;
                }

                break;
            case "D":
                direction = 1;
                x = point[0];
                y = point[1];
                dx = directions[direction][0];
                dy = directions[direction][1];

                nx = x + dx;
                ny = y + dy;
                if((nx < 0 || nx >= W)||(ny < 0 || ny >= H)) {
                    battleMap[y][x] = "v";
                    break;
                }

                if (Objects.equals(battleMap[ny][nx], ".")){
                    battleMap[y][x] = ".";
                    battleMap[ny][nx] = "v";
                    point[0] = nx;
                    point[1] = ny;
                    break;
                }
                else if (Objects.equals(battleMap[ny][nx], "*") || Objects.equals(battleMap[ny][nx], "#") || Objects.equals(battleMap[ny][nx], "-")){
                    battleMap[y][x] = "v";
                    break;
                }
                break;
            case "L":
                direction = 2;
                x = point[0];
                y = point[1];
                dx = directions[direction][0];
                dy = directions[direction][1];

                nx = x + dx;
                ny = y + dy;
                if((nx < 0 || nx >= W)||(ny < 0 || ny >= H)) {
                    battleMap[y][x] = "<";
                    break;
                }

                if (Objects.equals(battleMap[ny][nx], ".")){
                    battleMap[y][x] = ".";
                    battleMap[ny][nx] = "<";
                    point[0] = nx;
                    point[1] = ny;
                    break;
                }
                else if (Objects.equals(battleMap[ny][nx], "*") || Objects.equals(battleMap[ny][nx], "#") || Objects.equals(battleMap[ny][nx], "-")){
                    battleMap[y][x] = "<";
                    break;
                }
                break;
            case "R":
                direction = 3;
                x = point[0];
                y = point[1];
                dx = directions[direction][0];
                dy = directions[direction][1];

                nx = x + dx;
                ny = y + dy;
                if((nx < 0 || nx >= W)||(ny < 0 || ny >= H)) {
                    battleMap[y][x] = ">";
                    break;
                }

                if (Objects.equals(battleMap[ny][nx], ".")){
                    battleMap[y][x] = ".";
                    battleMap[ny][nx] = ">";
                    point[0] = nx;
                    point[1] = ny;
                    break;
                }
                else if (Objects.equals(battleMap[ny][nx], "*") || Objects.equals(battleMap[ny][nx], "#") || Objects.equals(battleMap[ny][nx], "-")){
                    battleMap[y][x] = ">";
                    break;
                }
                break;
            case "S":
                x = point[0];
                y = point[1];
                dx = directions[direction][0];
                dy = directions[direction][1];

                while(true){
                    nx = x + dx;
                    ny = y + dy;
                    if((nx < 0 || nx >= W)||(ny < 0 || ny >= H)) break;

                    if (Objects.equals(battleMap[ny][nx], "*")){
                        battleMap[ny][nx] = ".";
                        break;
                    }
                    else if (Objects.equals(battleMap[ny][nx], "#")){
                        break;
                    }
                    x = nx;
                    y = ny;
                }
                break;
        }
    }
}


//            for(int h = 0; h < H; h++)
//                System.out.println(Arrays.toString(battleMap[h]));
//            System.out.println(Arrays.toString(process));

/*
1
3 7
***....
*-..#**
#<.****
23
SURSSSSUSLSRSSSURRDSRDS
 */