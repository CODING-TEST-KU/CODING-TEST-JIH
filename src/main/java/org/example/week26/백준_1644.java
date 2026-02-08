package org.example.week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 백준_1644 {
    /*
    https://www.acmicpc.net/problem/1644

    26/2/8 17:43 ~ 18:13

    어떤 자연수가 주어지면, 연속된 소숙의 합으로 나타낼 수 있는 경우의수를 구하자

    소수 K개만 사용해서 자연수로 나타낼 있는지는 알아낼 수 있다.
    근데 너무 많이 소수집합을 순회한다

    슬라이딩 윈도우를 이용할 수 있을까?
    윈도우를 점진적으로 키우면서 어떤 자연수를 만들 수 있는지 알아보면 어떨까?
    */

    public static void main(String[] args) throws IOException {
        백준_1644 main = new 백준_1644();
        main.solution();
    }

    private void solution() throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if(N == 1){
            System.out.println(0);
            return;
        }

        // 소수 집합 구하기
        List<Integer> primeNumbers = new ArrayList<>();
        for(int i = 2; i <= N; i++){
            if(isPrime(i)){
                primeNumbers.add(i);
            }
        }

        // 슬라이딩 윈도우로 합이 N이 되는 경우의 수를 찾는다
        int answer = 0;
        int lo = 0;
        int hi = 0;
        int sum = primeNumbers.get(0);
        while(hi < primeNumbers.size()){
//            System.out.println(lo + " " + hi + " " + sum);
            // 합이 N보다 작으면 오른쪽으로 확장
            if(sum < N){
                hi++;
                if(hi >= primeNumbers.size()){
                    break;
                }
                sum += primeNumbers.get(hi);
                continue;
            }
            // 합이 N이면 카운트하고 왼쪽을 삭제하고
            if(sum == N){
                answer++;
                sum -= primeNumbers.get(lo);
                lo++;
                continue;
            }
            // 합이 N보다 크면 왼쪽을 삭제
            if(sum > N){
                sum -= primeNumbers.get(lo);
                lo++;
            }
        }

        // 결과를 출력
        System.out.println(answer);
    }

    private boolean isPrime(int num){
        for(int i = 2; i <= Math.sqrt(num); i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
}
