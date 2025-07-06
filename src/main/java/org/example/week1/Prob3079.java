package org.example.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Prob3079 {
    /*
    https://www.acmicpc.net/problem/3079

    25/7/5 20:30 ~

    가장 빨리 걸리는 심사대에서 걸리는 시간이 최대 대기 시간이 되도록 나머지 심사대에 균등하게 나누어준다?
    3 x 3 5 x 2 반례 존재하니 가설 틀림

    최대 대기 시간을 내가 정해놓고 분배가 가능한지 확인한다?
    근데 파라매트릭 서치를 사용하려면 최대 대기 시간을 줄여 나갈 때 가능한 지점과 불가능한 지점이 나뉘어야 하는데?
    생각해보니 되는 것 같네

    4프로에서 틀림

    테스트 케이스 통과해야함
    1 1000000000
    1000000000

    34프로에서 틀림
    isPossible함수에서 오버플로우를 제대로 해결하지 못해서 생긴 문제임

     */
    public static void main(String[] args) throws IOException{
        int N, M;
        int[] waitingTimes;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력을 받는다
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        waitingTimes = new int[N];
        for(int i = 0; i < N; i++){
            waitingTimes[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(waitingTimes);

//        for(int i = 0; i < N; i++){
//            System.out.println(waitingTimes[i]);
//        }

        // 파라매트릭 서치를 이용한다
        long lo = 0;
        long hi = ((long) waitingTimes[N - 1]) * M;
        while(lo + 1 < hi){
            long mid = (lo + hi) / 2;
            if(isPossibleTotalTime(mid, M, waitingTimes)){
                hi = mid;
            }
            else{
                lo = mid;
            }
        }
        System.out.println(hi);
        // hi가 결과이다?
    }

    private static boolean isPossibleTotalTime(long totalTime, int M, int[] waitingTimes){
        // 가장 적은 대기시간부터 사람들을 분배한다
        long peopleToAddress = M;
        for(int i = 0; i < waitingTimes.length; i++){
            peopleToAddress -= totalTime / waitingTimes[i];
            if(peopleToAddress <= 0){ // 계산 중에 확인해야 언더플로우가 안남
                return true;
            }
        }
        return false;
    }
}