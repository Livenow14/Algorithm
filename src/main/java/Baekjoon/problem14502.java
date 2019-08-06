package Baekjoon;

import java.util.*;
import java.io.*;
public class problem14502 {

    static class position{
        int x,y;

        public position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int N, M;
    static int[][] map, nmap;
    static int answer = 0;
    static int[] dx = { 0, 0, -1 ,1};
    static int[] dy = {1, -1, 0 , 0};
    static ArrayList<position> virusPosition = new ArrayList<position>();

    public static void main( String[] args ) throws IOException, NumberFormatException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        //Get Input Area
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        nmap = new int[N][M];
        for( int i=0;i<N;i++){
            st =new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<M;j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        solve(0,0);
        //Algorithms Section
        System.out.println(answer);
    }

    static void solve(int start, int depth){
        if( depth == 3){

            for( position pos : virusPosition)
                spread(pos.x, pos.y);

            answer = Math.max(answer, getSafeArea());

            return;
        }

        for(int i=start;i<N*M;i++){
            int x = i%M;
            int y = i/M;
            if( map[y][x] == 0){
                map[y][x] =1;
                solve(i+1, depth+1);
                map[y][x] = 0;
            }
        }

    }
    static void copyMap(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++)
                nmap[i][j] = map[i][j];
        }
    }
    static void spread(int x, int y){
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if( nx >=0 && nx <M && ny>=0 && ny < N ){
                if(nmap[ny][nx] == 0){
                    nmap[ny][nx] = 2;
                    spread(nx, ny);
                }
            }
        }
    }

    static int getSafeArea(){
        int safe = 0;

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if( map[i][j] ==0 )
                    safe++;
            }
        }
        return safe;
    }
}
