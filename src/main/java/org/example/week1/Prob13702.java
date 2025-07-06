package org.example.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Prob13702 {
    /*
    https://www.acmicpc.net/problem/13702

    25/7/6 12:33 ~

    각자 받을 수 있는 용량의 최댓값을 구해야함

    인단 x만큼 줄 수 있는지 판정할 수 있음

    파라매트릭 서치를 이용해서 x의 최댓값을 구할 수 있을까?
    x가 증가함에 따라서 어느 지점에서는 분배가 불가능해질 것이니 가능함

케이스

2 3
0
0
= 0

3 4
4
0
0
0
= 1

3 4
3
0
0
0
= 0?

1 1
2147483647
= 1
-> 이 케이스가 문제였음.

     */
    public static void main(String[] args) throws IOException{
        int N, K;
        int[] makgullis;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받는다
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        makgullis = new int[N];
        for(int i = 0; i < N; i++){
            makgullis[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(makgullis);

//        System.out.println(Integer.MAX_VALUE);

        // 파라매트릭 서치를 한다
        long lo = -1;
        long hi = (long)makgullis[N - 1] + 1; // 문제는 여기서 발생함
        while(lo + 1 < hi){
            long mid = (lo + hi) / 2;
            // 각 막걸리를 mid로 나눈 몫의 합이 K이상이면 lo = mid
            if(canDistribute(mid, makgullis, K)){
                lo = mid;
            }
            else{
                hi = mid;
            }
        }

        // lo를 출력한다.
        System.out.println(lo);
    }

    private static boolean canDistribute(long portion, int[] makgullis, int K){
        if(portion == 0) return true;
        int sumOfDrinkers = 0;
        for(int i = 0; i < makgullis.length; i++){
            sumOfDrinkers += makgullis[i] / portion;
            if(sumOfDrinkers >= K){
                return true;
            }
        }
        return false;
    }
}