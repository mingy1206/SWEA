import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] heights = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            int maxH = 0;
            for (int i = 0; i < N; i++) {
                heights[i] = Integer.parseInt(st.nextToken());
                maxH = Math.max(maxH, heights[i]);
            }

            long totalOnes = 0;
            long totalTwos = 0;

            for (int h : heights) {
                int diff = maxH - h;
                totalTwos += diff / 2;
                totalOnes += diff % 2;
            }

            long low = 0;
            long high = 400000000L;
            long ans = high;

            while (low <= high) {
                long mid = (low + high) / 2;

                if (check(mid, totalOnes, totalTwos)) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            System.out.println("#" +  t  +" " + ans);
        }
    }

    public static boolean check(long D, long ones, long twos) {
        if (D < 0) return false;

        long evenDays = D / 2;
        long oddDays = (D + 1) / 2;

        long remainingTwos = Math.max(0, twos - evenDays);

        long totalOnesNeeded = ones + remainingTwos * 2;

        return oddDays >= totalOnesNeeded;
    }
}