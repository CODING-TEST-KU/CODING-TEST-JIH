package org.example.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ1541 {
    /*
    25/10/1 13:14 ~ 13:24
    14:02 ~ 14:26

    https://www.acmicpc.net/problem/1541

    식은 더하기와 빼기로 이루어져 있고 계산 순서를 바꾸어서 식의 값을 최소로 만들어야 한다

    모든 연산은 더하기로 볼 수 있다.
    두 피연산자가 최소라면 더하기도 최소이다.

    따라서 dp를 이용할 수 있다.
    dp[i][j] : i번부터 j번 수까지 더할 때
    dp[i][j] = for (i <= k <= j) min(dp[i][k] + dp[k + 1][j]])

    접근을 잘못했다..

    괄호를 아무데나 칠 수 있어서 마이너스가 괄호 앞에 붙을 수 있다.

    나는 연산자를 바꿀 수 있게 바꾸어서 해결했다.

    근데 사실 이 문제는 수학적 센스를 이용한 그리디 접근만으로도 풀 수 있다!
    */

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();
        int headOfNum = 0;
        List<Integer> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        for(int i = 0; i < exp.length(); i++){
            if(exp.charAt(i) == '-' || exp.charAt(i) == '+'){
                nums.add(Integer.parseInt(exp.substring(headOfNum, i)));
                ops.add(exp.charAt(i));
                headOfNum = i + 1;
            }
        }
        nums.add(Integer.parseInt(exp.substring(headOfNum, exp.length())));

//        for(int i = 0; i < nums.size(); i++){
//            System.out.println(nums.get(i) + " ");
//        }

        int[][] dp = new int[nums.size()][nums.size()];
        final int INF = 999999;
        for(int i = 0; i < nums.size(); i++){
            for(int j = 0; j < nums.size(); j++){
                dp[i][j] = INF;
            }
        }
        for(int i = 0; i < nums.size(); i++){
            dp[i][i] = nums.get(i);
        }
        for(int length = 2; length <= nums.size(); length++){
            for(int i = 0; i + length <= nums.size(); i++){
                int j = i + (length - 1);
                for(int k = i; k + 1 <= j; k++){
                    if(ops.get(i) == '-'){
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] - dp[k + 1][j]);
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }
            }
        }

        System.out.println(dp[0][nums.size() - 1]);
    }
}