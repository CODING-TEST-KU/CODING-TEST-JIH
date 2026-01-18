package org.example.week23;

import java.io.*;

public class 백준_15988 {
    /*
    https://www.acmicpc.net/problem/15988

    26/1/12 18:43 ~ 19:57

    f(n)을 1,000,000,009으로 나눈 나머지를 구하자

    f(n) = f(n - 1) + f(n - 2) + f(n - 3)

    숫자의 순서만 다르면 됨
    */
    public static void main(String[] args) throws IOException {
        백준_15988 main = new 백준_15988();
        main.solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T;
        T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            int n = Integer.parseInt(br.readLine());

            if(n == 1) {
                System.out.println(1);
                continue;
            }
            else if(n == 2){
                System.out.println(2);
                continue;
            }
            long [] dp = new long[n + 1];
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;
            for(int i = 4; i <= n; i++){
                dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1_000_000_009;
            }
            System.out.println(dp[n]);
        }
    }
}