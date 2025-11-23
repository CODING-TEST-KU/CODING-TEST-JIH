package org.example.week17;

import java.util.*;

class 프로그래머스_2025코드챌린지_완전범죄 {
    /*
    11/21 15:10 ~ 
    
    모든 물건을 훔쳤을 때 A가 남긴 흔적의 최솟값을 구하자
    
    info의 길이 <= 40
    백트래킹을 쓰면 괜찮으려나? 시간초과가 발생함
    
    dp를 쓸 수 있을까?

예시
info = 
1 1   1 0, 0 1
1 1   1 1, 0 2
1 1   1 2, 0 3
1 1   2 2, 2 3
1 1   3 2, 3 3

n, m = 4, 3

정답은 3

dp[i][j] : i번째 물건에서 B의 누적흔적이 j일 때 A의 최소 누적흔적수

dp[0][0] = A[0]
dp[0][1] = 0

dp[i][j + B[i]] = dp[i - 1][j] if (j + B[i] < m)
dp[i][j] = dp[i - 1][j] if(dp[i - 1][j] + A[i] < n)

1 2  1 0, 0 2
2 3  3 0, 2 5, 1 3, 0 5
2 1  5 0, 4 5, 
    */
    private int answer = 140;
    
    public int solution(int[][] info, int n, int m) {   
        int[][] dp = new int[info.length][m];
        
        for(int i = 0; i < dp.length; i++)
            Arrays.fill(dp[i], 140);
        
        dp[0][0] = info[0][0];
        if(info[0][1] < m){
            dp[0][info[0][1]] = 0;
        }
            
        for(int i = 1; i < dp.length; i++){
            for(int j = 0; j < m; j++){
                if(j - info[i][1] >= 0 && dp[i - 1][j - info[i][1]] < n) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - info[i][1]]);  
                }
                if(dp[i - 1][j] + info[i][0] < n){
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + info[i][0]);
                }
            }
        }
        
        // for(int i = 0; i < dp.length; i++)
        //     System.out.println(Arrays.toString(dp[i]));
        
        int ans = 140;
        for(int i = 0; i < m; i++){
            if(dp[dp.length - 1][i] < ans){
                ans = dp[info.length - 1][i];
            }
        }
        if(ans == 140){
            return -1;
        }
        return ans;
    }
}