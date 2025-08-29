package org.swea.미생물격리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 매 탐색에서 Map<Integer(index = y*N+x), List<class>>에 저장
public class Solution {
    private static int N,M,K;
    private static final int[][] directions = {{}, {0,-1}, {0,1}, {-1,0}, {1,0}};
    private static Map<Integer, List<microorganism>> microorganismProcess;
    private static int result;
    static class microorganism{
        int x;
        int y;
        int num;
        int direction; // 상 : 1, 하 : 2, 좌 : 3, 우 : 4
        public microorganism(int x, int y, int num, int direction){
            this.x = x;
            this.y = y;
            this.num = num;
            this.direction = direction;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            result = 0;
            microorganismProcess = new HashMap<>();

            for(int k = 0; k < K; k++){
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                int n = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int index = y*N+x;
                microorganism micro = new microorganism(x, y, n, d);
                List<microorganism> microorganismList = new ArrayList<>();
                microorganismList.add(micro);
                microorganismProcess.put(index, microorganismList);
            }

            for(int m = 0; m < M; m++){
                Map<Integer, List<microorganism>> tempMicroorganismProcess = new HashMap<>();
                for(List<microorganism> list  : microorganismProcess.values()){
                    // 어차피 합치는 병합 로직 때문에 첫번째 인자 아니면 존재하지 않음
                    microorganism micro = list.get(0);

                        int d = micro.direction;
                        int num = micro.num;
                        int nx = micro.x + directions[d][0];
                        int ny = micro.y + directions[d][1];
                        if(nx == 0 || nx == N-1|| ny == 0 || ny == N-1){
                            if(d == 1) d = 2;
                            else if(d == 2) d = 1;
                            else if(d == 3) d = 4;
                            else if(d == 4) d = 3;

                            num = micro.num/2;
                        }
                        if(num > 0){
                            int newtIndex = ny*N+nx;
                            List<microorganism> microorganismList = new ArrayList<>();

                            if(tempMicroorganismProcess.containsKey(newtIndex)){
                                microorganismList = tempMicroorganismProcess.get(newtIndex);
                                microorganismList.add(new microorganism(nx, ny, num, d));
                            }
                            else{
                                microorganismList.add(new microorganism(nx, ny, num, d));
                            }
                            tempMicroorganismProcess.put(newtIndex, microorganismList);
                    }
                }

                microorganismProcess = new HashMap<>();
                for(Map.Entry<Integer, List<microorganism>> entries : tempMicroorganismProcess.entrySet()){
                    List<microorganism> microorganismList = entries.getValue();
                    if(microorganismList.size() > 1){
                            int totalNum = 0, maxNum = 0, finalDir = 0;
                            int x = microorganismList.get(0).x;
                            int y = microorganismList.get(0).y;
                            for(microorganism micro : microorganismList){
                                totalNum += micro.num;
                                if(micro.num > maxNum){
                                    maxNum = micro.num;
                                    finalDir = micro.direction;
                            }
                        }
                        List<microorganism> tempMicroorganismList = new ArrayList<>();
                        tempMicroorganismList.add(new microorganism(x, y, totalNum, finalDir));
                        microorganismProcess.put(entries.getKey(), tempMicroorganismList);
                    }else {
                        microorganismProcess.put(entries.getKey(), microorganismList);
                    }

                }
            }

            for(List<microorganism> list : microorganismProcess.values()){
                    result += list.get(0).num;

            }
            System.out.println("#" + t + " " + result);
        }
    }
}
