import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int result = 1;
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)arr[i] = Integer.parseInt(st.nextToken());

        int front = 0;
        int back = 1;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(arr[0],1);
        for(int i = 1; i < N; i++){
            int cnt = map.getOrDefault(arr[i],0);
            if(cnt+1 > K){
                for(int j = front; j < back; j++){
                    if(arr[j] == arr[i]){
                        front = j+1;
                        break;
                    }else{
                        map.put(arr[j],map.get(arr[j])-1);
                    }
                }
                back++;
            }else {
                map.put(arr[i], cnt+1);
                back++;
            }
            result = Math.max(result, back-front);

        }

        System.out.println(result);
    }
}
