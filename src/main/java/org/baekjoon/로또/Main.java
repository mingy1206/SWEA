    package org.baekjoon.로또;

    import java.io.*;
    import java.util.*;

    public class Main {
        static int k;
        static int[] arr;
        static int[] result = new int[6];
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            while (true){
                String[] str = br.readLine().split(" ");
                k = Integer.parseInt(str[0]);
                arr = new int[k];
                if(k==0) break;
                for(int i = 1; i <= k; i++) arr[i-1] = Integer.parseInt(str[i]);

                System.out.println();
                lotto(0,0   );
            }

        }

        static void lotto(int depth, int cnt){
            if(cnt >= 6){
                for(int i = 0; i < 6; i++) {
                    if(i<5) System.out.print(result[i]+" ");
                    else System.out.println(result[i]);
                }
                return;
            }
            for(int i = depth; i < k; i++){
                result[cnt] = arr[i];
                lotto(i+1, cnt+1);
            }
        }
    }
