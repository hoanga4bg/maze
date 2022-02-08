/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import miniproject.QItem;

/**
 *
 * @author hoang
 */
public class Miniproject {

    public static final int MAX_INT=9999999;
    public static int minDistance(int sRow, int sCol, int dRow, int dCol,int[][] maze) {
        QItem start = new QItem(0, 0, 0);
        start.row = sRow;
        start.col = sCol;

        // applying BFS on matrix cells starting from source
        Queue<QItem> queue = new LinkedList<>();
        queue.add(new QItem(start.row, start.col, 1));
        
        // if start point on maze has value = 1 retunr -1 
        if(maze[sRow][sCol]==1){
            return -1;
        }
        
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        visited[start.row][start.col] = true;

        while (queue.isEmpty() == false) {
            QItem p = queue.remove();

            // Destination found;
            if (p.row == dRow && p.col == dCol) {
                return p.dist;
            }

            // moving up
            if (isValid(p.row - 1, p.col,maze, visited)) {
                queue.add(new QItem(p.row - 1, p.col,
                        p.dist + 1));
                visited[p.row - 1][p.col] = true;
            }

            // moving down
            if (isValid(p.row + 1, p.col,maze, visited)) {
                queue.add(new QItem(p.row + 1, p.col,
                        p.dist + 1));
                visited[p.row + 1][p.col] = true;
            }

            // moving left
            if (isValid(p.row, p.col - 1,maze, visited)) {
                queue.add(new QItem(p.row, p.col - 1,
                        p.dist + 1));
                visited[p.row][p.col - 1] = true;
            }

            // moving right
            if (isValid(p.row, p.col + 1,maze,visited)) {
                queue.add(new QItem(p.row, p.col + 1,
                        p.dist + 1));
                visited[p.row][p.col + 1] = true;
            }
        }
        return -1;
    }

    // checking where it's valid or not
    private static boolean isValid(int x, int y,int[][] maze,boolean[][] visited) {
        if (x >= 0 && y >= 0 && x < maze.length
                && y < maze[0].length && maze[x][y] == 0
                && visited[x][y] == false) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int startRow=sc.nextInt()-1;
        int startCol=sc.nextInt()-1;
//        int[][] maze = {
//            {1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
//            {1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1},
//            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1},
//            {1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
//            {1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0},
//            {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
//            {1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1}
//            
//        };

        // Enter maze
        int[][] maze=new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                int temp=sc.nextInt();
                maze[i][j]=temp;
            }
        }
        
        // if start position at exit point min step = 1
        if(startCol==0 || startRow==0 || startCol==(m-1) || startRow==(m-1)){
            System.out.println("1");
        }
        else{
            // list exit points
            List<Point> listPoints=new ArrayList<Point>();
            
            // get exit point on first row
            for(int j=0;j<m;j++){
                if(maze[0][j]==0){
                    Point p=new Point();
                    p.x=0;
                    p.y=j;
                    listPoints.add(p);
                }
            }
            
            // get exit point on last row
            for(int j=0;j<m;j++){
                if(maze[n-1][j]==0){
                    Point p=new Point();
                    p.x=n-1;
                    p.y=j;
                    listPoints.add(p);
                }
            }
            
            // get exit point on first colunm
            for(int i=0;i<n;i++){
                if(maze[i][0]==0){
                    Point p=new Point();
                    p.x=i;
                    p.y=0;
                    listPoints.add(p);
                }
            }
            
            // get exit point on last colunm
            for(int i=0;i<n;i++){
                if(maze[i][m-1]==0){
                    Point p=new Point();
                    p.x=i;
                    p.y=m-1;
                    listPoints.add(p);
                }
            }
            
            
            int min=MAX_INT;
            // caculate distance form start point to each exit point
            // if distance < min => min = distance
            for(Point p:listPoints){
//                System.out.print("x: "+p.x+" - y: "+p.y+ " =>");
                int distance=minDistance(startRow, startCol, p.x, p.y, maze);
//                System.out.println(distance);
                if(distance!=-1 && distance<min){
                    min=distance;
                }
            }
            if(min!=MAX_INT){
                System.out.println(min);
            }else{
                System.out.println("-1");
            }
        }

    }

}
