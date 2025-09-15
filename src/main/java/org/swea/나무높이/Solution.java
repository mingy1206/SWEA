import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int[] heights = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            int maxH = 0;
            for (int i = 0; i < N; i++) {
                heights[i] = Integer.parseInt(st.nextToken());
                if (heights[i] > maxH) {
                    maxH = heights[i];
                }
            }

            long totalOnes = 0;
            long totalTwos = 0;

            // 1. 모든 나무에 대해 필요한 +1, +2 성장 횟수의 총량을 계산
            for (int h : heights) {
                int diff = maxH - h;
                totalTwos += diff / 2;
                totalOnes += diff % 2;
            }

            // 2. 이분 탐색으로 최소 날짜 D를 효율적으로 탐색
            long low = 0;
            // N*120이 최대 성장량이므로, 2를 곱해 충분한 상한을 설정
            long high = (long)(100 * 120) * 2;
            long ans = high;

            while (low <= high) {
                long mid = (low + high) / 2;

                // 3. check 함수를 통해 mid일 안에 가능한지 판단
                if (check(mid, totalOnes, totalTwos)) {
                    ans = mid;      // 가능하다면 더 적은 날짜를 시도
                    high = mid - 1;
                } else {
                    low = mid + 1; // 불가능하다면 더 많은 날짜가 필요
                }
            }

            sb.append("#").append(test_case).append(" ").append(ans).append("\n");
        }
        System.out.print(sb.toString());
    }

    /**
     * D일 안에 모든 성장을 완료할 수 있는지 판별하는 모듈 (함수)
     * @param D 검사할 총 일수
     * @param ones 필요한 +1 성장 총 횟수
     * @param twos 필요한 +2 성장 총 횟수
     * @return 완료 가능 여부 (boolean)
     */
    public static boolean check(long D, long ones, long twos) {
        if (D < 0) return false;

        // D일 동안의 짝수/홀수 날짜 수
        long evenDays = D / 2;
        long oddDays = (D + 1) / 2;

        // 짝수 날에 +2 성장을 최대한 할당하고, 모자란 양을 계산
        long twosNeededOnOdd = Math.max(0, twos - evenDays);

        // 짝수 날이 부족해서 홀수 날에 처리해야 할 +2 성장은
        // 2배의 +1 성장으로 전환됨
        long totalOnesNeeded = ones + twosNeededOnOdd * 2;

        // 총 필요한 +1 성장 횟수를 홀수 날의 수로 감당할 수 있는지 확인
        return oddDays >= totalOnesNeeded;
    }
}