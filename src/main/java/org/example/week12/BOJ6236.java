package org.example.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ6236 {
    /*
    25/9/30 17:14 ~ 17:45

    https://www.acmicpc.net/problem/6236

    N개의 날이 있는데 K원씩 M번만 인출을 할 수 있다
    연속된 날에는 K원을 넘어서 사용할 수 없다.
    이때 K원 최소 금액을 구해야 한다

5 3
200
100
300
200
100
    */
    private static int N, M;
    private static int[] money;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        money = new int[N];
        int maxMoney = 0;
        int sum = 0;
        for(int i = 0; i < N; i++){
            money[i] = Integer.parseInt(br.readLine());
            maxMoney = Math.max(maxMoney, money[i]);
            sum += money[i];
        }

        int lo = maxMoney - 1;
        int hi = sum + 1;
        while(lo + 1 < hi){
            int mid = (lo + hi) / 2;
            if(isPossible(mid)){
                hi = mid;
            }
            else{
                lo = mid;
            }
        }
        System.out.println(hi);
    }

    private static boolean isPossible(int k){
        // k원씩 m번 나누어주기가 가능한가?
        int group = 0;
        int amount = 0;
        for(int i = 0; i < N; i++){
            amount += money[i];
            if(amount > k){
                group++;
                amount = money[i];
            }
        }
        if(amount > 0){
            group++;
        }
        return group <= M;
    }
}