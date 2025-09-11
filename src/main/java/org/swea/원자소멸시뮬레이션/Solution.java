package org.swea.원자소멸시뮬레이션;

import java.io.*;
import java.util.*;

// 가로나 세로 거리 중 더 거리가 멀리 떨어져 있는 횟수 만큼 반복
public class Solution {
    private static final int[][] directions = {{0,1},{0,-1},{-1,0},{1,0}};//상하좌우 (dx,dy)로 저장

    static class Atom{
        private int id, x, y, dir, energy;
        boolean isAlive = true;

        Atom(int id, int x, int y, int dir, int energy){
            this.id = id; this.x = x; this.y = y; this.dir = dir; this.energy = energy;
        }

    }
    static class Event implements  Comparable<Event>{
        int time;
        int atom1, atom2;
        Event(int time, int atom1, int atom2){
            this.time = time; this.atom1 =atom1; this.atom2 = atom2;
        }
        @Override
        public int compareTo(Event e){
            return this.time - e.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T= Integer.parseInt(br.readLine());

        for(int t = 1; t<= T; t++){
            int result = 0;
            int N = Integer.parseInt(br.readLine());
            Atom[] atoms = new Atom[N];
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                atoms[i] = new Atom(new Point(Integer.parseInt(st.nextToken())*2,Integer.parseInt(st.nextToken())*2),
                        Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            PriorityQueue<Event> pq = new PriorityQueue<>();

            int maxLength = 0;
            for (int i = 0; i < N; i++){
                Point now  = atomics[i].point;
                for(int j = i+1; j < N; j++){
                    Point next = atomics[j].point;
                    int length = Math.max(Math.abs(now.x - next.x), Math.abs(now.y - next.y));
                    maxLength = Math.max(maxLength, length);
                }

            }

            for (int i = 0; i<maxLength; i++){
                Map<Point, List<Integer>> pointMap = new HashMap<>();
                for (int j = 0; j < N; j++){
                    Atomic atomic = atomics[j];
                    if(!atomic.exist) continue;
                    int[] direction = directions[atomic.direction];
                    atomics[j].setPoint(atomic.point.x + direction[0], atomic.point.y + direction[1]);


                    List<Integer> list;
                    if(pointMap.containsKey(atomics[j].point)){
                        list = pointMap.get(atomics[j].point);
                        list.add(j);
                    }
                    else{
                        list = new ArrayList<>();
                        list.add(j);
                    }
                    pointMap.put(atomics[j].point, list);
                }

                for(List<Integer> list : pointMap.values()){
                    if(list.size() <= 1) continue;

                    for(int index : list){
                        Atomic atomic = atomics[index];
                        result += atomic.energe;
                        atomics[index].setExist(false);
                    }
                }

            }
            System.out.println("#" + t + " " + result);

        }

    }
}
/*
2
4
-1000 0 3 5
1000 0 2 3
0 1000 1 7
0 -1000 0 9
4
-1 1 3 3
0 1 1 1
0 0 2 2
-1 0 0 9
 */