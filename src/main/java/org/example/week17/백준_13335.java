package org.example.week17;

import java.io.*;
import java.util.*;

public class 백준_13335 {
    /*
    11:23 18:43 ~ 19:11

    모든 트럭이 건널 때까지 걸리는 시간을 구하자
    트럭이 들어오고 나가는 모습이 큐와 닮았다

    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        int N, W, L;
        int[] truck;

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        truck = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            truck[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Integer> qu = new LinkedList<>();
        for(int i = 0; i < W; i++){
            qu.offer(0);
        }

        int answer = 0;
        int totalWeight = 0;
        int in = 0;
        while(!qu.isEmpty()){
            int out = qu.poll();
            totalWeight -= out;
            if(in < N) {
                if (totalWeight + truck[in] <= L) {
                    totalWeight += truck[in];
                    qu.offer(truck[in]);
                    in++;
                } else {
                    qu.offer(0);
                }
            }
            answer++;
        }

        System.out.println(answer);
    }
}