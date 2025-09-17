package org.example.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Prob9184 {
    /*
    25/9/12 14:13 ~ 
    https://www.acmicpc.net/problem/9184

    겹치는 부분 문제가 존재함
    최적 부분 구조도 성립함

    다이나믹 프로그래밍이구나 캐치 가능
    */
    private static int[][][] dp = new int[21][21][21];

    public static void main(String[] args) throws IOException{
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 각 입력에 대해서 다이나믹 프로그래밍으로 값 계산
        for(int a = 0; a <= 20; a++){
            for(int b = 0; b <= 20; b++){
                Arrays.fill(dp[a][b], -1);
            }
        }

        while(true){
            String line = br.readLine();
            if(line.equals("-1 -1 -1")){
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            sb.append("w(" + a + ", " + b + ", " + c + ") = " + recursive(a, b, c) + "\n");
        }

        System.out.println(sb.toString());
    }

    private static int recursive(int a, int b, int c){
        if(a <= 0 || b <= 0 || c <= 0){
            return 1;
        }
        if(a > 20 || b > 20 || c > 20){
            return recursive(20, 20, 20);
        }
        int ret = dp[a][b][c];
        if(ret != -1){
            return ret;
        }
        if(a < b && b < c){
            return dp[a][b][c] = recursive(a, b, c-1) + recursive(a, b-1, c-1) - recursive(a, b-1, c);
        }
        return dp[a][b][c] = recursive(a-1, b, c) + recursive(a-1, b-1, c) + recursive(a-1, b, c-1) - recursive(a-1, b-1, c-1);
    }
}