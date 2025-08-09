package org.example.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Prob14002 {
    /*
    https://www.acmicpc.net/problem/14002

    25/8/10 00:26 ~ 01:09

    N = 1000이니 N*N 복잡도도 가능함
    dp[i][0] : i번째 수를 마지막으로 갖는 부분 증가 수열의 길이
    dp[i][1] : i번째 수를 마지막으로 갖는 부분 증가 수열의 이전 원소 인덱스

    5
10 20 30 40 50

5
10 10 10 10 10

5
50 40 30 20 10

5
50 10 30 20 30
    */

    public static void main(String[] args) throws IOException{
        int N;
        int[] A;
        int[][] dp;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        N = Integer.parseInt(br.readLine());
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        A = new int[N];
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        // lis 계산
        int maxLength = 1;
        int maxIndex = 0;
        dp = new int[2][N];
        dp[0][0] = 1;
        Arrays.fill(dp[1], -1);
        for(int i = 1; i < N; i++){
            dp[0][i] = 1;
            for(int j = 0; j < i; j++){
                if(A[j] < A[i] && dp[0][i] < dp[0][j] + 1){
                    dp[0][i] = dp[0][j] + 1;
                    dp[1][i] = j;
                }
            }
            if(maxLength < dp[0][i]){
                maxLength = dp[0][i];
                maxIndex = i;
            }
        }

        // 출력
        System.out.println(maxLength);
        Stack<Integer> stack = new Stack<>();
        int index = maxIndex;
        while(index != -1){
            stack.push(A[index]);
            index = dp[1][index];
        }
        while(!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
    }
}