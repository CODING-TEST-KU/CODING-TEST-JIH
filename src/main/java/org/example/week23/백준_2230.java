package org.example.week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_2230 {
    /*
    https://www.acmicpc.net/problem/2230

    26/1/12 19:36 ~ 20:11

    수열에서 고른 두 수의 차이가 M이상이면서 최소가 되도록 할 때 차이를 구하자

    M = 3
    1 5 3

    1 3 5

    N이 최대 10만이니 브루트 포스는 어려움

    정렬을 수행한 후에 투포인터르 이용해서 차이가 M이상이면서 최소가 되게 만들 수 있을까?

    1 3 5 6 7 10
    */
    public static void main(String[] args) throws IOException {
        백준_2230 main = new 백준_2230();
        main.solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(A);

        int answer = A[N - 1] - A[0];
        for(int i = 0; i < N; i++){
            int lo = i;
            int hi = N;
            while(lo + 1 < hi){
                int mid = (lo + hi) / 2;
                if(A[mid] - A[i] >= M){
                    hi = mid;
                }
                else{
                    lo = mid;
                }
            }
            if(hi == i || hi == N){
                continue;
            }
            answer = Math.min(answer, A[hi] - A[i]);
        }
        System.out.println(answer);
    }
}