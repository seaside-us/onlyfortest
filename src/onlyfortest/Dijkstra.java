package onlyfortest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Dijkstra {
	static int M = 10000000;// (��·��ͨ)
	static int size = 5;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// �ڽӾ���
		int[][] graph = new int[5][5];
		String[] stations= new String[5];
		File file = new File("F:/bjut_ai_maya/subway.txt");
		int count = 0;
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			String line;
			while ((line = buffer.readLine()) != null) {
				String[] lineData = line.split("	");// ÿ�����ݷָ�������һ��Ԫ��Ϊվ��,but ��һ��ȫ��վ��
				if (count == 0) {// ��һ��Ϊ����վ����
					System.out.println("��" + count + "�У�" + line);
					for(int i=0;i<lineData.length;i++){
						stations[i]=lineData[i].trim();
					}
				} else {
					for (int i = 1; i < lineData.length; i++) {
						int dis = Integer.valueOf(lineData[i]);
						if (dis == -1) {
							graph[count - 1][i - 1] = M;
						} else
							graph[count - 1][i - 1] = dis;
					}
				}
				count++;
			}
			buffer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < size; i++) {
			int[] shortPath = new int[size];
			shortPath = Dijsktra(graph, i);
			for (int m = 0; m < shortPath.length; m++)
				System.out.println("��" + stations[i] + "������" + stations[m] + "����̾���Ϊ��" + shortPath[m]);
		}

	}

	public static int[] Dijsktra(int[][] weight, int start) {
		// ����һ������ͼ��Ȩ�ؾ��󣬺�һ�������start����0��ţ�������������У�
		// ����һ��int[] ���飬��ʾ��start���������·������
		int n = weight.length; // �������
		int[] shortPath = new int[n]; // ��Ŵ�start��������������·��
		String[] path = new String[n]; // ��Ŵ�start��������������·�����ַ�����ʾ
		for (int i = 0; i < n; i++)
			path[i] = new String(start + "-->" + i);
		int[] visited = new int[n]; // ��ǵ�ǰ�ö�������·���Ƿ��Ѿ����,1��ʾ�����

		// ��ʼ������һ���������
		shortPath[start] = 0;
		visited[start] = 1;

		for (int count = 1; count <= n - 1; count++) // Ҫ����n-1������
		{
			int k = -1; // ѡ��һ�������ʼ����start�����δ��Ƕ���
			int dmin = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				if (visited[i] == 0 && weight[start][i] < dmin) {
					dmin = weight[start][i];
					k = i;
				}
			}
			System.out.println("k=" + k);
			// ����ѡ���Ķ�����Ϊ��������·�����ҵ�start�����·������dmin
			shortPath[k] = dmin;
			visited[k] = 1;
			// ��kΪ�м�㣬������start��δ���ʸ���ľ���
			for (int i = 0; i < n; i++) { // System.out.println("k="+k);
				if (visited[i] == 0 && weight[start][k] + weight[k][i] < weight[start][i]) {
					weight[start][i] = weight[start][k] + weight[k][i];
					path[i] = path[k] + "-->" + i;
				}
			}
		}
		for (int i = 0; i < n; i++)
			System.out.println("��" + start + "������" + i + "�����·��Ϊ��" + path[i]);
		System.out.println("=====================================");

		return shortPath;
	}
}