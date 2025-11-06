package org.baekjoon.니가싫어싫어너무싫어싫어오지마게찝쩍대지마20440;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] TE = new int[N];
        int[] TX = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            TE[i] = Integer.parseInt(st.nextToken());
            TX[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(TE);
        Arrays.sort(TX);

        int max_e = 0, max_x = 0;
        int max_count = 0;
        int count = 1;
        int index_e = 0, index_x = 0;
        int e = TE[index_e], x = TE[index_e];

        while (index_e < N && index_x < N) {

            if (index_e + 1 < N) {
                // 모기 입장
                if (TE[index_e + 1] < TX[index_x]) {
                    x = TE[index_e + 1];
                    e = TE[index_e + 1];
                    count++;
                    index_e++;
                }
                // 모기 퇴장
                else if (TE[index_e + 1] > TX[index_x]) {
                    x = TX[index_x];
                    if (count > max_count) {
                        max_e = e;
                        max_x = x;
                        max_count = count;
                    }

                    index_x++;
                    count--;
                }
                // 입장과 동시에 퇴장
                else if (TE[index_e + 1] == TX[index_x]) {
                    index_x++;
                    index_e++;

                }
            } else { // 마지막 입장 모기 (index_e + 1 >= N)
                x = TX[index_x];
                if (count > max_count) {
                    max_e = e;
                    max_x = x;
                    max_count = count;
                }
                index_x++;
                count--;
            }
        }

        System.out.println(max_count);
        System.out.println(max_e + " " + max_x);
    }
}
