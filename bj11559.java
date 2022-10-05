package example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class bj11559 {

	private static char[][] map;
	private static boolean[][] visit;

	private static int[] dx = {-1, 0, 1, 0}; // 상하좌우
	private static int[] dy = {0, 1, 0, -1}; // 상하좌우

	private static int cnt = 0; // 같은 색깔의 뿌요 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		map = new char[12][6]; // 12줄 6개의 문자

		for (int i=0; i<12; i++) {
			String input = br.readLine();
			char[] c = input.toCharArray(); // 문자열을 char배열로 변환
			for (int j=0; j<6; j++) {
				map[i][j] = c[j];
			}
		}

		int rst = 0; // 결과 출력을 위해 선언
		while (true) {
			visit = new boolean[12][6];

			//연산 했는지 안했는지 체크
			boolean flag = false;

			//같은 것 찾는 연산
			for (int i=0; i<12; i++) {
				for (int j=0; j<6; j++) {
					if (!visit[i][j] && map[i][j] != '.') { // 방문하지 않았고 .이 아닐때
						cnt = 1;

						if (findCnt(i,j, map[i][j])) { //현재 뿌요와 같은 색의 뿌요 개수 4개 이상인지 확인
							flag = true;
							bfs(i,j,map[i][j]);
						}

					}
				}
			}

			if (flag) { //연산이 일어났다면 연쇄 증가
				rst++;
			} else { //연쇄가 없다면 탈출
				break;
			}


			while (true) {	//아래로 1칸씩 이동	
				boolean check = false;
				check = gravity(check); // //내려갈 친구들 있는지 체크

				if (!check) { //내려갈 뿌요가 없으면 탈출
					break;
				}

			}

		}
		sb.append(rst);
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	public static void bfs(int x, int y, char color) {

		Queue<Node> q = new LinkedList<>();

		q.add(new Node(x, y));

		while (!q.isEmpty()) {
			Node node = q.remove();

			int qx = node.x;
			int qy = node.y;

			for (int i=0; i<dx.length; i++) {
				int nx = qx + dx[i];
				int ny = qy + dy[i];

				if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6 || map[nx][ny] != color) {
					continue;
				}

				if (visit[nx][ny] && map[nx][ny] == color) { //이전 개수셀 때 방문했고 색상이 같으면 .으로 변경(터뜨리기)
					map[nx][ny] = '.'; // 터뜨리기
					q.add(new Node(nx, ny)); // 추가 후 다시 while문으로
				}
			}

		}

	}

	public static boolean gravity(boolean check) { //아래서부터 위로 중력실행
		for (int i=11; i>0; i--) {
			for (int j=5; j>=0; j--) {
				if (map[i][j] == '.' && map[i-1][j] != '.') { // 뿌요 밑에가 비어있을 때
					check = true;
					map[i][j] = map[i-1][j];
					map[i-1][j] = '.';
				}
			}
		}
		return check;
	}

	public static boolean findCnt(int x, int y, char color) { // 상하좌우로 4개의 뿌요가 있는지 확인

		visit[x][y] = true; // 방문

		for (int i=0; i<dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6 || map[nx][ny] != color) {
				continue;
			}

			if (!visit[nx][ny] && map[nx][ny] == color) { //인접한 곳중 색상이 같으면 카운트+1
				cnt++;
				findCnt(nx, ny, color);
			}
		}

		if (cnt >= 4) { // 4개가 같은면 true
			return true;
		}
		return false;

	}

}


class Node {
	int x, y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}