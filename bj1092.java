package example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class bj1092 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		ArrayList<Integer> crane = new ArrayList<>();

		
		for(int i = 0; i < N; i++)
		{
			crane.add(Integer.parseInt(st.nextToken()));
		}
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		ArrayList<Integer> box = new ArrayList<>();
		
		for(int j = 0; j < M ; j++)
		{
			box.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(crane, Collections.reverseOrder());
		Collections.sort(box, Collections.reverseOrder());
		
		int result = 0;

		if(box.get(0)>crane.get(0)) result = -1; // 박스가 크레인보다 크다면 옮기지 못해서 -1출력
		else 
		{
			while(box.size() != 0)
			{
			
				int idx = 0;
				int temp = 0;
				while( N > idx) 
				{
					if(temp == box.size()) break;  
					else if(box.get(temp)<= crane.get(idx)) { // 크레인으로 박스를 옮길 수 있을 때
						box.remove(temp);
						idx++;
					}
					else if(box.get(temp) > crane.get(idx)) temp++; // 크레인으로 박스을 못 옮길 때
				}
				result++;
			
			}
		}

		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
		
	}

}
