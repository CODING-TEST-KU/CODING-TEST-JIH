package org.example.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1446 {
    /*
    25/9/20 21:13 ~

    https://www.acmicpc.net/problem/1446

    지름길을 여러번 사용해서 이동하는 최소거리를 구하자

    다이나믹 프로그래밍 같음

    a에서 b까지 이동하는데 최소거리는
    a에서 c로 이동한 최소 거리 + c에서 b로 이동한 최소 거리임

    dp[i][j] =  min( for(k) { min(dp[i][j] + dp[k][j]) }, road[i][j]);


    근데 시간 복잡도를 계산해보니 N ^ 12이니 브루트포스도 가능할 듯!
    */
    private static int shortestDist = 20000;
    private static int N, D;
    private static Road[] road;

    public static void main(String[] args) throws IOException{
        solution1();
    }

    private static void solution1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        road = new Road[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            road[i] = new Road();
            road[i].start = Integer.parseInt(st.nextToken());
            road[i].end = Integer.parseInt(st.nextToken());
            road[i].dist = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(road, (a, b) -> a.start - b.start);

        findShortestDist(0, 0, 0);

        System.out.println(shortestDist);
    }

    private static void findShortestDist(int depth, int cur, int dist){
        if(depth >= N){
            if(cur < D){
                dist += D - cur;
            }
//            System.out.println("dist " + dist);
            shortestDist = Math.min(shortestDist, dist);
            return;
        }

        // 지름길을 타거나 안 탄다
        if(cur <= road[depth].start && road[depth].end <= D){
//            System.out.println("select road " + depth);
            findShortestDist(depth + 1, road[depth].end, dist + road[depth].start - cur + road[depth].dist);
        }
//        System.out.println("skip road " + depth);
        findShortestDist(depth + 1, cur, dist);
    }

    static class Road{
        int start;
        int end;
        int dist;
    }
}