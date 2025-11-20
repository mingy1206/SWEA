package org.baekjoon.좌표정렬하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main   {
    public static class Point implements Comparable<Point>{
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Point point){
            if(this.x == point.x) return this.y - point.y;
            else return this.x - point.x;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Point[] points = new Point[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            points[i] = new Point(Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(points);
        for(int i = 0; i < N; i++){
            int x = points[i].x;
            int y = points[i].y;
            System.out.println(x + " " + y);
        }
    }

}
