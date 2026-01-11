package org.example.week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_16235 {
    /*

    https://www.acmicpc.net/problem/16235

    26/1/10 14:43 ~ 15:55

    N x N땅에 M개의 나무를 심고 K년 뒤에 살아 있는 나무의 수를 구하자

    for-each문 사용할 때 컬렉션을 수정하면 ConcurrentModificationException 발생함에 주의
    Deque을 사용하는 개선 방안에 대해서 고민해보나
    */

    private int answer = 0;
    private int N, M, K;
    private int[][] A;
    private int[][] land;
    private List<Tree>[][] trees;

    public static void main(String[] args) throws IOException {
        백준_16235 main = new 백준_16235();
        main.solution();
    }

    public void solution() throws IOException {
        init();

        // 처음에는 땅에 양분이 5
        // K년 동안
        for(int year = 1; year <= K; year++){
//            System.out.println("spring = " + year);
            spring();
//            print();

//            System.out.println("summer = " + year);
            summer();
//            print();

//            System.out.println("fall = " + year);

            fall();
//            print();

//            System.out.println("winter = " + year);

            winter();
//            print();

        }

        countAlive();

        System.out.println(answer);
    }

    private void print(){
        System.out.println("land = ");
        for(int i = 1; i <= N; i++){
            System.out.println(Arrays.toString(land[i]));
        }
        System.out.println("trees = ");
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(trees[i][j].size() == 0) continue;
                System.out.println("(r,c) = " + i + "," + j + " " + trees[i][j]);
            }
        }
    }

    private void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N + 1][N + 1];
        for(int r = 1; r <= N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 1; c <= N; c++){
                A[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        land = new int[N + 1][N + 1];
        for(int r = 1; r <= N; r++){
            for(int c = 1; c <= N; c++){
                land[r][c] = 5;
            }
        }

        trees = new ArrayList[N + 1][N + 1];
        for(int r = 1; r <= N; r++){
            for(int c = 1; c <= N; c++){
                trees[r][c] = new ArrayList<>();
            }
        }

        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());

            trees[r][c].add(new Tree(a));
        }
    }

    private void spring(){
        // 어린 나무부터 자신의 나이만큼 자신의 칸에서 양분을 차감하고 나이를 1 증가
        // 나이만큼 양분 못 먹으면 죽은 상태로 전이
        for(int r = 1; r <= N; r++){
            for(int c = 1; c <= N; c++){
                if(trees[r][c].size() == 0) continue;
                trees[r][c].sort((a, b) -> a.age - b.age);
                for(Tree tree : trees[r][c]){
                    if(tree.age <= land[r][c]){
                        land[r][c] -= tree.age;
                        tree.age++;
                    }
                    else{
                        tree.isAlive = false;
                    }
                }
            }
        }
    }

    private void summer(){
        // 죽은 나무의 (수명)/2만큼 칸에 양분을 추가
        for(int r = 1; r <= N; r++){
            for(int c = 1; c <= N; c++){
                if(trees[r][c].size() == 0) continue;
                List<Tree> toRemove = new ArrayList<>();
                for(int i = 0; i < trees[r][c].size(); i++){
                    Tree tree = trees[r][c].get(i);
                    if(tree.isAlive == false){
                        land[r][c] += tree.age / 2;
                        toRemove.add(tree);
                    }
                }
                for(Tree t : toRemove){
                    trees[r][c].remove(t);
                }
            }

        }
    }

    private void fall(){
        // 나이가 5의 배수이면
            // 인접한 8칸에 나이 1인 나무 추가
        int[] dr = { 0, -1, -1, -1, 0, 1, 1, 1 };
        int[] dc = { -1, -1, 0, 1, 1, 1, 0, -1 };
        for(int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (trees[r][c].size() == 0) continue;
                for(Tree tree : trees[r][c]){
                    if(tree.age % 5 == 0){
                        for(int i = 0; i < 8; i++){
                            int nr = r + dr[i];
                            int nc = c + dc[i];
                            if(nr < 1 || nr > N || nc < 1 || nc > N) continue;
                            trees[nr][nc].add(new Tree(1));
                        }
                    }
                }
            }
        }
    }

    private void winter(){
        // A에 맞게 양분을 추가
        for(int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                land[r][c] += A[r][c];
            }
        }
    }

    private void countAlive(){
        for(int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                answer += trees[r][c].size();
            }
        }
    }

    static class Tree{
        int age;
        boolean isAlive;

        public Tree(int age){
            this.age = age;
            this.isAlive = true;
        }

        public String toString(){
            return "Tree = (" + age + "," + isAlive + ")";
        }
    }
}