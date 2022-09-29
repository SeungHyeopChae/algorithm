package example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj1012 {
	
    static int m;
    static int n;
    static int k;
    static int[][] arr;
    static boolean[][] visited;
    static Queue<Node> q;
    
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        
        for(int c = 0; c < T; c++)
        {
            st = new StringTokenizer(br.readLine());
            
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            
            arr = new int[m][n];
            visited = new boolean[m][n];
            q = new LinkedList<>();
            
            int count = 0;
            
            for(int j = 0; j < k; j++)
            {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                arr[x][y] = 1;
            }
            
            for(int a = 0; a < m; a++)
            {
            	for(int b = 0; b < n; b++)
            	{
            		if(arr[a][b] == 1 && !visited[a][b])
            		{
            			q.add(new Node(a,b));
            			bfs();
            			count++;
            		}
            	}
            }
            
            
            sb.append(count).append("\n");
        }
        
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();

    }
    
    public static void bfs()
    {
        while(!q.isEmpty())
        {
            Node node = q.remove();
            int x = node.x;
            int y = node.y;
            visited[x][y] = true;
            
            for(int i = 0; i < 4; i++)
            {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx >= 0 && ny >= 0 && nx < m && ny < n && arr[nx][ny] == 1 && !visited[nx][ny])
                {
                	q.add(new Node(nx,ny));
                	visited[nx][ny] = true;
                }
            }
        }    
    }
}

class Node{
    int x;
    int y;
    
    public Node(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}