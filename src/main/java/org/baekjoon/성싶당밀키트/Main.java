package org.baekjoon.성싶당밀키트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 음 이거 시간복잡도가 어떻게 될지 추측이 어려움
// 2십만번을 매번 구해서 전체 합이랑 중요하지 않은 재료를 우선순위 큐 or 누적합으로 풀 수 있을 것 같은데
// 누적합은 시간 초과 될거 같으니 우선순위 큐가 맞는 것 같ㄴ은데 뭔가 얘도 2십만번씩 계속 연산하면 초과될 것 같기도 하고;;
// 누적합 우선순위는 어떨까? 음.. 잘하면 같이 쓸 수 있을 것 같은데 아니다...
// 역시나 터지네 2십만번은
// 이분탐색이 답이구나
//public class Main {
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int N = Integer.parseInt(st.nextToken());
//        int G = Integer.parseInt(st.nextToken());
//        int K = Integer.parseInt(st.nextToken());
//        int[] S = new int[N];
//        int[] L = new int[N];
//        int[] O = new int[N];
//
//        for(int i = 0; i < N; i++){
//            st = new StringTokenizer(br.readLine());
//            S[i] = Integer.parseInt(st.nextToken());
//            L[i] = Integer.parseInt(st.nextToken());
//            O[i] = Integer.parseInt(st.nextToken());
//        }
//        int day = 1;
//        while(true){
//            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
//            int Germs = 0;
//            for(int i = 0; i < N; i++){
//                int subGerms= S[i]*Math.max(1,day-L[i]);
//                Germs += subGerms;
//                if(O[i] > 0) pq.add(subGerms);
//            }
//            if(Germs <= G){
//                day++;
//                continue;
//            }
//            boolean flag = false;
//            for (int i = 0; i<K; i++){
//                if(pq.size() == 0){
//                    flag = true;
//                    break;
//                }
//
//                Germs -= pq.poll();
//                if(Germs <= G){
//                    day++;
//                }
//            }
//            if(flag) break;
//        }
//
//        System.out.println(day);
//    }
//}

public class Main {

    static int N, K;
    static long G;
    static long[] S, L;
    static int[] O;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        G = Long.parseLong(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        S = new long[N];
        L = new long[N];
        O = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            S[i] = Long.parseLong(st.nextToken());
            L[i] = Long.parseLong(st.nextToken());
            O[i] = Integer.parseInt(st.nextToken());
        }

        long left = 1;
        long right = 2_000_000_001L;
        long ans = 0;

        while (left <= right) {
            long midDay = (left + right) / 2;

            if (check(midDay)) {
                ans = midDay;
                left = midDay + 1;
            } else {
                right = midDay - 1;
            }
        }

        System.out.println(ans);
    }
    static boolean check(long day) {
        long totalGerms = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            long germs = S[i] * Math.max(1, day - L[i]);
            totalGerms += germs;

            if (O[i] == 1) {
                pq.add(germs);
            }
        }

        if (totalGerms <= G) {
            return true;
        }

        int removeCount = 0;
        while (removeCount < K && !pq.isEmpty()) {
            totalGerms -= pq.poll();
            removeCount++;

            if (totalGerms <= G) {
                return true;
            }
        }

        return (totalGerms <= G);
    }
}
