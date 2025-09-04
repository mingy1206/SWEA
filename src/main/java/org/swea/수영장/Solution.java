package org.swea.수영장;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            int[] prices = new int[4]; // 이용권 가격들
            int[] planes = new int[13]; //각 달의 이용 계획

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) prices[i] = Integer.parseInt(st.nextToken());
            // 1일 / 1달 / 3달 / 1년
            // 3달 이용권으로 인해서 greedy하게 볼 수 없고 여러 상황을 고려해야 함
            // 1일 -> 1달 가능 / 1달 -> 1년 가능 => 1일권 or 1달 vs 1년
            // 1일권은 이번달 안에서만 결정이 가능 / 1년권은 1년 전체를 보고 한번에 결정 가능
            // but 3달권은 현재 다음 다다음을 고려해야함
            // greedy의 경우에는 1년 혹은 1달만 고려해서 값을 바로 구할 수 있어야 함
            // 그래서 3달 단위로 묶어서 계속 확인을 해줘야 함. 단순히 greedy하게 값이 나올 수가 없음.

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 12; i++) planes[i] = Integer.parseInt(st.nextToken());
            int[] dp = new int[13];


            for(int i = 1; i <= 12; i++){
                // 1달 단위 비교 1일권 vs 1달권
                int minValue = Math.min(planes[i-1]*prices[0], prices[1]);
                dp[i] = dp[i-1] + minValue; // 이용 가격 누적합

                //이전 3달에 비해서 3달권이 메리트가 있는지
                if (i >=3) dp[i] = Math.min(dp[i], dp[i-3] + prices[2]);
            }
            // 1일권, 1달권, 3달권을 조합한 최적의 비용 vs 걍 1년 비용
            int result = Math.min(dp[12], prices[3]);
            System.out.println("#" + t +" " + result);
        }

    }

}
/*
2
10 40 100 300
0 0 2 9 1 5 0 0 0 0 0 0
10 100 50 300
0 0 0 0 0 0 0 0 6 2 7 8
 */