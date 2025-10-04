package org.example.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1477 {
    /*
    25/10/4 00:11 ~

    https://www.acmicpc.net/problem/1477

    휴게소 사이의 최대 거리를 최소화해야 한다
    파라매트릭 서치임은 알 수 있다

    휴게소가 없는 구간의 길이이 최대가 x가 이도록 휴게소를 M개 배치하려고 시도해보자
    가능하면 -> x 줄여야 한다.
    안되면 -> x를 바꿔야한다

    3 1 1000
    200 701 800

    처음 구간의 길이 : 200, 501, 200
    200, 251, 250, 200 -> 이게 맞음
    200 200 301 200 -> 이런거 안됨

    3 1 1000
    300 701 800

    처음 : 300 401 200
    배치 :
    300, 200, 201, 200 -> 이건 맞음
    300, 100, 301, 200 -> 이러면 안됨 최소가 아님

    가만보니
    주어진 주유소가 없는 구간을 보고 x로 나누어 본다 x로 나눈 몫이 세워야할 최소의 주유소 개수이다
    근데 나는 M개만 세워야 한다
    M개 초과면? -> M개로는 못한다
    M개 이하면? -> M개로도 가능하다!

    25/10/4 18:24 ~

N = 0도 처리해야한다

3 1 1000
200 701 800

현재 구간의 길이
200 501 99 200

200 251 250 99 200
200 250 251 99 200
사실 휴게소의 위치는 유일하지 않다

최대길이의 최솟값을 구하라 -> 파라매트릭 서치의 신호

최대 길이가 x가 되도록 M개의 주유소를 추가할 수 있는가?를 가지고 최솟값을 구한다
가능하다 -> x를 줄인다
불가능하다 -> x를 늘린다

가능여부는 어떻게 아는가?
주유소의 개수 = 각 구간의 길이 / x. 단, 길이가 나눈 나머지가 0이면 끝은 제외
주유소의 개수를 합 < M이면 -> 가능하다
>= M이면 -> 불가능하다

이진탐색의 상한과 하한은?
상한 : 구간의 최대 길이
하한 : 1
    */
    private static int N, M, L;
    private static int[] station;
    private static int[] length;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        if(N == 0){
            System.out.println((int) Math.ceil((double)L / (M + 1)));
            return;
        }

        st = new StringTokenizer(br.readLine());
        station = new int[N];
        for(int i = 0; i < N; i++){
            station[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(station);

        length = new int[N + 1];
        length[0] = station[0];
        for(int i = 1; i < N; i++){
            length[i] = station[i] - station[i - 1];
        }
        length[N] = L - station[N - 1];
        Arrays.sort(length);

        // 파라매트릭 서치
        int lo = 0;
        int hi = length[N];
        while(lo + 1 < hi){
            int mid = (lo + hi) / 2;
            if(place(mid)){
                hi = mid;
            }
            else{
                lo = mid;
            }
        }
        System.out.println(hi);
    }

    private static boolean place(int maxDist){
        // 가능 여부 확인
        int neededStation = 0;
        for(int i = 0; i < length.length; i++){
            neededStation += length[i] / maxDist;
            if(length[i] % maxDist == 0){
                neededStation -= 1;
            }
        }
        return neededStation <= M;
    }


    class PartiallyCorrect71{
        private static int N, M, L;
        private static int[] station;
        private static int[] length;

        public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            // 입력 처리
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            if(N > 0){
                station = new int[N];
                st = new StringTokenizer(br.readLine());
                for(int i = 0; i < N; i++){
                    station[i] = Integer.parseInt(st.nextToken());
                }
                Arrays.sort(station);

                length = new int[N + 1];
                length[0] = station[0];
                for(int i = 1; i < N; i++){
                    length[i] = station[i] - station[i - 1];
                }
                length[N] = L - station[N - 1];
                Arrays.sort(length);
            }
            else{
                length = new int[] {L};
            }

            int lo = length[0] / (M + 1);
            int hi = length[length.length - 1];
            while(lo + 1 < hi){
//            System.out.println("lo hi : " + lo + " " + hi);
                int mid = (lo + hi) / 2;
                if(canPlace(mid)){
                    hi = mid;
                }
                else{
                    lo = mid;
                }
            }
            System.out.println(hi);
        }

        private static boolean canPlace(int maxDist){
            // 최소로 세우는 주유소 수
            int count = 0;
            for(int i = 0; i < length.length; i++){
                count += length[i] / maxDist;
                if(length[i] % maxDist == 0){
                    count -= 1;
                }
            }
//        System.out.println("maxDist count ret: " + maxDist + " " + count + " " + (count <= M));
            return count <= M;
        }
    }
}