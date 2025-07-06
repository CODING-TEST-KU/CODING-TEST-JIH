package org.example.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob2805 {
    /*
    https://www.acmicpc.net/problem/2805

    25/07/01 22:03 ~ 22:37

    문제 해결의 핵심 : 이분탐색 아이디어
    문제 해결에 어려웠던 점 : 최댓값을 찾아야 하는데 조건을 잘못 설정했음. 오버플로우도 조심해야할 것 같음
     */

    public static void main(String[] args) throws IOException{
        // 입력 처리
        int N, M;
        int[] trees;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        line = br.readLine();
        st = new StringTokenizer(line);
        trees = new int[N];
        int maxHeight = 0;
        for(int i = 0; i < N; i++){
            trees[i] = Integer.parseInt(st.nextToken());
            if(trees[i] > maxHeight){
                maxHeight = trees[i];
            }
        }

        // hi = 가장 긴 높이 와 lo = 0라고 하자
        int hi = maxHeight + 1;
        int lo = -1;
        while(lo + 1 < hi){
            int mid = (lo + hi) / 2;
            // mid에서 절단했을 때 얻을 수 있는 gain을 구한다
            // gain이 required보다 크거나 같으면 lo = mid
            if(sumOfLogs(trees, mid) >= M){
                lo = mid;
            }
            // 그렇지 않으면 hi = mid
            else{
                hi = mid;
            }
        }
        // 마지막에 얻은 lo가 답
        System.out.println(lo);

    }

    private static long sumOfLogs(int[] trees, int cuttingHeight){
        long sum = 0;
        for(int height : trees){
            if(height > cuttingHeight){
                sum += height - cuttingHeight;
            }
        }
        return sum;
    }
}