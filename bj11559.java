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

	private static int[] dx = {-1, 0, 1, 0}; // �����¿�
	private static int[] dy = {0, 1, 0, -1}; // �����¿�

	private static int cnt = 0; // ���� ������ �ѿ� ��

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		map = new char[12][6]; // 12�� 6���� ����

		for (int i=0; i<12; i++) {
			String input = br.readLine();
			char[] c = input.toCharArray(); // ���ڿ��� char�迭�� ��ȯ
			for (int j=0; j<6; j++) {
				map[i][j] = c[j];
			}
		}

		int rst = 0; // ��� ����� ���� ����
		while (true) {
			visit = new boolean[12][6];

			//���� �ߴ��� ���ߴ��� üũ
			boolean flag = false;

			//���� �� ã�� ����
			for (int i=0; i<12; i++) {
				for (int j=0; j<6; j++) {
					if (!visit[i][j] && map[i][j] != '.') { // �湮���� �ʾҰ� .�� �ƴҶ�
						cnt = 1;

						if (findCnt(i,j, map[i][j])) { //���� �ѿ�� ���� ���� �ѿ� ���� 4�� �̻����� Ȯ��
							flag = true;
							bfs(i,j,map[i][j]);
						}

					}
				}
			}

			if (flag) { //������ �Ͼ�ٸ� ���� ����
				rst++;
			} else { //���Ⱑ ���ٸ� Ż��
				break;
			}


			while (true) {	//�Ʒ��� 1ĭ�� �̵�	
				boolean check = false;
				check = gravity(check); // //������ ģ���� �ִ��� üũ

				if (!check) { //������ �ѿ䰡 ������ Ż��
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

				if (visit[nx][ny] && map[nx][ny] == color) { //���� ������ �� �湮�߰� ������ ������ .���� ����(�Ͷ߸���)
					map[nx][ny] = '.'; // �Ͷ߸���
					q.add(new Node(nx, ny)); // �߰� �� �ٽ� while������
				}
			}

		}

	}

	public static boolean gravity(boolean check) { //�Ʒ������� ���� �߷½���
		for (int i=11; i>0; i--) {
			for (int j=5; j>=0; j--) {
				if (map[i][j] == '.' && map[i-1][j] != '.') { // �ѿ� �ؿ��� ������� ��
					check = true;
					map[i][j] = map[i-1][j];
					map[i-1][j] = '.';
				}
			}
		}
		return check;
	}

	public static boolean findCnt(int x, int y, char color) { // �����¿�� 4���� �ѿ䰡 �ִ��� Ȯ��

		visit[x][y] = true; // �湮

		for (int i=0; i<dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6 || map[nx][ny] != color) {
				continue;
			}

			if (!visit[nx][ny] && map[nx][ny] == color) { //������ ���� ������ ������ ī��Ʈ+1
				cnt++;
				findCnt(nx, ny, color);
			}
		}

		if (cnt >= 4) { // 4���� ������ true
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