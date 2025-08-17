package org.example.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Prob4803 {
    /*
    https://www.acmicpc.net/problem/4803

    25/8/12 22:04 ~ 23:13 

    연결 요소를 모두 찾고 각 연결요소가 사이클을 형성하는지 확인하면 됨
    dfs로 확인해보자.
    dfs로 감지하는 방법을 잘못 알고 있었음.
    유니온파인드로 푸는 것이 나았음

2 1
1 2
0 0
Case 1: No trees.

500 1
1 2
0 0
Case 1: A forest of 3 trees.

    */
    private static List<Integer>[] graph;
    private static boolean cycleDetected;
    public static void main(String[] args) throws IOException{
        int N, M;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 각 테스트 케이스에 대해서
        StringBuilder sb = new StringBuilder();
        int caseNo = 1;
        while(true){
            // 입력 처리한다.
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if(N == 0 && M == 0){
                break;
            }

            graph = new ArrayList[N + 1];
            for(int i = 1; i <= N; i++){
                graph[i] = new ArrayList<>();
            }

            for(int i = 1; i<= M; i++){
                line = br.readLine();
                st = new StringTokenizer(line);
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                graph[s].add(e);
                graph[e].add(s);
            }

            // 모든 연결요소 구한다. 이때 사이클 형성 여부 확인한다.
//            System.out.println("case =" + caseNo);
            int numberOfTree = 0;
            boolean[] visited = new boolean[N + 1];
            DisjointSet ds = new DisjointSet(N);
            for(int i = 1; i <= N; i++){
                if(!visited[i]){
                    cycleDetected = false;
//                    System.out.println("visit i = " + i);
                    dfs(i, 0, visited, ds);
                    if(!cycleDetected){
                        numberOfTree++;
                    }
                }
            }

            // 정댭을 출력한다.
            if(numberOfTree == 0){
                sb.append("Case " + caseNo +": No trees.\n");
            }
            else if(numberOfTree == 1){
                sb.append("Case " + caseNo + ": There is one tree.\n");
            }
            else{
                sb.append("Case " + caseNo + ": A forest of " + numberOfTree+ " trees.\n");
            }
            caseNo++;
        }
        System.out.print(sb.toString());
    }

    private static void dfs(int cur, int prev, boolean[] visited, DisjointSet ds){
        if(visited[cur]){
            return;
        }
        visited[cur] = true;
        for(int next : graph[cur]){
            if(next != prev && ds.union(cur, next) == false){
                cycleDetected = true;
            }
            dfs(next, cur, visited, ds);
        }
    }

    static class DisjointSet{
        int[] parents;

        public DisjointSet(int n){
            parents = new int[n + 1];
            for(int i = 1; i <= n; i++){
                parents[i] = i;
            }
        }

        public int find(int u){
            if(parents[u] == u){
                return u;
            }
            return parents[u] = find(parents[u]);
        }

        public boolean union(int u, int v){
            int up = find(u);
            int vp = find(v);
            if(up != vp){
                parents[u] = parents[v];
                return true;
            }
            return false;
        }
    }
}