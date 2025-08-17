package org.example.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob20040 {
    /*
    https://www.acmicpc.net/problem/20040

    25/8/15 11:53 ~ 12:26

    사이클이 어느시점에 처음 만들어지는지 알아내야 한다

    유니온파인드를 이용해서 새로운 선을 그을 때 사이클이 만들어졌는지 확인하면 될 듯!

    왜 1%에서 틀렸을까?

    유니온 파인드 구현이 잘못되었기 때문! 최상위 부모끼리 부모를 정해야 한다!
    */

    public static void main(String[] args) throws IOException{
        int N, M;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int answer = 0;
        DisjointSet set = new DisjointSet(N);
        for(int i = 1; i <= M; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if(!set.union(u, v)){
                answer = i;
                break;
            }
        }

        // 결과 출력
        System.out.print(answer);
    }

    static class DisjointSet{
        int[] parents;

        public DisjointSet(int n){
            parents = new int[n];
            for(int i = 0; i < n; i++){
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
                parents[up] = vp;
                return true;
            }
            return false;
        }

    }
}