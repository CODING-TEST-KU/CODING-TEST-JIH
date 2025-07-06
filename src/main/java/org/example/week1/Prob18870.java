package org.example.week1;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Prob18870 {
    /*
    https://www.acmicpc.net/problem/18870

    25/7/4 21:52 ~ 22:20
     */
    public static void main(String[] args) throws IOException{
        int N;
        int[] X;
        Map<Integer, Integer> map = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 수열을 집합에 입력받는다
        String line = br.readLine();
        N = Integer.parseInt(line);
        line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        X = new int[N];
        for(int i = 0; i < N; i++){
            X[i] = Integer.parseInt(st.nextToken());
            map.put(X[i], 0);
        }

        // 수열을 집합에 있는 수를 정렬한다
        Integer[] elems = map.keySet().toArray(new Integer[0]);
        Arrays.sort(elems);

        // 차례로 개수를 구한다.
        int lessThanNumber = 0;
        for(Integer elem : elems){
            map.put(elem, lessThanNumber++);
        }

        for(int x : X){
            bw.write(map.get(x) + " ");
        }
        bw.flush();
    }
}