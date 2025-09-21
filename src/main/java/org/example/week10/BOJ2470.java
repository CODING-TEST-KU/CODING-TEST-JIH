package org.example.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2470 {
    /*
    25/9/17 12:55 ~

    https://www.acmicpc.net/problem/2470

    완전탐색은 N * N이라 시간 초과 가능성이 높음

    투 포인터를 이용하여서 합이 0에 가까운 두 용액을 찾을 수 있지 않을까?

-99 -2 -1 4 98

6
-99 -2 2 4 97 200

2
100 200

3
-99 -2 -1

    최댓값과 최슷값이 같은 부호이면 0에 가장 가까운 두 수를 선택한다
    다른 부호라면 둘 중 left와 right 중에서 절댓값이 더 큰 걸 가리키는 쪽을 이동시킨다
    */

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        int liquidSum = Integer.MAX_VALUE;
        int left = 0;
        int right = N - 1;
        int answerLeft = 0;
        int answerRight = N - 1;
        if(A[left] >= 0 && A[right] >= 0) {
            System.out.println(A[left] + " " + A[left + 1]);
            return;
        }
        if(A[left] <= 0 && A[right] <= 0){
            System.out.println(A[right - 1] + " " + A[right]);
            return;
        }
        while(left < right){
            if(Math.abs(A[left] + A[right]) < Math.abs(liquidSum)){
                liquidSum = A[left] + A[right];
                answerLeft = left;
                answerRight = right;
            }
            if(Math.abs(A[left]) > Math.abs(A[right])){
                left++;
            }
            else{
                right--;
            }
        }
        System.out.println(A[answerLeft] + " " + A[answerRight]);
    }
}