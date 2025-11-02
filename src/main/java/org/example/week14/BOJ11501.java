package org.example.week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11501 {
    /*
    25/10/29 11:45 ~ 12:30, 13:20 ~ 13:40

    https://www.acmicpc.net/problem/11501

    최대 이익을 구하자
    이익 = 판매 가격 - 구매 비용

    완전 탐색은 어려우니 더 빠른 방법 필요

    주식은 오르거나 내리거나 그대로다
    주식이 오를 때는 사야한다
    주식이 내릴 때는 안 사는 게 나은데, 나중에 주식 고점이 높을 때는 사는 게 맞다
    그대로면 사거나 가만히 있거나 똑같다

    10 7 6 -> 주가가 내려가니 사면 안됨, 이익 = 0
    3 5 9 -> 주가가 계속 오르니 첫 두날에 사야함, 이익 = 9*2 - (3 + 5) =10
    1 1 3 1 2 -> 1 1 3에서 주가가 오른다, 1 2에서 주가가 오른다. 뒤에 고점이 앞에 고점보다 크지 않다. 이익 = 3*2 - (1 + 1) + 2 - 1 = 5

    1 1 3 2 1 -> 1 1 3에서 사다가 판다, 이익 = 3*2 - (1 + 1) = 4

    1 1 3 1 4

    주가를 막대 그래프로 나타낼 때 각 막대에서 왼쪽으로 빔을 쏜다는 느낌.
    빔 아래에 있으면 팔고 빔을 쏜 곳에서 팔아야 한다

    더 높은 것에 가로막히느냐 아니냐를 판별해야 함

    뒤에서부터 배열을 탐색하면 어떨까?

    뒤에서부터 배열을 탐색
    맨 뒤 주가를 빈 배열에 넣는다
    배열의 첫 원소보다 오늘 주가가 낮으면 넣는다
    배열의 첫 원소보다 오늘 주가가 높으면 배열에 있는 걸 모두 빼서 중간 이익을 계산한다

    그런데 틀렸다...
    사실 큐를 사용할 이유가 없다
    최고가가 무엇인지, 현재까지 최고가 아래의 주식을 팔아서 얻은 이익만 알고 있으면 된다.

    */

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T, N;
        int[] stock;
        T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            N = Integer.parseInt(br.readLine());
            stock = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i =0; i < N; i++){
                stock[i] = Integer.parseInt(st.nextToken());
            }

            long maxProfit = 0;
            int maxStock = stock[N - 1];
            for(int i = N - 2; i >= 0; i--){
                if(maxStock < stock[i]){
                    maxStock = stock[i];
                    continue;
                }
                maxProfit += maxStock - stock[i];
            }

            System.out.println(maxProfit);
        }
    }
}