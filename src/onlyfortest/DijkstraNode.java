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
	static int M = 10000000;// (此路不通)
	static final int stationNum = 318;// 站点总数
	// static final String[] stationsName = { "石厂", "小园", "栗园庄", "上岸", "桥户营" };
	static double distanceSum = 0;// 图中所有两点之间的 最短路径 的和
	static double distanceDaoShuSum = 0;// 图中所有两点之间的 最短路径倒数 的和
	static LinkedList<String> stationsName = new LinkedList<String>();// 按顺序存放站点名字
	static int[][] graph = new int[stationNum][stationNum];// 邻接矩阵

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 获取站点名
		File fileName = new File("F:/bjut_ai_maya/subwayName.txt");// E:\subwayStation\20180416
		File fileDis = new File("F:/bjut_ai_maya/subway.txt");// E:\subwayStation\20180416
		File output = new File("F:/bjut_ai_maya/outputData.txt");// 输出数据文件
		// 邻接矩阵初始化
		for (int i = 0; i < stationNum; i++) {
			for (int j = 0; j < stationNum; j++) {
				if (i == j)
					graph[i][j] = 0;// 对角线初始为0
				else
					graph[i][j] = M;// 初始化程序中的站点邻接矩阵
			}
		}

		try {
			BufferedReader buffer = new BufferedReader(new FileReader(fileName));
			String line;
			int rowNum = 0;
			while ((line = buffer.readLine()) != null) {
				String[] lineData = line.split("	");// 每行数据分割
				stationsName.add(lineData[1]);
			}
			buffer.close();
			buffer = new BufferedReader(new FileReader(fileDis));
			while ((line = buffer.readLine()) != null) {
				String[] lineData = line.split("	");// 每行数据分割
				for (int i = 1; i < lineData.length; i++) {
					int stationId = Integer.valueOf(lineData[i]) - 1;
					graph[rowNum][stationId] = 1;
				}
				rowNum++;
			}
			buffer.close();

			// 最外层的for计算所有单点
			BufferedWriter outputData = new BufferedWriter(new FileWriter(output));
			for (int i = 0; i < stationNum; i++) {
				int[] shortPath = new int[stationNum];
				shortPath = Dijkstra(graph, i);
				for (int m = 0; m < shortPath.length; m++) {
					if (shortPath[m] == M) {
						String info = "从" + stationsName.get(i) + "出发到" + stationsName.get(m) + "的最短距离为：不可达" + "\n";// 不可达
						outputData.write(info);
					} else if (shortPath[m] == 0) {
						String info = "从" + stationsName.get(i) + "出发到" + stationsName.get(m) + "的最短距离为：本身站点" + "\n";// 本身站点
						outputData.write(info);
					} else {
						String info = "从" + stationsName.get(i) + "出发到" + stationsName.get(m) + "的最短距离为："
								+ shortPath[m] + "\n";// 单点出发到各点的最短路径
						outputData.write(info);
						distanceSum = distanceSum + shortPath[m];
						distanceDaoShuSum = distanceDaoShuSum + 1 / shortPath[m];
					}
				}
			}
			String distanceSumInfo = "图中各站点到另一个站点的最短距离和：" + distanceSum + "\n";
			String distanceDaoShuSumInfo = "图中各站点到另一个站点的最短距离倒数的和：" + distanceDaoShuSum + "\n";
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
		// 接受一个有向图的权重矩阵，和一个起点编号start（从0编号，顶点存在数组中）
		// 返回一个int[] 数组，表示从start到它的最短路径长度
		int n = weight.length; // 顶点个数
		int[] shortPath = new int[n]; // 存放从start到其他各点的最短路径
		String[] path = new String[n]; // 存放从start到其他各点的最短路径的字符串表示
		for (int i = 0; i < n; i++)
			path[i] = new String(start + "-->" + i);
		int[] visited = new int[n]; // 标记当前该顶点的最短路径是否已经求出,1表示已求出

		// 初始化，第一个顶点求出
		shortPath[start] = 0;
		visited[start] = 1;

		for (int count = 1; count <= n - 1; count++) // 要加入n-1个顶点
		{
			int k = -1; // 选出一个距离初始顶点start最近的未标记顶点
			int dmin = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				if (visited[i] == 0 && weight[start][i] < dmin) {
					dmin = weight[start][i];
					k = i;
				}
			}
			// System.out.println("k=" + k);
			// 将新选出的顶点标记为已求出最短路径，且到start的最短路径就是dmin
			shortPath[k] = dmin;
			visited[k] = 1;
			// 以k为中间点，修正从start到未访问各点的距离
			for (int i = 0; i < n; i++) { // System.out.println("k="+k);
				if (visited[i] == 0 && weight[start][k] + weight[k][i] < weight[start][i]) {
					weight[start][i] = weight[start][k] + weight[k][i];
					path[i] = path[k] + "-->" + i;
				}
			}
		}
		// for (int i = 0; i < n; i++)
		// System.out.println("从" + start + "出发到" + i + "的最短路径为：" + path[i]);
		// System.out.println("=====================================");
		return shortPath;
	}
}