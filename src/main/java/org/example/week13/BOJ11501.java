package org.example.week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ11501 {
    /*
    25/10/26 21:25 ~ 21:49

    https://www.acmicpc.net/problem/11501

    N개의 로프를 이용해서 견딜 수 있는 중량의 최댓값을 구하자

    로프를 k개 사용하면 각 로프가 견딜 무게가 K분의 1로 줄어든다.

    4 20 30 10 20

    9 10 20 20 5 5 5 5 5 5

    A 로프가 버티는 무게가 제일 작다고 하면, A보다 더 많은 무게를 들 수 있는 것을 모두 사용해야 이 로프를 포함했을 때 최대의 무게임

    그런데 완전 탐색을 사용하면
    각 A 로프에 대해서, A 로프보다 긴 것을 찾으려면
    N * N의 시간이 걸림

    이진 탐색을 사용해서 A로프보다 긴 것을 찾는다면 시간이 좀 더 단축될 듯?

    
    왜이렇게 복잡하게 생각했는지 모르겠는데, 사실 로프가 몇번째로 작은지 말면 사용할 로프의 개수가 몇개인지 알 수 있음...
    */

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N;
        int[] rope;
        N = Integer.parseInt(br.readLine());
        rope = new int[N];
        for(int i = 0; i < N; i++){
            rope[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(rope);

        int maxWeight = 0;
        for(int i = 0; i < N; i++){
            int lo = -1;
            int hi = N;
            while(lo + 1 < hi){
                int mid = (lo + hi) / 2;
                if(rope[mid] >= rope[i]){
                    hi = mid;
                }
                else{
                    lo = mid;
                }
            }
            int numberOfRope = N - hi;
            int weight = numberOfRope * rope[i];
            if(weight > maxWeight){
                maxWeight = weight;
            }
        }

        System.out.println(maxWeight);
    }
}