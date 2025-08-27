package org.swea.최적경로;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

// N+2 <= 12 이유가 edge 연결하면서 12! 사용 4억 ~= 4초 (20초 이내)
public class Solution {
    private static int result;
    private static int N;
    private static Point[] point;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            result = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            visited = new boolean[N];
            point = new Point[N+2];
            st = new StringTokenizer(br.readLine());


            for(int i = 0; i < N + 2; i++){
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                if(i == 0) point[0] = new Point(x,y);
                else if(i == 1) point[N+1] = new Point(x,y);
                else point[i-1] = new Point(x,y);
            }

            List<Integer> list = new ArrayList<>();
            list.add(0);
            permutaion(0, list);
            System.out.println("#" + t + " " + result);

            }
        }

    private static void permutaion(int cnt, List<Integer> list){
        if(cnt == N){
            list.add(N+1);
            int distance = 0;
            for(int i = 0; i < list.size()-1; i++){
                int x1 = point[list.get(i)].x;
                int y1 = point[list.get(i)].y;
                int x2 = point[list.get(i+1)].x;
                int y2 = point[list.get(i+1)].y;
                distance += Math.abs(x1 - x2) + Math.abs(y1 - y2);
            }
            list.remove(Integer.valueOf(N+1));
            result = Math.min(result, distance);
        }
        for(int i = 1; i <= N; i++){
            if(visited[i-1]) continue;
            list.add(i);
            visited[i-1] = true;
            permutaion(cnt + 1, list);
            list.remove(Integer.valueOf(i));
            visited[i-1] = false;
        }
    }
}


