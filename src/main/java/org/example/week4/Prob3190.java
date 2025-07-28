package org.example.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Prob3190 {
    /*
    https://www.acmicpc.net/problem/3190

    25/7/28 00:00 ~ 01:20

    ***####
    #######
    #######

    ****####
    ########
    ########

    #***###
    ########
    ########

    ---
    ##***###
    ########
    ########

    ##***###
    ####*###
    ########

    ###**###
    ####*###
    ########

    뱀이 차지하는 칸을 큐에 저장?
    가장 뒷 부분이 큐의 프론트에 있어야 함

    IndexOutOfBounds 버그를 디버깅하는데 시간이 오래걸림
    버그의 원인은 자바의 나머지 연산 때문
    자바의 나머지 연산은 수학적 모듈로 연산이 아니라서 값이 음수일 수 있다는 점에 주의해야함
    자주 쓰이는 패턴 암기
    x = (x - 1 + N) % N; → 감소하면서 순환 인덱스 유지
     */

    private static int N, K, L;
    private static int[][] map;     // 0 빈공간, 1 사과, 2 뱀
    private static Map<Integer, String> commands = new HashMap<>();

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        for(int i = 0; i < K; i++){
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            map[row][col] = 1;
        }
        L = Integer.parseInt(br.readLine());
        for(int i = 0; i < L; i++){
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            commands.put(time, direction);
        }

        int endTime = simulate();

        // 게임이 끝나는 시간 출력
        System.out.println(endTime);
    }

    public static int simulate(){
        // 시뮬레이션 시작

        // 시간이 되면 뱀의 방향 전환
        // 뱀의 머리가 이동할 공간을 확인. 처음엔 오른쪽
        // 뱀이거나 벽이면 시뮬 종료
        // 사과가 있으면 기억
        // 이동할 공간에 머리를 추가
        // 사과가 안 만났으면 꼬리를 제거
        // 시간을 증가

        int clock = 0;
        Snake snake = new Snake(map);
        while(true){
//            System.out.println("map = ");
//            for(int i = 1; i <= N; i++){
//                for(int j = 1; j <= N; j++){
//                    System.out.print(map[i][j]);
//                }
//                System.out.println();
//            }
//            System.out.println();

            String command = commands.get(clock);
            if(command != null){
//                System.out.println("clock =" + clock + ", command = " + command);
                snake.turn(command);
            }
            clock++;
            if(!snake.move(N, map)){
                break;
            }
        }
        return clock;
    }

    static class Snake{
        Queue<Node> body = new LinkedList();
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, -1, 0, 1 };
        int headX = 1;
        int headY = 1;
        int direction = 2;
        public Snake(int[][] map){
            body.add(new Node(1, 1));
            map[1][1] = 2;
        }
        public void turn(String command){
            if(command.equals("L")){
                direction = (direction - 1 + 4) % 4;
                return;
            }
            direction = (direction + 1) % 4;
        }
        public boolean move(int N, int[][] map){
            int nx = headX + dx[direction];
            int ny = headY + dy[direction];
//            System.out.println("nx = " + nx + ", ny = " + ny);
            if(nx < 1 || nx > N || ny < 1 || ny > N){
                return false;
            }
            if(map[ny][nx] == 2){
                return false;
            }
            body.add(new Node(nx, ny));
            if(map[ny][nx] != 1){
                Node tail = body.poll();
                map[tail.y][tail.x] = 0;
            }
            map[ny][nx] = 2;
            headX = nx;
            headY = ny;
            return true;
        }
    }

    static class Node{
        int x;
        int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}