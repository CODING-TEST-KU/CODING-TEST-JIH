package org.example.week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_1764 {
    /*
    https://www.acmicpc.net/problem/1764

    26/2/8 21:14 ~ 21:25

    N과 M이 50만 이하라서 시간 초과에 걸릴지도 모륹다
    */

    public static void main(String[] args) throws IOException {
        백준_1764 main = new 백준_1764();
        main.solution();
    }

    private void solution() throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N, M;
        String[] notHeard;
        HashSet<String> notSeen;

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        notHeard = new String[N];
        for(int i = 0; i < N; i++){
            notHeard[i] = br.readLine();
        }

        notSeen = new HashSet<>();
        for(int i = 0; i < M; i++){
            notSeen.add(br.readLine());
        }

        List<String> neither = new ArrayList<>();
        for(int i = 0; i < N; i++){
            if(notSeen.contains(notHeard[i])){
                neither.add(notHeard[i]);
            }
        }

        neither.sort(String::compareTo);
        System.out.println(neither.size());
        for(int i = 0; i < neither.size(); i++){
            System.out.println(neither.get(i));
        }
    }
}
