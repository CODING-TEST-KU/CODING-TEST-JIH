package org.example.week24;
import java.io.*;
import java.util.*;

public class 백준_11779 {
    /*
    https://www.acmicpc.net/problem/11779

    26/1/18 14:55 ~ 15:10 15:17 ~ 15:30

    a도시에서 b도시까지 가는데 드는 최소비용,
    경로에 포함된 도시 개수,
    도시 방문 순서를 구하자

    다익스트라로 최소비용을 구할 수 있음
    엣지에 비용을 저장

    다익스트라에서 최단 경로를 저장하는 방법은
    현재 노드에 메시지를 보낸 이전 노드를 기억해두어서 역추적?
    */
    private int N, M;
    private List<int[]>[] graph;
    private int start, end;
    private int[] dists;
    private int[] prevs;

    public static void main(String[] args) throws IOException {
        백준_11779 main = new 백준_11779();
        main.solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력 처리
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new int[] { e, w });
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        // 다익스트라
        dijkstra(start);

        // 정답 출력
//        for(int dist : dists){
//            System.out.print(dist + " ");
//        }

        System.out.println(dists[end]);
        Stack<Integer> route = findRoute(end);
        System.out.println(route.size());
        while(!route.isEmpty()){
            System.out.print(route.pop() + " ");
        }
    }

    private void dijkstra(int start){
        prevs = new int[N + 1];
        dists = new int[N + 1];
        Arrays.fill(dists, 2000_000_000);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        pq.offer(new int[]{ start, 0 , -1});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();

            if(dists[cur[0]] < cur[1]){
                continue;
            }
            dists[cur[0]] = cur[1];
            prevs[cur[0]] = cur[2];

            for(int[] next : graph[cur[0]]){
                int nextDist = cur[1] + next[1];
                if(nextDist < dists[next[0]]){
                    pq.add(new int[] { next[0], nextDist, cur[0]});
                }
            }
        }
    }

    private Stack<Integer> findRoute(int end){
        Stack<Integer> route = new Stack<>();
        int i = end;
        route.push(end);
        while(prevs[i] != -1){
            route.push(prevs[i]);
            i = prevs[i];
        }
        return route;
    }
}