package org.example.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Prob1238 {
    /*
    https://www.acmicpc.net/problem/1238

    25/8/9 22:24 ~ 23:22

    모든 정점에서 다른 정점으로 가는 최단 경를 구한다
    플로이드 워셜?
    N * N * N 시간이라서 시간 초과가 날지도 모름

    다익스트라를 이용?
    N * N * log N이 소요되니 시간 내에 풀 수 있을 듯

    트러블 슈팅
    - 메모리 초과 문제 발생
    - 알고보니 우선순위 큐의 comparator를 잘못 구현해서 최대 힙이 되었음...
    - 우선순위 큐 커스텀에 주의하자.
    */
    private static int N, M, X;
    private static List<Node>[] graph;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력 처리
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e, d));
        }

        // N개의 정점에 대해서 다익스트라 적용
        // N개의 정점에서 x를 거쳐 원래 정점으로 돌아오는 거리 중에서 최댓값을 구한다

        int[] dist = new int[N + 1];
        int INF = 20000000;
        int maxRoundTripDist = 0;
        for(int s = 1; s <= N; s++){
            Arrays.fill(dist, INF);
            dijkstra(s, dist);
            int distFromStart = dist[X];
            Arrays.fill(dist, INF);
            dijkstra(X, dist);
            maxRoundTripDist = Math.max(maxRoundTripDist, distFromStart+ dist[s]);
        }
        // 정답 출력
        System.out.println(maxRoundTripDist);
    }

    private static void dijkstra(int start, int[] dist){
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist); // 이 부분이 문제였음. 최소 힙 구조 우선순위 큐로 구현해야 함!
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(dist[cur.vertex] <= cur.dist){
                continue;
            }
            dist[cur.vertex] = cur.dist;

            for(Node next : graph[cur.vertex]){
                int nextDist = cur.dist + next.dist;
                if(nextDist < dist[next.vertex]){
                    pq.offer(new Node(next.vertex, nextDist));
                }
            }
        }
    }

    static class Node{
        public int vertex;

        public int dist;

        public Node(int next, int dist){
            this.vertex = next;
            this.dist = dist;
        }
    }
}