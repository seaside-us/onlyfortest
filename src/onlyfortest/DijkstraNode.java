package onlyfortest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class DijkstraNode {
	static int M = 10000000;// (��·��ͨ)
	static final int stationNum = 318;// վ������
	// static final String[] stationsName = { "ʯ��", "С԰", "��԰ׯ", "�ϰ�", "�Ż�Ӫ" };
	static double distanceSum = 0;// ͼ����������֮��� ���·�� �ĺ�
	static double distanceDaoShuSum = 0;// ͼ����������֮��� ���·������ �ĺ�
	static LinkedList<String> stationsName = new LinkedList<String>();// ��˳����վ������
	static int[][] graph = new int[stationNum][stationNum];// �ڽӾ���

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ��ȡվ����
		File fileName = new File("F:/bjut_ai_maya/subwayName.txt");// E:\subwayStation\20180416
		File fileDis = new File("F:/bjut_ai_maya/subway.txt");// E:\subwayStation\20180416
		File output = new File("F:/bjut_ai_maya/outputData.txt");// ��������ļ�
		// �ڽӾ����ʼ��
		for (int i = 0; i < stationNum; i++) {
			for (int j = 0; j < stationNum; j++) {
				if (i == j)
					graph[i][j] = 0;// �Խ��߳�ʼΪ0
				else
					graph[i][j] = M;// ��ʼ�������е�վ���ڽӾ���
			}
		}

		try {
			BufferedReader buffer = new BufferedReader(new FileReader(fileName));
			String line;
			int rowNum = 0;
			while ((line = buffer.readLine()) != null) {
				String[] lineData = line.split("	");// ÿ�����ݷָ�
				stationsName.add(lineData[1]);
			}
			buffer.close();
			buffer = new BufferedReader(new FileReader(fileDis));
			while ((line = buffer.readLine()) != null) {
				String[] lineData = line.split("	");// ÿ�����ݷָ�
				for (int i = 1; i < lineData.length; i++) {
					int stationId = Integer.valueOf(lineData[i]) - 1;
					graph[rowNum][stationId] = 1;
				}
				rowNum++;
			}
			buffer.close();

			// ������for�������е���
			BufferedWriter outputData = new BufferedWriter(new FileWriter(output));
			for (int i = 0; i < stationNum; i++) {
				int[] shortPath = new int[stationNum];
				shortPath = Dijkstra(graph, i);
				for (int m = 0; m < shortPath.length; m++) {
					if (shortPath[m] == M) {
						String info = "��" + stationsName.get(i) + "������" + stationsName.get(m) + "����̾���Ϊ�����ɴ�" + "\n";// ���ɴ�
						outputData.write(info);
					} else if (shortPath[m] == 0) {
						String info = "��" + stationsName.get(i) + "������" + stationsName.get(m) + "����̾���Ϊ������վ��" + "\n";// ����վ��
						outputData.write(info);
					} else {
						String info = "��" + stationsName.get(i) + "������" + stationsName.get(m) + "����̾���Ϊ��"
								+ shortPath[m] + "\n";// �����������������·��
						outputData.write(info);
						distanceSum = distanceSum + shortPath[m];
						distanceDaoShuSum = distanceDaoShuSum + 1 / shortPath[m];
					}
				}
			}
			String distanceSumInfo = "ͼ�и�վ�㵽��һ��վ�����̾���ͣ�" + distanceSum + "\n";
			String distanceDaoShuSumInfo = "ͼ�и�վ�㵽��һ��վ�����̾��뵹���ĺͣ�" + distanceDaoShuSum + "\n";
			outputData.write(distanceSumInfo);
			outputData.write(distanceDaoShuSumInfo);
			outputData.close();
			System.out.println(distanceSumInfo + distanceDaoShuSumInfo);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int[] Dijkstra(int[][] weight, int start) {
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
			// System.out.println("k=" + k);
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
		// for (int i = 0; i < n; i++)
		// System.out.println("��" + start + "������" + i + "�����·��Ϊ��" + path[i]);
		// System.out.println("=====================================");
		return shortPath;
	}
}