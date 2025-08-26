package org.swea.최적경로;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

// N+2 <= 12 이유가 edge 연결하면서 12! 사용 4억 ~= 4초 (20초 이내)
public class Solution {
    static class Edge implements Comparable<Edge>{
        int from, to, weigh;
        public Edge(int from, int to, int weigh){
            this.from = from;
            this.to = to;
            this.weigh = weigh;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weigh, o.weigh);
        }


    }
    private static int N, E;
    private static TreeSet<Edge> edgeList;
    private static int[] parent;
    private static Point[] point;
    private static void make(){
        for (int i = 0; i < N; i++){
            parent[i] = i;
        }
    }
    private static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]); // path 압축
    }
    private static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;

        parent[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            N = Integer.parseInt(br.readLine());

            parent = new int[N+2];
            edgeList = new TreeSet<>();
            point = new Point[N+2];
            st = new StringTokenizer(br.readLine());


            for(int i = 0; i < N + 2; i++){
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                if(i == 0) point[0] = new Point(x,y);
                if(i == 1) point[N+1] = new Point(x,y);
                else point[i-1] = new Point(x,y);
            }

            for(int i = 0; i < N + 1; i++){
                int x1 = point[i].x;
                int y1 = point[i].y;
                for(int j = i + 1; j < N + 2; j++){
                    int x2 = point[j].x;
                    int y2 = point[j].y;
                    int from = i;
                    int to = j;
                    int weight = Math.abs(x1-x2) + Math.abs(y1-y2);
                    edgeList.add(new Edge(from,to,weight));
                }
            }
            make();
            int result = 0;
            int cnt = 0;
            for(Edge edge : edgeList){
                if(!union(edge.from, edge.to)) continue;
                result += edge.weigh;
                if(++cnt == N-1) break;
            }
            System.out.println("#" + t + " " + result);
        }
    }

}
